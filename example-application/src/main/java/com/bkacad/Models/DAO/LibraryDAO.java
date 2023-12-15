package com.bkacad.Models.DAO;

import java.util.List;
import java.util.Optional;

import com.bkacad.Exceptions.DatabaseActionException;
import com.bkacad.Models.Entitites.Book;

public interface LibraryDAO {
    void CreateTable() throws DatabaseActionException;
    void DeleteTable() throws DatabaseActionException;
    void CreateBook(final Book book) throws DatabaseActionException;
    void DeleteBook(int id) throws DatabaseActionException;
    void UpdateBook(int id, String title, String author, int yearOfPublic) throws DatabaseActionException;
    Optional<Book> FindBookByID(int id) throws DatabaseActionException;
    Optional<Book> FindBookByAuthor(String author) throws DatabaseActionException;
    Optional<Book> FindBookByTitle(String title) throws DatabaseActionException;
    List<Book> FindAll() throws DatabaseActionException;
}