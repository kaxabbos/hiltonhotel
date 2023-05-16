package com.hiltonhotel.controller;

import com.hiltonhotel.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatisticsCont extends Attributes {
    @GetMapping
    String stats(Model model){
        AddAttributesStats(model);
        return "statistics";
    }
}
