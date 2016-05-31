package com.shockn745.presentation.controller;

import com.shockn745.domain.application.driving.NewsletterUseCase;
import com.shockn745.presentation.model.newsletter.NewsletterRequest;
import com.shockn745.presentation.model.newsletter.NewsletterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by Florian on 31/05/16.
 */
@Controller
@RequestMapping("/newsletter")
public class NewsletterController {

    @Autowired
    NewsletterUseCase newsletterUseCase;

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    @ResponseBody
    public NewsletterResponse subscribe(@Valid @RequestBody NewsletterRequest newsletterRequest) {

        System.out.println("Backend: " + newsletterRequest.getEmail());
        newsletterUseCase.addToNewsletter(newsletterRequest.getEmail());

        NewsletterResponse response = new NewsletterResponse();
        response.setStatus(true);
        return response;
    }
}
