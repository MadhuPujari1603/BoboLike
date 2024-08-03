package com.ot.BoboLike.dao;

import com.ot.BoboLike.dto.User;
import com.ot.BoboLike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Page<User> findAll(int offset, int pageSize, String field) {
        return userRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));
    }

    public List<User> findByNameContaining(String letter) {
        return userRepository.findByNameContaining(letter);
    }

    public User findByOtp(String otp) {
        return userRepository.findByOtp(otp);
    }
}
