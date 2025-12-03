/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.report.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.codec.binary.Base64;
import org.jacq.common.security.JacqCallerPrincipal;
import java.security.Principal;

/**
 *
 * @author wkoller
 */
@Provider
public class PassthroughClientRequestFilter implements ClientRequestFilter {

    @Context
    protected SecurityContext securityContext;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        // First try to propagate an Authorization header captured from the incoming request
        String propagated = AuthorizationHeaderHolder.get();
        if (propagated != null && !propagated.isEmpty()) {
            List<Object> authorization = new ArrayList<>();
            authorization.add(propagated);
            requestContext.getHeaders().put("Authorization", authorization);
            return;
        }

        // Fallback to securityContext (may not be available in client filters)
        try {
            Principal principal = (securityContext != null) ? securityContext.getUserPrincipal() : null;
            if (principal instanceof JacqCallerPrincipal) {
                String authHeader = ((JacqCallerPrincipal) principal).getAuthorizationHeader();
                if (authHeader != null && !authHeader.isEmpty()) {
                    List<Object> authorization = new ArrayList<>();
                    authorization.add(authHeader);
                    requestContext.getHeaders().put("Authorization", authorization);
                }
            }
        } catch (Exception ignore) {
            // No-op: skip adding header when context/principal is unavailable
        }
    }

}
