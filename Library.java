package com.erikw.libraryloan;

import java.util.List;

public class Library {

    private BookDataAccessObject bookDataAccessObject;

    public Library(BookDataAccessObject bookDataAccessObject) {
        this.bookDataAccessObject = bookDataAccessObject;
        try {
            bookDataAccessObject.connect();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addNewBook(String name, String authors, int year) {
        Book book = new Book();
        book.setAvailable(true);
        book.setName(name);
        book.setAuthors(authors);
        book.setPublishedYear(year);

        bookDataAccessObject.insertBook(book);
    }

    public void loanBook(long uniqueID) {
        List<Book> books = bookDataAccessObject.findBookByProperty(BookSearchType.ID, uniqueID);
        if (books.size() > 0) {
            books.get(0).setAvailable(false);
            bookDataAccessObject.updateBook(books.get(0));
        }
    }

    public void returnBook(long uniqueID) {
        List<Book> books = bookDataAccessObject.findBookByProperty(BookSearchType.ID, uniqueID);
        if (books.size() > 0) {
            books.get(0).setAvailable(true);
            bookDataAccessObject.updateBook(books.get(0));
        }
    }

    public List<Book> search(BookSearchType searchType, String value) {
        return bookDataAccessObject.findBookByProperty(searchType, value);
    }

    public void close() {
        try {
            bookDataAccessObject.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
