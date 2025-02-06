package br.com.luger.dev.precad.model;

import lombok.Data;

import java.util.List;

@Data
public class Pageado<T> {
    private List<T> content;
    private int totalElements;
    private int totalPages;
    private int size;
    private int number;


}
