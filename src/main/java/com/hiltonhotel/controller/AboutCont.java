package com.hiltonhotel.controller;

import com.hiltonhotel.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutCont extends Attributes {
    @GetMapping("/about")
    String about(Model model) {
        AddAttributes(model);
        return "about";
    }

    @GetMapping("/map")
    String map(Model model) {
        AddAttributes(model);
        return "map";
    }
}
