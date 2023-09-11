package com.bookStore.Book.Store.Management.BookStoreService;

import com.bookStore.Book.Store.Management.BookStoreEntity.Book;
import com.bookStore.Book.Store.Management.BookStoreRepository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Book bookToSave = new Book();
        bookToSave.setName("The Last Leaf");
        bookToSave.setAuthor("O Henry");
        bookToSave.setPrice("600");

        bookService.save(bookToSave);

        verify(storeRepository, times(1)).save(bookToSave);
    }

    @Test
    void testGetAllBook() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Last Leaf", "O Henry", "600"));
        books.add(new Book(2, "Harry Potter", "J.K Rowling", "1500"));

        when(storeRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("The Last Leaf", result.get(0).getName());
        assertEquals("O Henry", result.get(0).getAuthor());
        assertEquals("600", result.get(0).getPrice());

        assertEquals("Harry Potter", result.get(1).getName());
        assertEquals("J.K Rowling", result.get(1).getAuthor());
        assertEquals("1500", result.get(1).getPrice());
    }

    @Test
    void testGetBookById() {
        int bookId = 1;
        Book expectedBook = new Book(bookId, "The Last Leaf", "O Henry", "600");

        when(storeRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        Book result = bookService.getBookById(bookId);

        assertEquals(expectedBook.getId(), result.getId());
        assertEquals(expectedBook.getName(), result.getName());
        assertEquals(expectedBook.getAuthor(), result.getAuthor());
        assertEquals(expectedBook.getPrice(), result.getPrice());
    }


    @Test
    void testDeleteById() {
        int bookId = 1;

        bookService.deleteById(bookId);
        verify(storeRepository, times(1)).deleteById(bookId);
    }
}
