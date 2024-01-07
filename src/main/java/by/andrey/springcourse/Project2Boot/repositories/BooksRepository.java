package by.andrey.springcourse.Project2Boot.repositories;


import by.andrey.springcourse.Project2Boot.models.Book;
import by.andrey.springcourse.Project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(Person person);

    List<Book> findByBookName(String bookName);

}
