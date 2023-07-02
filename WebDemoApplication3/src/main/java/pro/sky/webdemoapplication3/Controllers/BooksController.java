package pro.sky.webdemoapplication3.Controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.webdemoapplication3.Model.Book;
import pro.sky.webdemoapplication3.Services.BookService;

@RestController
@RequestMapping("books")

public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public Book getBookInfo(@PathVariable Long id) {
        return bookService.findBook(id);
    }

    @PostMapping
    public Book createBook(Book book) {
        return bookService.createBook(book);
    }
}
