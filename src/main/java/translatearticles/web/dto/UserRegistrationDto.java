package translatearticles.web.dto;

import lombok.Data;
import translatearticles.constraint.annotation.FieldMatch;
import translatearticles.constraint.annotation.ValidEmail;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must be matched"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must be matched")
})
@Data
public class UserRegistrationDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @ValidEmail
    @NotEmpty
    private String email;

    @ValidEmail
    @NotEmpty
    private String confirmEmail;

    @NotEmpty
    private String phoneNumber;

    @AssertTrue
    private Boolean terms;

}
