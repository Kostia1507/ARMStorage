package com.example.armstorage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserLoginRequest {

    @NotBlank(message = "should be not blank")
    private String login;

    @NotBlank(message = "should be not blank")
    private String password;

}
