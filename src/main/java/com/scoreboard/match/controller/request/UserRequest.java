package com.scoreboard.match.controller.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {
    @NonNull
    public String userName;
    @NonNull
    public String password;
    @NonNull
    public String firstName;
    public String lastName;
    public int enabled;
    @NonNull
    public String role;
}
