package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.files.DataFileResponseDTO;
import com.ayd2.intelafbackend.entities.ErrorEntity;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.orders.OrderHasProduct;
import com.ayd2.intelafbackend.entities.orders.OrderHasProductPK;
import com.ayd2.intelafbackend.entities.orders.PaymentOrder;
import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.entities.products.ProductStore;
import com.ayd2.intelafbackend.entities.products.ProductStorePK;
import com.ayd2.intelafbackend.entities.store.ShippingTime;
import com.ayd2.intelafbackend.entities.store.ShippingTimeId;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.entities.users.Employee;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.enums.user.Role;
import com.ayd2.intelafbackend.exceptions.*;
import com.ayd2.intelafbackend.repositories.*;
import com.ayd2.intelafbackend.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataFileServiceImpl implements DataFileService {

    private StoreRepository storeRepository;
    private ShippingTimeRepository shippingTimeRepository;
    private ProductRepository productRepository;
    private ProductStoreRepository productStoreRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private OrderRepository orderRepository;
    private PaymentOrderRepository paymentOrderRepository;
    private OrderHasProductRepository orderHasProductRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataFileServiceImpl(StoreRepository storeRepository, ShippingTimeRepository shippingTimeRepository, ProductRepository productRepository, ProductStoreRepository productStoreRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, OrderRepository orderRepository, PaymentOrderRepository paymentOrderRepository, OrderHasProductRepository orderHasProductRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.storeRepository = storeRepository;
        this.shippingTimeRepository = shippingTimeRepository;
        this.productRepository = productRepository;
        this.productStoreRepository = productStoreRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
        this.paymentOrderRepository = paymentOrderRepository;
        this.orderHasProductRepository = orderHasProductRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<String> verifySystemData() {
        List<String> itemsToInsert = new ArrayList<>();
        if (storeRepository.count() <= 0) {
            itemsToInsert.add("TIENDA");
        }
        if (productRepository.count() <= 0) {
            itemsToInsert.add("PRODUCTO");
        }
        if (userRepository.count() <= 1) {
            itemsToInsert.add("USUARIO(S)");
        }
        return itemsToInsert;
    }

    @Override
    public DataFileResponseDTO handleDataFile(MultipartFile file) throws UploadDataFileException {
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
            return new DataFileResponseDTO(records, errorsList);
        } catch (IOException ex) {
            throw new UploadDataFileException("Error to upload datafile");
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
                if (!columns[0].trim().equals("")) {
                    errorsList.add(new ErrorEntity(line, "Syntax", "Entidad desconocida"));
                }
            }
            return true;
        } catch (ParametersDoNotMatchException | PhoneNumberSyntaxException | NumberFormatException | DateFormatException ex) {
            errorsList.add(new ErrorEntity(line, "Syntax", ex.getMessage()));
        } catch (EntityNotFoundException ex) {
            errorsList.add(new ErrorEntity(line, "Entity Not Found", ex.getMessage()));
        } catch (DuplicatedEntityException ex) {
            errorsList.add(new ErrorEntity(line, "Duplicated Entity", ex.getMessage()));
        } catch (Exception ex) {
            System.err.println("Error al leer entidad: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
    private void handleStores(String[] columns) throws ParametersDoNotMatchException, PhoneNumberSyntaxException, DuplicatedEntityException {
        if (verifyParameters(5, columns.length, "TIENDA")) {
            String name = columns[1].trim();
            String address = columns[2].trim();
            String idStore = columns[3].trim();
            String phone = columns[4].trim();

            Optional<Store> storeOptional = storeRepository.findById(idStore);
            if (storeOptional.isPresent()) {
                throw new DuplicatedEntityException(String.format("La tienda con id %s ya existe en el sistema", idStore));
            }

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

    private void handleShippingTimes(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, EntityNotFoundException, NumberFormatException, DuplicatedEntityException {
        if (verifyParameters(4, columns.length, "TIEMPO")) {
            String idStore1 = columns[1].trim();
            String idStore2 = columns[2].trim();
            String time = columns[3].trim();

            existsStores(idStore1, idStore2);
            Optional<ShippingTime> shippingTimeOptional = shippingTimeRepository.findById(new ShippingTimeId(idStore1, idStore2));
            if(shippingTimeOptional.isPresent()) {
                throw new DuplicatedEntityException(String.format("El tiempo entre la tienda %s y %s ya existe en el sistema", idStore1, idStore2));
            }

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
            if (!isValidMoneyFormat(price)) {
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

    private void handleCustomers(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, PhoneNumberSyntaxException, DuplicatedEntityException {
        if (verifyParameters(5, columns.length, "CLIENTE")) {
            String name = columns[1].trim();
            String nit = columns[2].trim();
            String phone = columns[3].trim();
            String credit = columns[4].trim();

            Optional<Customer> customerOptional = customerRepository.findByNit(nit);
            if (customerOptional.isPresent()) {
                throw new DuplicatedEntityException(String.format("El cliente con NIT %s ya se encuentra en el sistema", nit));
            }

            if (!isPositiveEightDigitInteger(phone)) {
                throw new PhoneNumberSyntaxException("El número telefonico proporcionado no coincide con un formato válido. Verifica que únicamente se contengan 8 números enteros.");
            }
            if (!isValidMoneyFormat(credit)) {
                throw new NumberFormatException(String.format("El crédito de un cliente debe ser un número entero positivo con dos decimales, valor obtenido: %s", credit));
            }

            User userEntity = new User();
            userEntity.setName(name);
            userEntity.setNit(nit);
            userEntity.setPhone(phone);
            userEntity.setUsername(nit);
            userEntity.setPassword(passwordEncoder.encode(nit));
            userEntity.setRole(Role.CUSTOMER);
            userEntity = userRepository.save(userEntity);

            Customer customer = new Customer();
            customer.setCredit(BigDecimal.valueOf(Double.parseDouble(credit)));
            customer.setUser(userEntity);
            customerRepository.save(customer);
        }
    }

    private void handleEmployees(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, PhoneNumberSyntaxException, DuplicatedEntityException {
        if (verifyParameters(5, columns.length, "EMPLEADO")) {
            String name = columns[1].trim();
            String idEmployee = columns[2].trim();
            String phone = columns[3].trim();
            String dpi = columns[4].trim();

            Optional<Employee> employeeOptionalId = employeeRepository.findById(Long.parseLong(idEmployee));
            Optional<User> userOptional = userRepository.findFirstByDpi(dpi);

            if (employeeOptionalId.isPresent()) {
                throw new DuplicatedEntityException(String.format("Ya existe un empleado con código %s en el sistema", idEmployee));
            }
            if (userOptional.isPresent()) {
                throw new DuplicatedEntityException(String.format("Ya existe un usuario con el dpi %s en el sistema", dpi));
            }

            if (!isPositiveEightDigitInteger(phone)) {
                throw new PhoneNumberSyntaxException("El número telefonico proporcionado no coincide con un formato válido. Verifica que únicamente se contengan 8 números enteros.");
            }
            if (!isPositiveInteger(dpi)) {
                throw new NumberFormatException("El dpi proporcionado no coincide con un formato válido. Verifica que únicamente se contengan números enteros positivos");
            }

            User userEntity = new User();
            userEntity.setName(name);
            userEntity.setUsername(idEmployee);
            userEntity.setPassword(passwordEncoder.encode(dpi));
            userEntity.setPhone(phone);
            userEntity.setDpi(dpi);
            userEntity.setRole(Role.EMPLOYEE);
            userEntity = userRepository.save(userEntity);

            Employee employeeEntity = new Employee();
            employeeEntity.setRole("employee");
            employeeEntity.setIdUser(userEntity.getIdUser());
            employeeRepository.save(employeeEntity);

        }
    }


    private void handleOrders(String[] columns, ArrayList<ErrorEntity> errors) throws ParametersDoNotMatchException, EntityNotFoundException, DateFormatException, DuplicatedEntityException {
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


            Optional<Order> order = orderRepository.findById(Long.parseLong(idOrder));

            if (order.isPresent()) {
                throw new DuplicatedEntityException(String.format("La orden %s ya existe", idOrder));
            }

            existsStores(idStore1, idStore2);

            Product productEntity = productRepository.findById(product)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("El producto con código %s no existe en el sistema", product)));
            ShippingTime shippingTimeEntity = shippingTimeRepository.findById(new ShippingTimeId(idStore1, idStore2))
                    .orElseThrow(() -> new EntityNotFoundException(String.format("No existe un tiempo de envío entre la tienda %s y %s", idStore1, idStore2)));
            Customer customerEntity = customerRepository.findByNit(customer)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("El usuario con NIT %s no existe en el sistema", customer)));
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

            int days = shippingTimeEntity.getTime();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    .toFormatter();

            LocalDateTime departureDate = LocalDateTime.parse(date + " 00:00:00", formatter);
            LocalDateTime entryDate = LocalDateTime.parse(addDaysToDate(date, days) + " 00:00:00", formatter);

            System.out.println("xd");

            Order orderEntity = new Order();
            orderEntity.setIdOrder(Long.parseLong(idOrder));
            orderEntity.setIdStoreShipping(idStore1);
            orderEntity.setIdStoreReceive(idStore2);
            orderEntity.setIdCustomer(customerEntity.getUserIdUser());
            orderEntity.setDateDeparture(departureDate);
            orderEntity.setDateEntry(entryDate);
            orderEntity.setTotal(BigDecimal.valueOf(Double.parseDouble(total)));
            orderEntity.setStatus("Route");

            System.out.println("xd");

            orderRepository.save(orderEntity);

            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setOrder(orderEntity);
            paymentOrder.setType("efectivo");
            paymentOrder.setAmount(Double.parseDouble(advance));
            paymentOrderRepository.save(paymentOrder);

            OrderHasProductPK orderHasProductPK = new OrderHasProductPK();
            orderHasProductPK.setOrderId(Long.parseLong(idOrder));
            orderHasProductPK.setProductId(product);

            OrderHasProduct orderHasProductEntity = new OrderHasProduct();
            orderHasProductEntity.setQuantiy(Integer.parseInt(quantity));
            orderHasProductEntity.setOrderHasProductPK(orderHasProductPK);
            orderHasProductEntity.setProduct(productEntity);
            orderHasProductEntity.setOrder(orderEntity);
            orderHasProductRepository.save(orderHasProductEntity);

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

    private static String addDaysToDate(String date, int daysToAdd) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .toFormatter();
        LocalDate initialDate = LocalDate.parse(date, formatter);
        LocalDate finalDate = initialDate.plusDays(daysToAdd);

        return finalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
