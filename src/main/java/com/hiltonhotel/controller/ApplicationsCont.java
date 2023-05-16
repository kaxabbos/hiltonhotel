package com.hiltonhotel.controller;

import com.hiltonhotel.controller.main.Attributes;
import com.hiltonhotel.model.Cart;
import com.hiltonhotel.model.Stat;
import com.hiltonhotel.model.enums.CartStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apps")
public class ApplicationsCont extends Attributes {
    @GetMapping()
    String cart(Model model) {
        AddAttributesApps(model);
        return "applications";
    }

    @GetMapping("/conf/{id}")
    String Confirmation(@PathVariable Long id) {
        Cart cart = cartRepo.getReferenceById(id);

        Stat stat = cart.getRoom().getStat();

        stat.setQuantity(stat.getQuantity() + cart.getQuantity());
        stat.setPrice(stat.getPrice() + (cart.getQuantity() * stat.getRoom().getPrice()));
        statRepo.save(stat);

        cart.setStatus(CartStatus.APPROVED);
        cartRepo.save(cart);
        return "redirect:/apps";
    }

    @GetMapping("/unconf/{id}")
    String UnConfirmation(@PathVariable Long id) {
        Cart cart = cartRepo.getReferenceById(id);
        cart.setStatus(CartStatus.REFUSED);
        cartRepo.save(cart);
        return "redirect:/apps";
    }


}
