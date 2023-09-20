/*
 * Copyright 2018 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.input.controller;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
import org.jacq.common.manager.JacqConfig;
import org.jacq.input.ApplicationManager;
import org.jacq.input.SessionManager;

/**
 * Controller for handling logins of users
 *
 * @author wkoller
 */
@RequestScoped
@Named
public class LoginController {

    @Inject
    protected SecurityContext securityContext;

    @Inject
    protected SessionManager sessionController;

    @Inject
    protected ApplicationManager applicationManager;

    protected String username;
    protected String password;

    @PostConstruct
    public void init() {
        ResourceBundle messages = ResourceBundle.getBundle("org.jacq.messages", sessionController.getLanguage());
        if (applicationManager.getJacqPortalConfig().getLong(JacqConfig.DATABASE_PRODUCTIVSYSTEM) != 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, messages.getString("warning"), messages.getString("testsystem")));
        }
    }

    public String login() {
        Credential credential = new UsernamePasswordCredential(username, password);

        AuthenticationStatus as = securityContext.authenticate(getRequest(), getResponse(), AuthenticationParameters.withParams().credential(credential));

        if (as.equals(AuthenticationStatus.SEND_CONTINUE)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now.
            FacesContext.getCurrentInstance().responseComplete();

            return null;
        } else if (as.equals(AuthenticationStatus.SUCCESS)) {
            // redirect to manage page
            return "livingplant/manage.xhtml?faces-redirect=true";
        }

        // by default send error message and stay on page
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "Authentication failed", null));

        return null;
    }

    /**
     * Logout the user and redirect to login page
     *
     * @return
     * @throws ServletException
     */
    public String logout() throws ServletException {
        this.getRequest().logout();
        this.getRequest().getSession().invalidate();

        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext()
                .getExternalContext()
                .getRequest();
    }

    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext()
                .getExternalContext()
                .getResponse();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

}
