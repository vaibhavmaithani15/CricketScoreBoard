package com.scoreboard.match.controller.request;

import lombok.Data;

@Data
public class UserRequest {
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public int enabled;
    public String role;

}
