package by.andrey.springcourse.Project2Boot.services;


import by.andrey.springcourse.Project2Boot.models.Book;
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
import java.util.List;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }
    public List<Book> findAll(PageRequest pageRequest){
        return booksRepository.findAll(pageRequest).getContent();
    }

    public List<Book> findAll(){ return booksRepository.findAll();}
    public void save(Book book) {
        booksRepository.save(book);
    }

    public void update(int id, Book updatedBook){
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    public Book findById(int id){ return booksRepository.findById(id).get();}

    public List<Book> findByOwner(Person person){
        return booksRepository.findByOwner(person);
    }

//    public List<Book> findByOwnerAndCheck(Person person){
//        Map<Book, Boolean> bookMap = new HashMap<>();
//
//        for(Book book : booksRepository.findByOwner(person)) {
//            bookMap.put(book, );
//        }
//    }

    public void freeBook(int bookId){
//        Session session = entityManager.unwrap(Session.class);
//
//        session.createQuery("update Book b set owner = null where b.bookId = :bookId")
//                .setParameter("bookId", bookId);
        booksRepository.findById(bookId).get().setOwner(null);
//        peopleRepository.findById(booksRepository.findById(bookId).get().getOwner().getPersonId()).get().getBooks().
//                remove(booksRepository.findById(bookId).get());
    }

    public void assignBook(int bookId, int personId) {
        booksRepository.findById(bookId).get().setOwner(peopleRepository.findById(personId).get());
        peopleRepository.findById(personId).get().getBooks().add(booksRepository.findById(bookId).get());
    }

    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }


    public List<Book> findByCharacters(String bookName) {
        Session session = entityManager.unwrap(Session.class);

        return session.createQuery("select b from Book b where b.bookName like :bookName", Book.class)
                .setParameter("bookName", bookName + "%")
                .getResultList();
    }

//    public Boolean isBookExpired(Book book){
//        Session session = entityManager.unwrap(Session.class);
//
//        Date currentDate = (Date) session.createQuery("SELECT current_date");
//
//        Date dateTaken = book.getDateTaken();
//
//
//
//        return Boolean.FALSE;
//    }
}
