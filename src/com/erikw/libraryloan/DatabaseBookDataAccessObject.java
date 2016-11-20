/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikw.libraryloan;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author Erik.Walker
 */
public class DatabaseBookDataAccessObject implements BookDataAccessObject
{
    /*// two valid reasons not to use library. 
        - library is too large
    
        - used this library..
        - https://commons.apache.org/proper/commons-dbutils/download_dbutils.cgi
    */
    // we need connections to database
    
    private Connection myConn;
    
    private QueryRunner dbAccess = new QueryRunner();
    
    final  String url = "jdbc:mysql://localhost:3306/books";
    final  String id = "student";
    final  String  password = "student";
        
    
    // instead of creating a new ArrayList every time.
    private static final List<Book> EMPTY = new ArrayList<>();
    
    @Override
    public void setup() throws Exception
    {
        
        try
        {
            // get connection 
            myConn = DriverManager.getConnection(url,id, password);
            
                        
            // execute query
            dbAccess.update(myConn,"drop table if exists books;");
            
            dbAccess.update(myConn,"CREATE TABLE books ("
                + "uniqueID BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(30),"
                    + " authors VARCHAR(100), publishedYear INTEGER,"
                    + " available BOOLEAN, PRIMARY KEY (uniqueID))");
            
        }
        catch(Exception ex)
        {
            
        }
        System.out.println("Table was created !");
    }

    @Override
    public void connect() throws Exception {
        try
        {
            myConn = DriverManager.getConnection(url,id, password);
        }
        catch(Exception e)
        {
            
        }
        
    }

    @Override
    public void close() throws Exception {
        myConn.close();
        
        try
        {
            myConn = DriverManager.getConnection(url,id, password);
        }
        catch(Exception e)
        {
            
        }
    }
    
       
    @Override
    public boolean insertBook(Book book) {
        try 
        {
            dbAccess.insert(myConn,"INSERT INTO Books(name, authors,"
                    + "publishedYear, available) VALUES (?, ?, ?, ?)",
                    new ScalarHandler<BigDecimal>(), book.getName(), 
                    book.getAuthors(), book.getPublishedYear(), 
                    book.isAvailable()).longValue();
            
            return true;
        }
        
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        
        try 
        {
            dbAccess.update(myConn, "UPDATE Books SET name =?, authors=?,"
                    + "publishedYear=?,available=? WHERE uniqueID=?",
                    book.getName(),book.getAuthors(), book.getPublishedYear(), 
                    book.isAvailable(), book.getUniqueID());
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        try
        {
            dbAccess.update(myConn, "DELETE FROM Books WHERE uniqueID=?", 
                    book.getUniqueID());
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> findBookByProperty(BookSearchType searchType,
            Object value)
    {
        String whereClause = "";
        String valueClause = "";
        
        switch(searchType) {
            case AUTHOR:
                whereClause = "authors LIKE ?";
                valueClause = "%" + value.toString() + "%";
                break;
            case AVAILABLE:
                whereClause = "available = ?";
                valueClause = value.toString();
                break;
            case ID:
                whereClause = "uniqueID = ?";
                valueClause = value.toString();
                break;
            case NAME:
                whereClause = "name LIKE ?";
                // if the particular name or column contains part of string
                // when searching
                valueClause = "%" + value.toString() + "%";
                break;
            
            case PUBLISHED_YEAR:
                whereClause = "publishedYear = ?";
                valueClause = value.toString();
                break;
                
            default: 
                System.out.println("Unknown search type");
                break;
                        
        }
            
        
        try
        {
            // BeanListHandler allows us to automatically parse fields of Book
            dbAccess.query(myConn, "SELECT * FROM Books WHERE " + whereClause,
                    new BeanListHandler<Book>(Book.class), valueClause);
                    
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return EMPTY;    
    }

    public List<Book> findAll() {
        try
        {
            // BeanListHandler allows us to automatically parse fields of Book
            return dbAccess.query(myConn, "SELECT * FROM Books", 
                    new BeanListHandler<Book>(Book.class));
                    
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return EMPTY;  
    }

    
    
}
