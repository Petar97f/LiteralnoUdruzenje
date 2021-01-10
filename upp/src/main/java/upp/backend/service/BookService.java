package upp.backend.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upp.backend.dto.BookDTO;
import upp.backend.dto.GenreDTO;
import upp.backend.model.Book;
import upp.backend.model.Genre;
import upp.backend.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> findall() {
        return bookRepository.findAll();
    }
    public Book convertFromDTO(BookDTO bookDTO){
        Book book = new Book();
        if(book.getId()!=null) {
            book.setId(bookDTO.getId());
        }
        book.setName(bookDTO.getName());
        return book;
    }
    public BookDTO convertToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setEditors(book.getEditors());
        bookDTO.setAuthorId(book.getAuthorId());
        bookDTO.setLectors(book.getLectors());
        bookDTO.setNumOfPages(book.getNumOfPages());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPublisherId(book.getPublisherId());
        bookDTO.setYearOfIssue(book.getYearOfIssue());
        bookDTO.setGenre(book.getGenre());
        return bookDTO;
    }
}
