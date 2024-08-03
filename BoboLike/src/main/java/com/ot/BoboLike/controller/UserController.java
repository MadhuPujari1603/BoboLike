package com.ot.BoboLike.controller;

import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.User;
import com.ot.BoboLike.dto.request.AdminRegister;
import com.ot.BoboLike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseStructure<User>> update(@RequestParam long id,
                                                          @RequestBody AdminRegister admin) {
        return userService.update(id, admin);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<User>> delete(@RequestParam long id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<ResponseStructure<User>> findById(@RequestParam long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<Page<User>>> findAll(@RequestParam(defaultValue = "0") int offset,
                                                                 @RequestParam(defaultValue = "5") int pageSize,
                                                                 @RequestParam(defaultValue = "id") String field) {
        return userService.findAll(offset, pageSize, field);
    }

    @GetMapping(value = "/findByPhone")
    public ResponseEntity<ResponseStructure<User>> findByPhone(@RequestParam String phone) {
        return userService.findByPhone(phone);
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<ResponseStructure<User>> findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @PatchMapping(value = "/updateNewPassword")
    public ResponseEntity<ResponseStructure<User>> updateNewPassword(@RequestParam String password, @RequestParam String otp) {
        return userService.updateNewPassword(password, otp);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseStructure<User>> login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    @PostMapping(value = "/forgotPassword")
    public ResponseEntity<ResponseStructure<String>> forgotPassword(@RequestParam String email) {
        return userService.forgotPassword(email);
    }

    @GetMapping(value = "/validateOTP")
    public ResponseEntity<ResponseStructure<User>> validateOTP(@RequestParam String otp) throws Exception {
        return userService.validateOtp(otp);
    }
}
