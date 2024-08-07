package com.example.springpractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {
    private int page; // 현재 페이지 번호
    private int size; // 페이지당 항목 수

    public PageRequest() {
        this.page = 1;
        this.size = 10;
    }
    public int getOffset(){
        return (page - 1) * size;
    }
}
