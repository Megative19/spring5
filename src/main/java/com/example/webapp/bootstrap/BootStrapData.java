package com.example.webapp.bootstrap;

import com.example.webapp.domain.Author;
import com.example.webapp.domain.Book;
import com.example.webapp.domain.Publisher;
import com.example.webapp.repository.AuthorRepository;
import com.example.webapp.repository.BookRepository;
import com.example.webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Tech Publishing");
        publisher.setCity("London");
        publisherRepository.save(publisher);
        System.out.println("Publisher count: " + publisherRepository.count());

        Author mega = new Author("Muhammad", "Yusifov");
        Book quantumBook = new Book("Quantum Computing", "7.5");
        mega.getBooks().add(quantumBook);
        quantumBook.getAuthors().add(mega);

        quantumBook.setPublisher(publisher);
        publisher.getBooks().add(quantumBook);

        authorRepository.save(mega);
        bookRepository.save(quantumBook);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());

    }
}
