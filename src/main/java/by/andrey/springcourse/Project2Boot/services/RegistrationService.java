package by.andrey.springcourse.Project2Boot.services;

import by.andrey.springcourse.Project2Boot.models.Userr;
import by.andrey.springcourse.Project2Boot.repositories.UserrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationService {
    private final UserrRepository userrRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserrRepository userrRepository, PasswordEncoder passwordEncoder) {
        this.userrRepository = userrRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Userr user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userrRepository.save(user);
    }
}
