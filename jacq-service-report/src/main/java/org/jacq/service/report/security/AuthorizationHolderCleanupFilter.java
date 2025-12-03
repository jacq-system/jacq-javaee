package org.jacq.service.report.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Ensures the ThreadLocal authorization holder is cleared after the request is
 * processed to avoid leaking values between requests on reused threads.
 */
@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class AuthorizationHolderCleanupFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        AuthorizationHeaderHolder.clear();
    }
}
