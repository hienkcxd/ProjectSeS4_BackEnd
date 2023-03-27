package com.example.BachEnd_Ses4.DTO.SystemDTO;

import lombok.Data;

@Data
public class UserDetailDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Long deviceQuantity;
    private Long StoreQuantity;
    private String curPassword;
    private String newPassword;
}
