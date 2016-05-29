package com.shockn745.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @RequestMapping(value = "sitemap")
    public String addPost(Model model, String post) {
//        Blo
        return "analytics/sitemap";

    }

}
