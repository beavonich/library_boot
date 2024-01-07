package by.andrey.springcourse.Project2Boot.services;

import by.andrey.springcourse.Project2Boot.models.Userr;
import by.andrey.springcourse.Project2Boot.repositories.UserrRepository;
import by.andrey.springcourse.Project2Boot.security.UserrDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserrDetailsService implements UserDetailsService {
    private final UserrRepository userrRepository;

    @Autowired
    public UserrDetailsService(UserrRepository userrRepository) {
        this.userrRepository = userrRepository;
    }

    @Override
    public UserrDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Userr> person = userrRepository.findByUsername(username);

        if(person.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserrDetails(person.get());
    }
}
