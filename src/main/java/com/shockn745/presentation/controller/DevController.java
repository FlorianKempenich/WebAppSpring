package com.shockn745.presentation.controller;

import com.shockn745.domain.application.driving.GetWhatMessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    GetWhatMessageUseCase getWhatMessageUseCase;


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

    @RequestMapping(value = "/paper")
    public String paper() {
        return "yabe/paper";
    }

    @RequestMapping(value = "/maintenance")
    public String showMain(Model model) {
        model.addAttribute("email", "shockn745@gmail.com");
        model.addAttribute("title", "Hello");
        return "dev/maintenance";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String showMap() {
        return "dev/map";
    }

    @RequestMapping(value = "/kitten", method = RequestMethod.GET)
    public String showKitten() {
        return "dev/kitten";
    }

    @RequestMapping(value = "/what", method = RequestMethod.GET)
    public String what(
            @RequestParam(value = "what", required = false, defaultValue = "Smoke ?") String input,
            Model model) {
        String toDisplay = getWhatMessageUseCase.execute(input);
        model.addAttribute("what", toDisplay);
        return "dev/what";
    }

    @RequestMapping(value = "/youtube")
    public String embedVideo(Model model) {
        return "dev/youtube";
    }


    @RequestMapping(value = "/newsletter", method = RequestMethod.GET)
    public String newsletter(Model model) {
        return "dev/newsletter";
    }

    @RequestMapping(value = "/newsletter", method = RequestMethod.POST)
    public String newsletter_post(Model model, @RequestParam(value="firstname", required=false)String firstName) {
        System.out.println(firstName);
        return "dev/newsletter";
    }
}
