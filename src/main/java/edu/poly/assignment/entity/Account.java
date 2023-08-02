package edu.poly.assignment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

    @Id
    @NotBlank(message = "Vui lòng nhập tài khoản")
    private String username;

    @NotBlank(message = "vui lòng nhập mật khẩu")
    @Size(min = 6, max = 12, message = "Mật khẩu phải từ 6 đến 12 ký tự")
    private String password;

    @NotBlank(message = "vui lòng nhập tên của bạn")
    private String fullname;

    @NotBlank(message = "vui lòng nhập email")
    @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)*(\\.[a-zA-Z]{2,})$", message = "Email không đúng định dạng")
    private String email;

    private String photo;

    @NotBlank(message = "vui lòng nhập địa chỉ")
    private String address;

    @NotBlank(message = "vui lòng nhập số điện thoại")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải là 10 số")
    private String phone;

    private Boolean sex = true;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday = new Date();

    private Boolean activated = true;
    private Boolean admin = false;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}