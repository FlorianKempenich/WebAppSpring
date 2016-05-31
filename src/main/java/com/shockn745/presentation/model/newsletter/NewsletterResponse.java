package com.shockn745.presentation.model.newsletter;

/**
 * Simple response class for the Ajax 'subscribe to newsletter' request.
 * Might not even be necessary, but for now does the job.
 * (Simple string response would send failed to Ajax)
 */
public class NewsletterResponse {

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
