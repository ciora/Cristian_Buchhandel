package at.htl.business;

import at.htl.model.Book;
import at.htl.model.Publisher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class BeanValidationTest {
    private static Validator validator;
    String summary = "This Field should contain a short summary about the book. Sadly it is not available at the moment. " +
            "In the near future this problem will be solved " +
            "so that you can enjoy the service of this bookshop to the fullest.";


    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void T011_autorNullValue(){
        Book book = new Book("The Adventure of The Miniprojekt","111111","Me"
                ,11.11,null,153,"German",summary,"Fantasy");
        Set<ConstraintViolation<Book>> constraintViolationSet = validator.validate(book);

        book.setAutor(null);
        constraintViolationSet = validator.validate(book);

        assertThat(book.getAutor(), is(nullValue()));
    }

    @Test
    public void T021_checkISBNLength(){
        Book book = new Book("The Adventure of The Miniprojekt","111111","Me"
                ,11.11,null,153,"German",summary,"Fantasy");
        Set<ConstraintViolation<Book>> constraintViolationSet = validator.validate(book);

        book.setISBN("12352");
        constraintViolationSet = validator.validate(book);

        assertThat(5, is(book.getISBN().length()));
    }

    @Test
    public void T031_NullInBook(){
        Book book = new Book(null,"111111","Me"
                ,11.11,null,153,"German",summary,"Fantasy");
        Set<ConstraintViolation<Book>> violations = this.validator.validate(book);
        assertThat(violations.isEmpty(), Matchers.is(false));
    }

    @Test
    public void T031_WithoutNullInBook(){
        Publisher publisher = new Publisher("Publisher","PublisherStreet 3","5555","PublisherCity");

        Book book = new Book("The Adventure of The Miniprojekt","111111","Me"
                ,11.11,publisher,153,"German",summary,"Fantasy");
        book.setPublisher(publisher);
        Set<ConstraintViolation<Book>> violations = this.validator.validate(book);
        assertThat(violations.isEmpty(), Matchers.is(true));
    }
}