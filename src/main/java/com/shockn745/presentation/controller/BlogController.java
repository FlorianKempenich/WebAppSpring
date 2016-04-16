package com.shockn745.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping(value = "/yabe")
public class BlogController {

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return showTestMessage(model);
    }



    private String showTestMessage(Model model) {
        model.addAttribute("what", "This is a test");
        return "dev/what";
    }
}
