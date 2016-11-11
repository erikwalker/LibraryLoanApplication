/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikw.libraryloan;

import java.util.List;

/**
 *
 * @author Erik.Walker
 */
public interface BookDataAccessObject extends DataAccessObject
{
    // inserts a book data structure and gives back id
    public boolean insertBook(Book book);
    public boolean updateBook(Book book);
    public boolean deleteBook(Book book);
    
    // the properties could be uniqueId, name, authors, publishedYear
    // or available(availability)
    public List<Book> findBookByProperty(
            BookSearchType searchType, Object value);
    
    // a generic find all object
    public List<Book> findAll();
}
