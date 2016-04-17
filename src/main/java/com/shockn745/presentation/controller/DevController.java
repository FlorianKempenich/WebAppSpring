package com.shockn745.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
