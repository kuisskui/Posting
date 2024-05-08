package com.example.Posting.dto;

import com.example.Posting.validation.ConfirmPassword;
import com.example.Posting.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@ConfirmPassword.List({
    @ConfirmPassword(
        field = "password",
        fieldMatch = "confirmPassword",
        message = "Passwords do not match!"
    )
}
)
@Data
public class SignupRequest {

    @NotBlank
    @Size(min=4, message = "Username must have at least 4 characters")
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    @ValidPassword
    private String confirmPassword;

    @NotBlank
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name can only contain letters")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name can only contain letters")
    private String lastName;
}
