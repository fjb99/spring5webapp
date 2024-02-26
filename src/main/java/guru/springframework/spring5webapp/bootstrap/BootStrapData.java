package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("fjona publisher");
        publisher.setCity("pertersburg");
        publisher.setState("LA");

        publisherRepository.save(publisher);
        System.out.println("######:: Started in Bootstrap, FJONA");
        //System.out.println("Number of publisher: " + publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("libri i ktij tipit erik", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod","Jonson");
        Book J2ee = new Book("j2ee","1324567");
        rod.getBooks().add(J2ee);
        J2ee.getAuthors().add(rod);

        J2ee.setPublisher(publisher);
        publisher.getBooks().add(J2ee);

        authorRepository.save(rod);
        bookRepository.save(J2ee);
        publisherRepository.save(publisher);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publisher: " + publisherRepository.count());
        System.out.println("Publisher Number of books : " + publisher.getBooks().size());
    }
}
