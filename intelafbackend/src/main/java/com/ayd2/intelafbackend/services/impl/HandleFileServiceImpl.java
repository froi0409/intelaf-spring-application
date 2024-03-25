package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.entities.ErrorEntity;
import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.entities.products.ProductStore;
import com.ayd2.intelafbackend.entities.products.ProductStorePK;
import com.ayd2.intelafbackend.entities.store.ShippingTime;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.DateFormatException;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.ParametersDoNotMatchException;
import com.ayd2.intelafbackend.exceptions.PhoneNumberSyntaxException;
import com.ayd2.intelafbackend.repositories.*;
import com.ayd2.intelafbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class HandleFileServiceImpl implements HandleFileService {

    private StoreRepository storeRepository;
    private ShippingTimeRepository shippingTimeRepository;
    private ProductRepository productRepository;
    private ProductStoreRepository productStoreRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private OrderRepository orderRepository;

    @Autowired
    public HandleFileServiceImpl(StoreRepository storeRepository, ShippingTimeRepository shippingTimeRepository, ProductRepository productRepository, ProductStoreRepository productStoreRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        this.storeRepository = storeRepository;
        this.shippingTimeRepository = shippingTimeRepository;
        this.productRepository = productRepository;
        this.productStoreRepository = productStoreRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void handleDataFile(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            ArrayList<ErrorEntity> errorsList = new ArrayList<>();
            int linesCount = 0;
            int records = 0;
            String line;
            
            while ((line = reader.readLine()) != null) {
                linesCount++;
                String[] columns = line.split(",");
                if (insertEntity(errorsList, columns, linesCount)) {
                    records++;
                }
            }
        } catch (IOException ex) {

        }
    }

    private boolean insertEntity(ArrayList<ErrorEntity> errorsList, String[] columns, int line) {
        try {
            if (columns[0].equalsIgnoreCase("TIENDA")) {
                handleStores(columns);
            } else if (columns[0].equalsIgnoreCase("TIEMPO")) {
                handleShippingTimes(columns, errorsList);
            } else if (columns[0].equalsIgnoreCase("PRODUCTO")) {
                handleProducts(columns, errorsList);
            } else if (columns[0].equalsIgnoreCase("CLIENTE")) {
                handleCustomers(columns, errorsList);
            } else if (columns[0].equalsIgnoreCase("EMPLEADO")) {
                handleEmployees(columns, errorsList);
            } else if (columns[0].equalsIgnoreCase("PEDIDO")) {
                handleOrders(columns, errorsList);
            } else {
                errorsList.add(new ErrorEntity(line, "Syntax", ""));
            }
            return true;
        } catch (ParametersDoNotMatchException | PhoneNumberSyntaxException | NumberFormatException | DateFormatException ex) {
            errorsList.add(new ErrorEntity(line, "Syntax", ex.getMessage()));
        } catch (EntityNotFoundException ex) {
            errorsList.add(new ErrorEntity(line, "Entity Not Found", ex.getMessage()));
        }
        return false;
    }
    
    private void handleStores(String[] columns) throws ParametersDoNotMatchException, PhoneNumberSyntaxException {
        if (verifyParameters(5, columns.length, "TIENDA")) {
            String name = columns[1].trim();
            String address = columns[2].trim();
            String idStore = columns[3].trim();
            String phone = columns[4].trim();

            if (!isPositiveEightDigitInteger(phone)) {
                throw new PhoneNumberSyntaxException("El número telefonico proporcionado no coincide con un formato válido. Verifica que únicamente se contengan 8 números enteros.");
            }

            Store storeEntity = new Store();
            storeEntity.setName(name);
            storeEntity.setAddress(address);
            storeEntity.setIdStore(idStore);
            storeEntity.setPhone1(phone);

            storeRepository.save(storeEntity);
        }
    }

    private void handleShippingTimes(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, EntityNotFoundException, NumberFormatException {
        if (verifyParameters(4, columns.length, "TIEMPO")) {
            String idStore1 = columns[1].trim();
            String idStore2 = columns[2].trim();
            String time = columns[3].trim();

            existsStores(idStore1, idStore2);
            if (!isPositiveInteger(time)) {
                throw new NumberFormatException("El tiempo entre tiendas debe ser un número entero positivo. Valor obtenido: " + time);
            }

            ShippingTime shippingTimeEntity = new ShippingTime();
            shippingTimeEntity.setIdStore1(idStore1);
            shippingTimeEntity.setIdStore2(idStore2);
            shippingTimeEntity.setTime(Integer.parseInt(time));

            shippingTimeRepository.save(shippingTimeEntity);
        }
    }

    private void handleProducts(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, EntityNotFoundException {
        if (verifyParameters(7, columns.length, "PRODUCTO")) {
            String name = columns[1].trim();
            String manufacturer = columns[2].trim();
            String idProduct = columns[3].trim();
            String stock = columns[4].trim();
            String price = columns[5].trim();
            String idStore = columns[6].trim();

            Optional<Store> verifyStore = storeRepository.findById(idStore);
            if (verifyStore.isEmpty()) {
                throw new EntityNotFoundException(String.format("La tienda con codigo %s no existe", idStore));
            }
            if (!isPositiveInteger(stock)) {
                throw new NumberFormatException(String.format("La cantidad de un producto debe ser un número entero positivo, valor obtenido: %s", stock));
            }
            if (!isValidMoneyFormat(idStore)) {
                throw new NumberFormatException(String.format("El precio de un producto debe ser un número entero positivo con dos decimales, valor obtenido: %s", price));
            }

            Optional<Product> verifyProductEntity = productRepository.findById(idProduct);
            if (verifyProductEntity.isEmpty()) {
                Product productEntity = new Product();
                productEntity.setName(name);
                productEntity.setManufacturer(manufacturer);
                productEntity.setPrice(Double.parseDouble(price));
                productEntity.setIdProduct(idProduct);

                productRepository.save(productEntity);
            }
            ProductStorePK productStorePK = new ProductStorePK(idStore, idProduct);
            ProductStore productStoreEntity = new ProductStore();
            productStoreEntity.setId(productStorePK);
            productStoreEntity.setStock(Integer.parseInt(stock));

            productStoreRepository.save(productStoreEntity);

        }
    }

    private void handleCustomers(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, PhoneNumberSyntaxException {
        if (verifyParameters(5, columns.length, "CLIENTE")) {
            String name = columns[1].trim();
            String nit = columns[2].trim();
            String phone = columns[3].trim();
            String credit = columns[4].trim();

            if (!isPositiveEightDigitInteger(phone)) {
                throw new PhoneNumberSyntaxException("El número telefonico proporcionado no coincide con un formato válido. Verifica que únicamente se contengan 8 números enteros.");
            }
            if (!isValidMoneyFormat(credit)) {
                throw new NumberFormatException(String.format("El crédito de un cliente debe ser un número entero positivo con dos decimales, valor obtenido: %s", credit));
            }

            Customer customerEntity = new Customer();

        }
    }

    private void handleEmployees(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException {
        if (verifyParameters(5, columns.length, "EMPLEADO")) {

        }
    }

    private void handleOrders(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, EntityNotFoundException, DateFormatException {
        if (verifyParameters(10, columns.length, "PEDIDO")) {
            String idOrder = columns[1].trim();
            String idStore1 = columns[2].trim();
            String idStore2 = columns[3].trim();
            String date = columns[4].trim();
            String customer = columns[5].trim();
            String product = columns[6].trim();
            String quantity = columns[7].trim();
            String total = columns[8].trim();
            String advance = columns[9].trim();

            existsStores(idStore1, idStore2);
            if (!isValidDateFormat(date)) {
                throw new DateFormatException(String.format("La fecha del envío debe estar en formato yyyy-mm-dd. Valor encontrado: %s", date));
            }
            if (!isPositiveInteger(quantity)) {
                throw new NumberFormatException(String.format("La cantidad de un producto debe ser un número entero positivo. Valor encontrado: %s", quantity));
            }
            if (!isValidMoneyFormat(total)) {
                throw new NumberFormatException(String.format("El total de una compra debe ser un número entero positivo con dos decimales, valor obtenido: %s", total));
            }
            if (!isValidMoneyFormat(advance)) {
                throw new NumberFormatException(String.format("El anticipo de una compra debe ser un número entero positivo con dos decimales, valor obtenido: %s", advance));
            }

            // lógica restante

        }
    }

    private boolean verifyParameters(int parametersRequired, int parameters, String columnName) throws ParametersDoNotMatchException {
        if (parametersRequired != parameters) {
            throw new ParametersDoNotMatchException(String.format("%s neceita tener %s parametros, solo se encontraron %s", columnName, parametersRequired, parameters));
        }
        return true;
    }

    public static boolean isPositiveEightDigitInteger(String str) {
        return str.matches("^\\d{8}$");
    }

    public static boolean isPositiveInteger(String str) {
        return str.matches("^\\d+$");
    }

    public boolean isValidDateFormat(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(str, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidMoneyFormat(String str) {
        return str.matches("^\\d+(\\.\\d{1,2})?$");
    }

    public void existsStores(String idStore1, String idStore2) throws EntityNotFoundException {
        Optional<Store> store1 = storeRepository.findById(idStore1);
        Optional<Store> store2 = storeRepository.findById(idStore2);
        if (store1.isEmpty() || store2.isEmpty()) {
            List<String> invalidStoreIds = new ArrayList<>();
            if (store1.isEmpty()) {
                invalidStoreIds.add(idStore1);
            }
            if (store2.isEmpty()) {
                invalidStoreIds.add(idStore2);
            }
            throw new EntityNotFoundException("Código(s) de tienda inválido(s): " + invalidStoreIds + " do not exist");
        }
    }

}
