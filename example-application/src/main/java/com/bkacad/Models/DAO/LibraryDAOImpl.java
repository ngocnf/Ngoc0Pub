package com.bkacad.Models.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bkacad.Exceptions.DatabaseActionException;
import com.bkacad.Models.Entitites.Book;

import lombok.Getter;
import lombok.Setter;

@Repository
@Getter
@Setter

public class LibraryDAOImpl implements LibraryDAO {

    @Autowired
    private JdbcTemplate JdbcTemplate;
    
    @Override
    public void CreateTable() throws DatabaseActionException{
    // "Books" is the table name
        try {
            JdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Books (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(255)," +
                "author VARCHAR(255)," +
                "yearOfPublic INT)"
            );
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }


    @Override
    public void DeleteTable() throws DatabaseActionException{
        try {
            JdbcTemplate.execute("DROP TABLE IF EXISTS Books");
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }
    

    @Override
    public void CreateBook(Book book) throws DatabaseActionException{
        String sql = "INSERT INTO Books(id, tile, author, yearOfPublic)";
        try {
            JdbcTemplate.update(sql, book.GetTitle(), book.GetAuthor(), book.GetyearOfPublic());
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }


    @Override
    public void DeleteBook(int id) throws DatabaseActionException{
        String sql = "DELETE FROM Books WHERE id =?";
        try {
            JdbcTemplate.update(sql, id);
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }


    @Override
    public void UpdateBook(int id, String newTitle, String newAuthor, int newYearOfPublic) throws DatabaseActionException{
        String sql = "UPDATE Books SET title = ?, author = ?, yearOfPublic = ? WHERE id = ?";
        try {
            JdbcTemplate.update(sql, newTitle, newAuthor, newYearOfPublic);
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }


    // Cách dùng RowMapper ?
    @Override
    public Optional<Book> FindBookByID(int id) throws DatabaseActionException{
        String sql = "SELECT * FROM Books WHERE id = ?";
        try {
            return JdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) -> Optional.of(MapResultSetToBook(resultSet))); 
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }


    @Override
    public Optional<Book> FindBookByAuthor(String author) throws DatabaseActionException{
        String sql = "SELECT * FROM Books WHERE author = ?";
        try {
            return JdbcTemplate.queryForObject(sql, new Object[]{author}, (resultSet, rowNum) -> Optional.of(MapResultSetToBook(resultSet))); 
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }

    
    @Override
    public Optional<Book> FindBookByTitle(String title) throws DatabaseActionException{
        String sql = "SELECT * FROM Books WHERE author = ?";
        try {
            return JdbcTemplate.queryForObject(sql, new Object[]{title}, (resultSet, rowNum) -> Optional.of(MapResultSetToBook(resultSet))); 
        } 
        catch (Exception e) {
            throw new DatabaseActionException();
        }
    }


    @Override
    public List<Book> FindAll() throws DatabaseActionException{
        String sql = "SELECT * FROM Books";
        try {
            return JdbcTemplate.query(sql, (resultSet, rowNum) -> new Book()
            .id(resultSet.getInt("id"))
            .title(resultSet.getString("title"))
            .author(resultSet.getString("author"))
            .yearOfPublic(resultSet.getInt("yearOfPublic"))
            );
        } 
        catch (Exception e) {
          throw new DatabaseActionException();
        }

    }


    public Book MapResultSetToBook(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        int yearOfPublic = resultSet.getInt("yearOfPublic");
        return new Book().id(id).title(title).author(author).yearOfPublic(yearOfPublic);
    }

}
