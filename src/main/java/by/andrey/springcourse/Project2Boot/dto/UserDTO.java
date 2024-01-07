package by.andrey.springcourse.Project2Boot.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotEmpty(message = "The name should not be empty")
    @Size(min = 2, max = 100, message = "The name should be between 2 and 100 characters")
    private String username;

    @Min(value = 1910, message = "Year of birth should be more than 1909")
    private int year_of_birth;

    @NotEmpty(message = "The name should not be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
