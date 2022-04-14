package icu.api.dto;

import javax.validation.constraints.Size;

public class RegisterParam {
    @Size(min = 5, message = "用户名太短")
    @Size(max = 15, message = "用户名太长")
    private String username;
    private String password;

    public RegisterParam(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
