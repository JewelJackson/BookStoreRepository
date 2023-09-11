package com.bookStore.Book.Store.Management.BookStoreController;

import com.bookStore.Book.Store.Management.BookStoreEntity.Book;
import com.bookStore.Book.Store.Management.BookStoreEntity.MyBookList;
import com.bookStore.Book.Store.Management.BookStoreService.BookService;
import com.bookStore.Book.Store.Management.BookStoreService.MyBookListService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Controller
public class StoreController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookListService myBookListService;


    @GetMapping(value = "/")
    public String home() {
        return "home";
    }


    @GetMapping(value = "/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    /**
     * Displays available books
     * @return
     */
    @GetMapping(value = "/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = bookService.getAllBooks();
        /*ModelAndView m = new ModelAndView();
        m.setViewName("bookList");
        m.addObject("book",list);
        return m;*/
        return new ModelAndView("bookList", "book", list);
    }

    /**
     * Adding book details
     * @param book
     * @return
     */
    @PostMapping(value = "/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    /**
     * Displays My books
     * @param model
     * @return
     */
    @GetMapping(value = "/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBook";
    }

    /**
     * Adding a particular book to My books
     * @param id
     * @return
     */
    @GetMapping(value = "/my-list/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b = bookService.getBookById(id);
        MyBookList myBook = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        myBookListService.saveMyBooks(myBook);
        return "redirect:/my_books";
    }

    /**
     * Editing a book
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {  //Model: to send data from controller to view
        Book b = bookService.getBookById(id);
        model.addAttribute("book", b);
        return "bookEdit";
    }

    /**
     * Book Deletion
     * @param id
     * @return
     */
    @GetMapping(value = "/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/available_books";
    }

    /**
     * Downloading available book list
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/download")
    public void downloadBooks(HttpServletResponse response) throws IOException {
        List<Book> data = bookService.getAllBooks();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=books.csv");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dt = formatter.format(date);

        String[] csvHeaderList = {"LIST OF ALL BOOKS"};
        String[] csvHeaderDate = {"Date",dt};
        String[] csvHeaderLib = {"Librarian","Jewel"};

        String[] csvHeader = {"ID","Name", "Author", "Price"};
        String[] nameMapping = {"id","name", "author", "price"};

        csvWriter.writeHeader(csvHeaderList);
        csvWriter.writeHeader(csvHeaderDate);
        csvWriter.writeHeader(csvHeaderLib);
        csvWriter.writeHeader(csvHeader);

        for (Book b : data) {
            csvWriter.write(b, nameMapping);
        }

        csvWriter.close();
    }

    /**
     * Downloading My book list
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/download/my_books")
    public void downloadMyBooks(HttpServletResponse response) throws IOException {
        List<MyBookList> data =myBookListService.getAllMyBooks();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=mybooks.csv");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dt = formatter.format(date);

        String[] csvHeaderList = {"LIST OF ALL BOOKS"};
        String[] csvHeaderDate = {"Date",dt};
        String[] csvHeaderLib = {"Librarian","Jewel"};

        String[] csvHeader = {"ID","Name", "Author", "Price"};
        String[] nameMapping = {"id","name", "author", "price"};

        csvWriter.writeHeader(csvHeaderList);
        csvWriter.writeHeader(csvHeaderDate);
        csvWriter.writeHeader(csvHeaderLib);
        csvWriter.writeHeader(csvHeader);

        for (MyBookList b : data) {
            csvWriter.write(b, nameMapping);
        }

        csvWriter.close();
    }


    /*@GetMapping(value = "/b")
    public String moveBack(){
        return "redirect:/available_books";
    }*/
}








