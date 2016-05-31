package com.shockn745.network;

import com.shockn745.domain.application.driven.NewsletterRepository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kempenich Florian
 */
public class SpringNewsletterRepositoryImpl implements NewsletterRepository{

    private RestTemplate restTemplate = new RestTemplateWithAuth("anystring", ApiKeys.MAILCHIMP);

    @Override
    public void save(String emailAddress) {
        MailchimpSubscriber subscriber = new MailchimpSubscriber();
        subscriber.setEmail_address(emailAddress);
        subscriber.setStatus("pending");
        try {
            restTemplate.postForObject(ApiKeys.MAILCHIMP_URL, subscriber, Object.class); //Not interested in the response
        } catch (RestClientException e) {
            //Do nothing. Adding email failed: totally fine!
        }

    }
}
