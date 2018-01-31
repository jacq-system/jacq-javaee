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

import javax.security.enterprise.CallerPrincipal;
import org.jacq.common.model.rest.UserResult;

/**
 *
 * @author wkoller
 */
public class InputCallerPrincipal extends CallerPrincipal {

    protected UserResult user;

    public InputCallerPrincipal(String name, UserResult user) {
        super(name);

        this.user = user;
    }

    public UserResult getUser() {
        return user;
    }

}