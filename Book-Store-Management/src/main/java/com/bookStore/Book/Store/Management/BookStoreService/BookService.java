package com.bookStore.Book.Store.Management.BookStoreService;

import com.bookStore.Book.Store.Management.BookStoreEntity.Book;
import com.bookStore.Book.Store.Management.BookStoreRepository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private StoreRepository storeRepository;

    /**
     * saving book details
     * @param book
     */
    public void save(Book book){
        storeRepository.save(book);
    }

    /**
     * Get all the books
     * @return
     */
    public List<Book> getAllBooks(){
        return storeRepository.findAll();
    }

    /**
     * Get book by ID
     * @param id
     * @return
     */
    public Book getBookById(int id){
        return storeRepository.findById(id).get(); //get(): return the book object
    }

    /**
     * Delete by ID
     * @param id
     */
    public void deleteById(int id){
        storeRepository.deleteById(id);
    }
}
