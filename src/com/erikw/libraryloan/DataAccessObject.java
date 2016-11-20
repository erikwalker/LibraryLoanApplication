/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erikw.libraryloan;

/**
 *
 * @author Erik.Walker
 */
public interface DataAccessObject
{
    // this interface will make it easier to communication with datbase
    public void setup() throws Exception;
    public void connect() throws Exception;
    public void close() throws Exception;
    
}
