package upp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import upp.backend.dto.BookDTO;
import upp.backend.dto.GenreDTO;
import upp.backend.model.Book;
import upp.backend.model.Genre;
import upp.backend.service.BookService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/getBooks")
    public ArrayList<BookDTO> getBooks() {
        List<Book> books = bookService.findall();
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        for(Book b: books) {
            bookDTOS.add(bookService.convertToDTO(b));
        }
        return bookDTOS;
    }
}
