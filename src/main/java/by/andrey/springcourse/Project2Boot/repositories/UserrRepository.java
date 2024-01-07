package by.andrey.springcourse.Project2Boot.repositories;

import by.andrey.springcourse.Project2Boot.models.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserrRepository extends JpaRepository<Userr, Integer> {
    Optional<Userr> findByUsername(String username);
}
