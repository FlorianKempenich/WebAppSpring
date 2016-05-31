package com.shockn745.presentation.model.newsletter;

/**
 * Created by Florian on 31/05/16.
 */
public class NewsletterResponse {

    private boolean status;
    private String email;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
