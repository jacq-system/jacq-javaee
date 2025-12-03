package org.jacq.service.report.security;

/**
 * Simple ThreadLocal holder for propagating the incoming Authorization header
 * from server-side request filters to client-side request filters within the
 * same request/thread.
 */
public final class AuthorizationHeaderHolder {

    private static final ThreadLocal<String> AUTHORIZATION = new ThreadLocal<>();

    private AuthorizationHeaderHolder() {
        // utility class
    }

    public static void set(String value) {
        if (value == null || value.isEmpty()) {
            AUTHORIZATION.remove();
        } else {
            AUTHORIZATION.set(value);
        }
    }

    public static String get() {
        return AUTHORIZATION.get();
    }

    public static void clear() {
        AUTHORIZATION.remove();
    }
}
