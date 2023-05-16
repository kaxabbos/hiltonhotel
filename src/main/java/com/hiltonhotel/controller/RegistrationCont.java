package com.hiltonhotel.controller;

import com.hiltonhotel.controller.main.Attributes;
import com.hiltonhotel.model.User;
import com.hiltonhotel.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegistrationCont extends Attributes {
    @GetMapping
    String reg() {
        return "registration";
    }

    @PostMapping
    String userAdd(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String password_repeat) {
        if (!password.equals(password_repeat)) {
            model.addAttribute("message", "Некорректный ввод паролей!");
            return "registration";
        }

        if (userRepo.findAll().isEmpty()) {
            userRepo.save(new User(username, password, Role.ADMIN));
            return "redirect:/login";
        }

        if (userRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует!");
            return "registration";
        }

        userRepo.save(new User(username, password, Role.BUYER));

        return "redirect:/login";
    }
}
