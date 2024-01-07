package by.andrey.springcourse.Project2Boot.services;

import by.andrey.springcourse.Project2Boot.models.Person;
import by.andrey.springcourse.Project2Boot.repositories.BooksRepository;
import by.andrey.springcourse.Project2Boot.repositories.PeopleRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll(PageRequest pageRequest){
        return peopleRepository.findAll(pageRequest).getContent();
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        return peopleRepository.findById(id).get();
    }

    public void save(Person person){
        peopleRepository.save(person);
    }

    public void update(int id, Person updatedPerson){
        updatedPerson.setPersonId(id);
        peopleRepository.save(updatedPerson);
    }

    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }

    public List<Person> personByFio(int id, String fio){
        Session session = entityManager.unwrap(Session.class);



        return session.createQuery("SELECT p FROM Person p WHERE p.fio = :fio AND p.personId != :id", Person.class)
                .setParameter("fio", fio)
                .setParameter("id", id)
                .getResultList();
    }

    //Исправить позже
    public List<Person> personByBookId(int bookId){

        Session session = entityManager.unwrap(Session.class);

        return session.createQuery(
                "SELECT p FROM Person p left join fetch p.books b where b.bookId = :bookId", Person.class)
                .setParameter("bookId", bookId)
                .getResultList();

//        return Collections.singletonList(peopleRepository.findById(booksRepository.findById(bookId).get().getOwner().
//                getPersonId()).get());
    }
}
