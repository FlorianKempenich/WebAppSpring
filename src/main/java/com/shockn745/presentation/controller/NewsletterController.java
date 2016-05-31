package com.shockn745.presentation.controller;

import com.shockn745.presentation.model.newsletter.NewsletterRequest;
import com.shockn745.presentation.model.newsletter.NewsletterResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Florian on 31/05/16.
 */
@Controller
@RequestMapping("/newsletter")
public class NewsletterController {

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    @ResponseBody
    public NewsletterResponse subscribe(@Valid @RequestBody NewsletterRequest newsletterRequest) {

        System.out.println("Backend: " + newsletterRequest.getEmail());

        NewsletterResponse response = new NewsletterResponse();
        response.setEmail("Frontend: " + newsletterRequest.getEmail());
        response.setStatus(true);

        return response;
    }
}
