package com.shockn745;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMain() {
        return "main";
    }

}
