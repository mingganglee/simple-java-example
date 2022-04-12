package com.authserver.domain.constant;

public enum MessageConstant {
    LOGIN_SUCCESS("登录成功!"),

    USERNAME_PASSWORD_ERROR("用户名或密码错误!"),

    ACCOUNT_DISABLED("该账户已被禁用，请联系管理员!"),

    ACCOUNT_EXPIRED("该账号已过期，请联系管理员!"),

    ACCOUNT_LOCKED("该账号已被锁定，请联系管理员!"),

    CREDENTIALS_EXPIRED("该账户的登录凭证已过期，请重新登录!"),

    PERMISSION_DENIED("没有访问权限，请联系管理员!");

    private String msg;

    MessageConstant(String msg) {
        this.msg = msg;
    }

}
