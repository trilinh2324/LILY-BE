package com.example.lily.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName; // tên khách hàng
    private String password;//mật khẩu
    private String email;
    private long phone_number;//số đt
    private String gender; // giới tính
    private String address;// địa chỉ
    private int Role;// phân quyền
}
