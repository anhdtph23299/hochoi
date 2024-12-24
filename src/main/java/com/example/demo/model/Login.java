package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Login implements Serializable {

    @Email(message = "{login.username.email}")
    @NotBlank(message = "{login.username.notnull}")
    private String userName;

    @NotBlank(message = "{login.password.notnull}")
    private String passWord;
}

