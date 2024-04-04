package com.ayd2.intelafbackend.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author waliray
 */
@RestController
@PreAuthorize("hasRole('EMPLOYEE')")
@RequestMapping("/v1/images")
public class ImageProductController {
    private static String imageDirectory = System.getProperty("user.dir") + "/images/";
    
    @PostMapping("/upload")
    public String createProduct(@RequestParam("image") MultipartFile image,@RequestParam("idProduct") String idProduct){
        try {            
            // Asegúrate de que el directorio de carga exista, si no, créalo
            Path uploadPath = Paths.get(imageDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Guarda la imagen en el directorio de carga
            //String filename = idProduct + "_" + image.getOriginalFilename();
            String fileExtension = getFileExtension(image.getOriginalFilename());      
            String filename = idProduct + "." + fileExtension;
            Path filePath = uploadPath.resolve(filename);
            Files.write(filePath, image.getBytes());

            // Aquí puedes realizar cualquier otra lógica, como asociar el nombre del archivo con el ID del producto en tu base de datos
            String pathSaved = imageDirectory + image.getOriginalFilename();
            return pathSaved;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload image";
        }
    }
    
    
    @GetMapping("/all-images-names")
    public List<String> getAllImageNames() {
        List<String> imageNames = new ArrayList<>();
        try {
            // Directorio donde se guardan las imágenes
            Path uploadPath = Paths.get(imageDirectory);

            // Obtén todos los nombres de archivo en el directorio de carga
            Files.list(uploadPath).forEach(path -> {
                if (Files.isRegularFile(path)) {
                    imageNames.add(path.getFileName().toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar el error apropiadamente
        }
        return imageNames;
    }
    
    @GetMapping("/image-by-name/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            // Directorio donde se guardan las imágenes
            Path uploadPath = Paths.get(imageDirectory);
            Path filePath = uploadPath.resolve(imageName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Cambiar según el tipo de imagen que estés almacenando
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar el error apropiadamente
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/image-by-idProduct/{idProduct}")
    public ResponseEntity<Resource> getImageByProductId(@PathVariable String idProduct) {
        try {
            // Directorio donde se guardan las imágenes
            Path uploadPath = Paths.get(imageDirectory);

            // Construye el nombre del archivo utilizando el ID del producto y la extensión del archivo
            String[] extensions = {"jpg", "jpeg", "png", "gif"}; // Puedes agregar más extensiones de archivo si es necesario
            for (String extension : extensions) {
                Path filePath = uploadPath.resolve(idProduct + "." + extension);
                Resource resource = new UrlResource(filePath.toUri());

                if (resource.exists() && resource.isReadable()) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG) // Cambiar según el tipo de imagen que estés almacenando
                            .body(resource);
                }
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar el error apropiadamente
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    
    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
    
    
    
}
