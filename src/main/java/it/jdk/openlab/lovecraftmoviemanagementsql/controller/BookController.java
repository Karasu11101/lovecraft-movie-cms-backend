package it.jdk.openlab.lovecraftmoviemanagementsql.controller;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.aspects.ToLog;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Book;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Movie;
import it.jdk.openlab.lovecraftmoviemanagementsql.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static it.jdk.openlab.lovecraftmoviemanagementsql.aspects.LoggingAspect.LOGGER_LEVEL;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ToLog(level = LOGGER_LEVEL)
    @PostMapping(value = "/newBook",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> insertBook(@RequestBody Book book) throws SuperException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.insertBook(book));
    }

    @ToLog(level = LOGGER_LEVEL)
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody Book book) throws SuperException {
        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.updateBook(book));
    }

    @ToLog(level = LOGGER_LEVEL)
    @DeleteMapping(value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> deleteBook(@RequestBody Book book) throws SuperException {
        this.bookService.deleteBook(book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ToLog(level = LOGGER_LEVEL)
    @GetMapping(value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.findAllBooks());
    }

//    @ToLog(level = LOGGER_LEVEL)
//    @GetMapping(value = "/list/{start}/{max}",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Book>> getBooks(@PathVariable Integer start, @PathVariable Integer max) {
//        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.findAllBooks());
//    }

    @ToLog(level = LOGGER_LEVEL)
    @GetMapping(value = "details/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookDetails(@PathVariable Integer id) {
        Optional<Book> book = this.bookService.findBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book.get());
//        if(book.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(book.get());
//        }
    }
}
