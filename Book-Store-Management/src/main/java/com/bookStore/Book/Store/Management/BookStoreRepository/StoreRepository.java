package com.bookStore.Book.Store.Management.BookStoreRepository;

import com.bookStore.Book.Store.Management.BookStoreEntity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Book, Integer> {

}
