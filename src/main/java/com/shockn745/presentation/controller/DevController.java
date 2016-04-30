package com.shockn745.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping("/dev")
public class DevController {


    @RequestMapping(value = "addPost", method = RequestMethod.PUT)
    public String addPost(Model model, String post) {
//        Blo
        return "maintenance";

    }

    @RequestMapping(value = "/thymeleaf")
    public String greeting(
            @RequestParam(value = "name", required = false, defaultValue = "Giorgos") String name,
            Model model) {

        model.addAttribute("name", name);

        List<String> names = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            names.add(name + " " + i);
        }
        model.addAttribute("names", names);


        return "dev/thymeleaf";
    }
}
