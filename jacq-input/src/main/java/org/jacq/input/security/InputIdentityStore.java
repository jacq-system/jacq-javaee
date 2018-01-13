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
package org.jacq.input.security;

import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.rest.UserService;
import org.jacq.input.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/faces/pages/login.xhtml",
                errorPage = "", // DRAFT API - must be set to empty for now
                useForwardToLogin = false
        )
)
@ApplicationScoped
@DeclareRoles({"authenticated"})
public class InputIdentityStore implements IdentityStore {

    protected UserService userService;

    @PostConstruct
    public void init() {
        this.userService = ServicesUtil.getUserService();
    }

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        UserResult user = this.userService.authenticate(usernamePasswordCredential.getCaller(), usernamePasswordCredential.getPasswordAsString());

        if (user != null) {
            return new CredentialValidationResult(user.getUsername(), new HashSet<String>(Arrays.asList("authenticated")));
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}