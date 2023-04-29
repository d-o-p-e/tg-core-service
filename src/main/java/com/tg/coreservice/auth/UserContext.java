package com.tg.coreservice.auth;

public class UserContext {
    public static final ThreadLocal<Long> CONTEXT = new ThreadLocal<>();
    public static Long getContext() {
        return UserContext.CONTEXT.get();
    }
}
