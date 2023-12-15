package com.bkacad.Models.Entitites;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Getter
@Setter

public class Book {
    private int id; 
    private String  title;
    private String author;
    private int yearOfPublic;  

    public int GetId(){
        return id;
    }

    public String GetTitle(){
        return  title;
    }

    public String GetAuthor(){
        return author;
    }

    public int GetyearOfPublic(){
        return yearOfPublic;
    }
}
