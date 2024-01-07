package by.andrey.springcourse.Project2Boot.util;

import by.andrey.springcourse.Project2Boot.models.Userr;
import by.andrey.springcourse.Project2Boot.services.UserrDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserrValidator implements Validator {
    private final UserrDetailsService userrDetailsService;

    @Autowired
    public UserrValidator(UserrDetailsService userrDetailsService) {
        this.userrDetailsService = userrDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Userr.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Userr user = (Userr) o;

        try {
            userrDetailsService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username", "", "The person with such username already exists");
    }
}
