package com.syberry.server.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 3, max = 40)
    private String password;
}
