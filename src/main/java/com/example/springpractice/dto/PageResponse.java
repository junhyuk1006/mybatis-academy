package com.example.springpractice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse {
    private List<BoardList> data;
    private int totalPages;
    private int currentPage;
    private int startPage;
    private int endPage;

    public PageResponse(List<BoardList> data, int totalPages, int currentPage,int startPage, int endPage) {
        this.data = data;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.startPage = startPage;
        this.endPage = endPage;
    }
}
