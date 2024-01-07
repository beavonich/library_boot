package by.andrey.springcourse.Project2Boot.controllers;

import by.andrey.springcourse.Project2Boot.models.Book;
import by.andrey.springcourse.Project2Boot.models.Person;
import by.andrey.springcourse.Project2Boot.services.BooksService;
import by.andrey.springcourse.Project2Boot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookController {
    BooksService booksService;
    PeopleService peopleService;

    @Autowired
    BookController(BooksService booksService, PeopleService peopleService){
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping("/page{page}")
    public String books(Model model, @PathVariable("page") int page) {
        if(page < 1)
            return "redirect:/books/page1";

        model.addAttribute("page", page);
        model.addAttribute("books", booksService.findAll(PageRequest
                .of(page - 1, 10, Sort.by("year"))));


        return "books/index";
    }

    @GetMapping("/{id}")
    public String book(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("people", peopleService.findAll());
        if(!peopleService.personByBookId(id).isEmpty()){
            model.addAttribute("person", peopleService.personByBookId(id).get(0));
        }else{
            model.addAttribute("person", new Person());
        }


        return "books/book";
    }

////////////////////////////////////////////////////////////////////
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {

        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "books/new";


        booksService.save(book);

        return "redirect:/books/page1";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book updatedBook, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        updatedBook.setBookId(id);
        if(bindingResult.hasErrors())
            return "books/edit";


        booksService.update(id, updatedBook);
        return "redirect:/books/page1";
    }

    @PatchMapping("/freebook/{bookId}")
    public String freeBook(@PathVariable("bookId") int bookId){
        booksService.freeBook(bookId);

        return "redirect:/books/" + bookId;
    }

    //==================================================================
    @PatchMapping("/assignbook/{bookId}")
    public String assignBook(@PathVariable("bookId") int bookId,
                             @ModelAttribute("person") Person person){

        booksService.assignBook(bookId, person.getPersonId());

        return "redirect:/books/" + bookId;
    }


    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        booksService.deleteById(id);

        return "redirect:/books/page1";
    }



    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "bookName", required = false) String bookName, Model model){
        model.addAttribute("books", booksService.findByCharacters(bookName));

        return "books/search";
    }


}
