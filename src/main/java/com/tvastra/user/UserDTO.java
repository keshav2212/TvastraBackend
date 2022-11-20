package com.tvastra.user;

import javax.validation.constraints.NotNull;

public class UserDTO {


    @NotNull(message = "username required")
    private String username;

    private String name;
    @NotNull(message = "password required")
    private String password;
    private String email;
    @NotNull(message = "role is required")
    private Role role;

    public UserDTO(String username, String name, String password, String email, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = Role.valueOf(role.toUpperCase());
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
