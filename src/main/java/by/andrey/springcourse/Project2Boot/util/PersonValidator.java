package by.andrey.springcourse.Project2Boot.util;


import by.andrey.springcourse.Project2Boot.models.Person;
import by.andrey.springcourse.Project2Boot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private PeopleService peopleService;

    @Autowired
    PersonValidator(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    // В этом методе спрингу надо понять, какой класс относится к валидатору (вызывается автоматически)
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Person.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;


        if(!peopleService.personByFio(person.getPersonId(), person.getFio()).isEmpty()){
            errors.rejectValue("fio", "", "This fio is already taken");
        }

    }
}
