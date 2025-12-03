package org.jacq.service.report.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Captures the incoming Authorization header at the start of request handling
 * and stores it in a ThreadLocal holder for client-side propagation.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class IncomingAuthorizationCaptureFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString("Authorization");
        AuthorizationHeaderHolder.set(authorization);
    }
}
