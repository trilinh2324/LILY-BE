package com.example.lily.Service;

import com.example.lily.Model.User;
import com.example.lily.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  implements IUserService {

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void remove(long id) {
    }

    @Autowired
    private UserRepository userRepository;

// admin
    public User loginUser(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password) && user.getRole() == 0) {
            return user;
        }
        return null; // Đăng nhập không thành công
    }
// user
public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
}

}
