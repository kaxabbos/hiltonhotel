package com.hiltonhotel.controller.main;

import com.hiltonhotel.model.User;
import com.hiltonhotel.repo.CartRepo;
import com.hiltonhotel.repo.RoomRepo;
import com.hiltonhotel.repo.StatRepo;
import com.hiltonhotel.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Main {

    @Autowired
    protected UserRepo userRepo;
    @Autowired
    protected CartRepo cartRepo;
    @Autowired
    protected RoomRepo roomRepo;
    @Autowired
    protected StatRepo statRepo;

    @Value("${upload.img}")
    protected String uploadImg;

    protected User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return userRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }
}