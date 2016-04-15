package com.shockn745.presentation.controller;

import com.shockn745.domain.application.driving.GetWhatMessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final GetWhatMessageUseCase getWhatMessageUseCase;

    @Autowired
    public MainController(GetWhatMessageUseCase getWhatMessageUseCase) {
        this.getWhatMessageUseCase = getWhatMessageUseCase;
    }

    @RequestMapping(value = "/")
    public String showMain(Model model) {
        model.addAttribute("email", "shockn745@gmail.com");
        model.addAttribute("title", "Hello");
        return "maintenance";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String showMap() {
        return "map";
    }

    @RequestMapping(value = "/kitten", method = RequestMethod.GET)
    public String showKitten() {
        return "kitten";
    }

    @RequestMapping(value = "/what", method = RequestMethod.GET)
    public String what(
            @RequestParam(value = "what", required = false, defaultValue = "Smoke ?") String input,
            Model model) {
        String toDisplay = getWhatMessageUseCase.execute(input);
        model.addAttribute("what", toDisplay);
        return "what";
    }

    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public String showTemplate() {
        return "blog-template";
    }

}
