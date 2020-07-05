package br.com.livro.rest;

import javax.ws.rs.ext.Provider;

@Provider
public class ContentTypeSetter {

    public ContentTypeSetter() {
        System.out.println("<< Provider Content Type Setter");
    }
}
