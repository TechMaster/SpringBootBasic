package vn.techmaster.pollapi.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 80)
    @Email
    private String email;
    @NotBlank
    @Size(max=100)
    private String password;
}
