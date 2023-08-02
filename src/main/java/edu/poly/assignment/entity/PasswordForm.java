package edu.poly.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {
    private String username;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
