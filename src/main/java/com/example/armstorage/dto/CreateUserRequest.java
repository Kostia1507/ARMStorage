package com.example.armstorage.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CreateUserRequest {

    @NotNull(message = "should not be null")
    private String login;

    @NotNull(message = "should not be null")
    private String password;

    @NotNull(message = "should not be null")
    private String fullname;

    @NotNull(message = "should not be null")
    private String role;
}

