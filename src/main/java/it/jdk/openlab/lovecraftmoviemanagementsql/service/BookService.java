package it.jdk.openlab.lovecraftmoviemanagementsql.service;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.entities.BookEntity;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Book;
import it.jdk.openlab.lovecraftmoviemanagementsql.repository.book.BookSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.group.BookValidationGroup.CreateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.group.BookValidationGroup.DeleteValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.group.BookValidationGroup.UpdateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.model.BookValidationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookSpringDataRepository bookRepository;
    private final BookValidationModel bookValidation;

    @Autowired
    public BookService(BookSpringDataRepository bookRepository, BookValidationModel bookValidation) {
        this.bookRepository = bookRepository;
        this.bookValidation = bookValidation;
    }

    @Transactional(readOnly = false)
    public Book insertBook(Book book) throws SuperException {
        this.bookValidation.validate(book, "One or more arguments are invalid", CreateValidationGroup.class);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPlot(book.getPlot());
        bookEntity.setImage(book.getImage());
        bookEntity.setPublished(book.getPublished());

        this.bookRepository.save(bookEntity);

        book.setId(bookEntity.getId());
        book.setVersion(0);

        return book;
    }

    @Transactional(readOnly = false)
    public Book updateBook(Book book) throws SuperException {
        this.bookValidation.validate(book, "One or more arguments are invalid", UpdateValidationGroup.class);

        Optional<BookEntity> bookEntity = this.bookRepository.findById(book.getId());

        BookEntity bookEntityObj = bookEntity.orElseThrow(
                () -> new RuntimeException("Book with id " + book.getId() + " not found"));
        if(bookEntityObj.getVersion() != book.getVersion()) {
            throw new OptimisticLockingFailureException("Entity version and DTO version do not match");
        }
        bookEntityObj.setTitle(book.getTitle());
        bookEntityObj.setPlot(book.getPlot());
        bookEntityObj.setImage(book.getImage());
        bookEntityObj.setPublished(book.getPublished());

        book.setVersion(book.getVersion() + 1);

        return book;
    }

    @Transactional(readOnly = false)
    public void deleteBook(Book book) throws SuperException {
        this.bookValidation.validate(book, "A book with id " + book.getId() + " was not found", DeleteValidationGroup.class);

        Optional<BookEntity> bookEntity = this.bookRepository.findById(book.getId());

        BookEntity bookEntityObj = bookEntity.orElseThrow(
                () -> new RuntimeException("Book with id " + book.getId() + " not found"));
        if(bookEntityObj.getVersion() != book.getVersion()) {
            throw new OptimisticLockingFailureException("Entity version and DTO version do not match");
        }
        this.bookRepository.delete(bookEntityObj);
    }

//    public PageModel<Book> findAllBooks(int page, int numOfElements) {
//        Page<BookEntity> entityPage = bookRepository.findAll(PageRequest.of(page -1, numOfElements));
//        PageModel<Book> bookPage = new PageModel<>();
//        bookPage.setTotalPages(entityPage.getTotalPages());
//        bookPage.setPage(entityPage.getNumber() + 1);
//        bookPage.setPageSize(entityPage.getSize());
//
//        List<Book> books = new ArrayList<>();
//
//        for(BookEntity entity : entityPage.getContent()) {
//            books.add(new Book(entity.getId(),
//                    entity.getTitle(), entity.getPlot(),
//                    entity.getImage(), entity.getPublished(), entity.getVersion()));
//        }
//
//        bookPage.setElements(books);
//        return bookPage;
//    }

    public List<Book> findAllBooks() {
        List<BookEntity> bookEntities = this.bookRepository.findAll();
        List<Book> books = new ArrayList<>();

        for(BookEntity bookEntity : bookEntities) {
            books.add(new Book(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getPlot(), bookEntity.getImage(),
                    bookEntity.getPublished(), bookEntity.getVersion()));
        }
        return books;
    }

    public Optional<Book> findBookById(Integer id) {
        Optional<BookEntity> bookEntity = this.bookRepository.findById(id);
        BookEntity bookEntityObj = bookEntity.orElseThrow(
                () -> new RuntimeException("A book with id " + id + " was not found"));
        return Optional.of(new Book(bookEntityObj.getId(), bookEntityObj.getTitle(),
                bookEntityObj.getPlot(), bookEntityObj.getImage(), bookEntityObj.getPublished(),
                bookEntityObj.getVersion()));
    }
}
