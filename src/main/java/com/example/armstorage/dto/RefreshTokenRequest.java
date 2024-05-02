package com.example.armstorage.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshTokenRequest {

    @NotNull(message = "should be not null")
    private String oldRefreshToken;
}
