package com.camunda.example.oauth2.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogoutController {


    private final String revokeURL = "https://dev-XXXX.okta.com/oauth2/default/v1/logout?id_token_hint=";



    @GetMapping(path = "**/logout", produces = "application/json")
    public ModelAndView revokeAccess(ProcessEngine processEngine) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenValue = (String) ((OidcUser) authentication.getPrincipal()).getIdToken().getTokenValue();
        return new ModelAndView("redirect:" + revokeURL + tokenValue);
    }

}