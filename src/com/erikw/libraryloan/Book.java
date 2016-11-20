/*
 * This is our data struture for the library loan application.
 
 */
package com.erikw.libraryloan;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Erik.Walker
 */

// this class will design the dataStructure
public class Book 
{
    // note same copies of the same book will have different unique ids
    private long uniqueID;
    private String name;
    private List<String> authors;
    private int publishedYear;
    private boolean available;

    // lists in databases do not do very well
    // we will serialize it ourselves
    
    // get the authors
    public  String getAuthors(){
        String result = "";
        
        for(String author : authors)
            result+= author + "_";
        
        // return the result but remove last delimeter
        return result.substring(0, result.length()-1);
    }
    
    // now set the authors
    
    public void setAuthors(String authors){
        this.authors = Arrays.asList(authors.split(" "));
    }
    
    
    /**
     * @return the uniqueID
     */
    public long getUniqueID() {
        return uniqueID;
    }

    /**
     * @param uniqueID the uniqueID to set
     */
    public void setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the publishedYear
     */
    public int getPublishedYear() {
        return publishedYear;
    }

    /**
     * @param publishedYear the publishedYear to set
     */
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    // generate the toString() methods

    @Override
    public String toString() {
        return "Book{" + "uniqueID=" + uniqueID + ", name=" + name 
                + ", authors=" + authors + ", publishedYear="
                + publishedYear + ", available=" + available + '}';
    }
    
    // generate hashcode() and equals()

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.authors);
        hash = 79 * hash + this.publishedYear;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.publishedYear != other.publishedYear) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        return true;
    }
    
    
}

