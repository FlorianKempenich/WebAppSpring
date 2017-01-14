package com.shockn745.network;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * {@code RestTemplate} supporting Basic Authentication.
 * <p>
 * Simply extract the Basic Authentication feature from {@code TestRestTemplate}.
 * <p>
 */
public class RestTemplateWithAuth extends RestTemplate {

    public RestTemplateWithAuth(String username, String password) {
        addAuthentication(username, password);
    }

    private void addAuthentication(String username, String password) {
        if (username == null) {
            return;
        }

        List<ClientHttpRequestInterceptor> interceptors = Collections.singletonList(
                new BasicAuthorizationInterceptor(username, password)
        );

        setRequestFactory(new InterceptingClientHttpRequestFactory(getRequestFactory(), interceptors));
    }

    private static class BasicAuthorizationInterceptor implements
            ClientHttpRequestInterceptor {

        private final String username;

        private final String password;

        public BasicAuthorizationInterceptor(String username, String password) {
            this.username = username;
            this.password = (password == null ? "" : password);
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            byte[] token = encodeToken(this.username + ":" + this.password);
            request.getHeaders().add("Authorization", "Basic " + new String(token));
            return execution.execute(request, body);
        }

        private byte[] encodeToken(String toEncode) {
            byte[] toEncodeBytes = toEncode.getBytes();
            return Base64.getEncoder().encode(toEncodeBytes);
        }

    }

}