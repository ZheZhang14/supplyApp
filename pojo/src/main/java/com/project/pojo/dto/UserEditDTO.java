package com.project.pojo.dto;


import com.project.pojo.entities.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDTO {
    private Integer id;
    private String username;
    private UserType userRole;
    private String password;
    private String email;
    private String contactName;
    private String phone;
    private String address;
    private LocalDateTime dateCreated;
    private String imagePath;
    private MultipartFile image;
}
