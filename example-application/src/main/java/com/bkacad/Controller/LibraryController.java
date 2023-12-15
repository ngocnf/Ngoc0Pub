package com.bkacad.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bkacad.Exceptions.DatabaseActionException;
import com.bkacad.Models.DAO.LibraryDAO;
import com.bkacad.Models.Entitites.Book;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Books")

public class LibraryController {
    
    @Autowired
    private LibraryDAO LibraryDAO;  

    // Add, Edit thì mở form mứi điền info. Add, Edit, Delete xong quay lại màn List
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String ShowAddForm(Model model) throws DatabaseActionException{
        model.addAttribute("Book", new Book());
        return "add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("Book") Book book) throws DatabaseActionException{
        LibraryDAO.CreateBook(book);
        return "redirect:/Books/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String ListBooks(Model model) throws DatabaseActionException{
        List<Book> Books = LibraryDAO.FindAll();
        model.addAttribute("Books", Books);
        return "list";
    }


    @RequestMapping(value = "/list/{author}", method = RequestMethod.GET)
    public String ListBooksByAuthor(Model model, String author) throws DatabaseActionException{
        Optional<Book> Books = LibraryDAO.FindBookByAuthor(author);
        model.addAttribute("Books", Books);
        return "list";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String ViewBookByID(@PathVariable int id, Model model) throws DatabaseActionException{
        Optional<Book> Book = LibraryDAO.FindBookByID(id);
        model.addAttribute("Books", Book.orElse(null));
        return "view";
    }


    @RequestMapping(value = "/view/{title}", method = RequestMethod.GET)
    public String ViewBookByTitle(@PathVariable String title, Model model) throws DatabaseActionException{
        Optional<Book> Book = LibraryDAO.FindBookByTitle(title);
        model.addAttribute("Books", Book.orElse(null));
        return "view";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String ShowEditForm(@PathVariable int id, Model model) throws DatabaseActionException{
        Optional<Book> ExistingBook = LibraryDAO.FindBookByID(id);
        model.addAttribute("Books", ExistingBook.orElse(null));
        return "edit";
    }

    // @ReqestParam trích xuất data từ chuỗi truy vấn, form param & tập tin từ Request (túc lấy thông tin của param trên URL, kiểu query string)
    @RequestMapping (value = "/edit/{id}", method = RequestMethod.POST)
    public String addBook(@PathVariable int id, @RequestParam String title, @RequestParam String author, @RequestParam int yearOfPublic) throws DatabaseActionException{
        Optional<Book> ExistingBook = LibraryDAO.FindBookByID(id);
        if (ExistingBook.isPresent()) {
            LibraryDAO.UpdateBook(id, title, author, yearOfPublic);
        } 
            return "redirect:/Books/list";
    }

    
    @RequestMapping (value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable int id) throws DatabaseActionException{
        LibraryDAO.DeleteBook(id);
        return "redirect:/Books/list";
    }
}