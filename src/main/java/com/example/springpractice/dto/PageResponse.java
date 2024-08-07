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

    public PageResponse(List<Board> data, int totalPages, int currentPage) {
        this.data = data;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
