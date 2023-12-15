package com.bkacad.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkacad.Exceptions.DatabaseActionException;
import com.bkacad.Models.DAO.LibraryDAO;
import com.bkacad.Models.Entitites.Book;

@Controller
@RequestMapping("/api")

public class LibraryAPIController {
    @Autowired
    private LibraryDAO LibraryDAO;

    //Find all Books 
    @RequestMapping(value = "/Books", method = RequestMethod.GET)
    public ResponseEntity<Object> FindAll() throws DatabaseActionException{
        List<Book> Books = LibraryDAO.FindAll();
        return new ResponseEntity<Object>(Books, HttpStatus.OK);
    } 


    // Find book by ID 
    // @PathVariable xử lý template variable trong URI Mapping mà dễ nhất là xác thực Pri.Key
    @RequestMapping(value = "/Books/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> FindBookByID(@PathVariable int id) throws DatabaseActionException{
        Optional<Book> Book = LibraryDAO.FindBookByID(id);
        if (Book.isEmpty()) {
            return new ResponseEntity<Object>(Book, HttpStatus.NOT_FOUND);
        } 
        else {
            return new ResponseEntity<Object>(Book.get(), HttpStatus.OK);
        }   
    }


    // Find book by Author
    @RequestMapping(value = "/Books/{Author}", method = RequestMethod.GET)
    public ResponseEntity<Object> FindBookByAuthor(@PathVariable String author) throws DatabaseActionException{
        Optional<Book> Book = LibraryDAO.FindBookByAuthor(author);
        if (Book.isEmpty()) {
            return new ResponseEntity<Object>(Book, HttpStatus.NOT_FOUND);
        } 
        else {
            return new ResponseEntity<Object>(Book.get(), HttpStatus.OK);
        }   
    }


    // Find book by Title
    @RequestMapping(value = "/Books/{Title}", method = RequestMethod.GET)
    public ResponseEntity<Object> FindBookByTitle(@PathVariable String title) throws DatabaseActionException{
        Optional<Book> Book = LibraryDAO.FindBookByTitle(title);
        if (Book.isEmpty()) {
            return new ResponseEntity<Object>(Book, HttpStatus.NOT_FOUND);
        } 
        else {
            return new ResponseEntity<Object>(Book.get(), HttpStatus.OK);
        }   
    }


    @RequestMapping(value = "/Books", method = RequestMethod.POST)
    public ResponseEntity<String> CreateBook(@RequestBody Book Book) throws DatabaseActionException{
        LibraryDAO.CreateBook(Book);
        return new ResponseEntity<String>("Created!", HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/Books/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> UpdateBook(@PathVariable int id, @RequestParam String title, @RequestParam String author, @RequestParam int yearOfPublic) throws DatabaseActionException{
        Optional<Book> ExistingBook = LibraryDAO.FindBookByID(id);
        if (ExistingBook.isPresent()) {
            LibraryDAO.UpdateBook(id, title, author, yearOfPublic);
        }
        return new ResponseEntity<String>("Updated", HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/Books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> DeleteBook(@PathVariable int id) throws DatabaseActionException{
        LibraryDAO.DeleteBook(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.NO_CONTENT);
    }
}
