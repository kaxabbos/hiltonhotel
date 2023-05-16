package com.hiltonhotel.controller;

import com.hiltonhotel.controller.main.Attributes;
import com.hiltonhotel.model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/archive")
public class ReservationsCont extends Attributes {
    @GetMapping()
    String archive(Model model) {
        AddAttributesArchive(model);
        return "reservations";
    }

    @PostMapping("/add/{id}")
    String archiveAdd(@RequestParam int quantity, @RequestParam String date, @PathVariable Long id) {
        cartRepo.save(new Cart(quantity, date, getUser(), roomRepo.getReferenceById(id)));
        return "redirect:/room/all";
    }
}
