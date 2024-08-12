package com.example.springpractice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse {
    private List<Board> data;
    private int totalPages;
    private int currentPage;
    private int startPage;
    private int endPage;

    public PageResponse(List<Board> data, int totalPages, int currentPage,int startPage, int endPage) {
        this.data = data;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.startPage = startPage;
        this.endPage = endPage;
    }
}
