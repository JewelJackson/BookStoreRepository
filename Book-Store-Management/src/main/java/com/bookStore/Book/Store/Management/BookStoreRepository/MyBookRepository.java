package com.bookStore.Book.Store.Management.BookStoreRepository;

import com.bookStore.Book.Store.Management.BookStoreEntity.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepository extends JpaRepository<MyBookList, Integer> {
}
