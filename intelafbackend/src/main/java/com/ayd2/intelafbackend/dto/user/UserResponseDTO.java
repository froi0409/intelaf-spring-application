package com.ayd2.intelafbackend.dto.user;


import com.ayd2.intelafbackend.entities.users.User;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class UserResponseDTO {

    Long idUser;
    String nit;
    String name;
    String phone;
    String dpi;
    String email;
    String address;
    String username;

    public UserResponseDTO(User user){
        this.idUser = user.getIdUser();
        this.nit = user.getNit();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.dpi = user.getDpi();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.username = user.getUsername();
    }
}
