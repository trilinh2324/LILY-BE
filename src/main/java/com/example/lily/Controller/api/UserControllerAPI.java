package com.example.lily.Controller.api;

import com.example.lily.Model.User;
import com.example.lily.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000") // Cấu hình CORS
public class UserControllerAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        User user = userService.findByUserName(userName);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Người dùng không tồn tại"));
        }

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Mật khẩu không hợp lệ"));
        }

        if (user.getRole() != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Quản trị viên không được phép đăng nhập ở đây"));
        }

        return ResponseEntity.ok(new LoginResponse("Đăng nhập thành công", user.getId(), user.getUserName(), user.getRole()));
    }

    public static class LoginRequest {
        private String userName;
        private String password;

        // Getters and Setters
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String message;
        private Long userId;
        private String userName;
        private int role;

        public LoginResponse(String message, Long userId, String userName, int role) {
            this.message = message;
            this.userId = userId;
            this.userName = userName;
            this.role = role;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
