package com.example.orderservice.external.interceptor;

import com.example.orderservice.exception.CustomException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class OAuthRequestInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public void apply(RequestTemplate template) {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("internal-client")
                .principal("internal")
                .build();

        OAuth2AuthorizedClient authorizedClient = oAuth2AuthorizedClientManager.authorize(authorizeRequest);

        if (authorizedClient != null) {
            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            template.header("Authorization", "Bearer " + accessToken);
        } else {
            throw new CustomException("Failed to authorize the client",
                    "UNAUTHORIZED_REQUEST",
                    403);
        }
    }
}
