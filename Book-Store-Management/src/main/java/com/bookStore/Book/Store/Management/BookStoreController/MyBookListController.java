package com.bookStore.Book.Store.Management.BookStoreController;

import com.bookStore.Book.Store.Management.BookStoreService.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyBookListController {
    @Autowired
    private MyBookListService myBookListSer;

    @GetMapping(value = "/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id){
        myBookListSer.deleteById(id);
        return "redirect:/my_books";
    }
}
