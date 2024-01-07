package by.andrey.springcourse.Project2Boot.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationDTO {
    @NotEmpty(message = "The name should not be empty")
    @Size(min = 2, max = 100, message = "The name should be between 2 and 100 characters")
    private String username;

    @NotEmpty(message = "The name should not be empty")
    private String password;

}
