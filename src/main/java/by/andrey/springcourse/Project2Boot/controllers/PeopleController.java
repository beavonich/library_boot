package by.andrey.springcourse.Project2Boot.controllers;


import by.andrey.springcourse.Project2Boot.models.Person;
import by.andrey.springcourse.Project2Boot.services.BooksService;
import by.andrey.springcourse.Project2Boot.services.PeopleService;
import by.andrey.springcourse.Project2Boot.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PeopleService peopleService;
    private BooksService booksService;
    private PersonValidator personValidator;

    @Autowired
    PeopleController(PeopleService peopleService, BooksService booksService, PersonValidator personValidator){
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String personByFio(Model model) {
        peopleService.personByFio(26, "Bebebe");

        return "people/index";
    }

    @GetMapping("page{page}")
    public String people(Model model, @PathVariable("page") int page) {
        if(page < 1)
            return "redirect:/people/page1";
        model.addAttribute("page", page);
        model.addAttribute("people", peopleService.findAll(PageRequest.of(page - 1, 10)));

        return "people/index";
    }

//    @GetMapping("{page}")
//    public String people(Model model, @PathVariable("page") int page) {
//
//        model.addAttribute("people", peopleService.findAll(page, 2));
//
//        return "people/index";
//    }

    @GetMapping("/{id}")
    public String person(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findById(id));

//        booksService.isBookExpired(booksService.findByOwner(peopleService.findById(id)).get(0));

        if(!booksService.findByOwner(peopleService.findById(id)).isEmpty()){
            model.addAttribute("books", booksService.findByOwner(peopleService.findById(id)));
        }
        else {
            model.addAttribute("books", Collections.emptyList());
        }

        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
//
        return "redirect:/people/page1";
    }
////////////////////////////////////////////////////////////////////
    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findById(id));

        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person updatedPerson, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        updatedPerson.setPersonId(id);
        personValidator.validate(updatedPerson, bindingResult);

        if(bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, updatedPerson);

        return "redirect:/people/page1";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        peopleService.deleteById(id);

        return "redirect:/people/page1";
    }

}
