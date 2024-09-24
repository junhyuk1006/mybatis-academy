package com.example.springpractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {
    private int page; // 현재 페이지 번호
    private int size; // 페이지당 항목 수
    private String sort; // 정렬
    private String searchKeyword;

    public PageRequest() {
        this.page = 1;
        this.size = 10;
        this.sort = "time";
        this.searchKeyword = "";
    }
    public int getOffset(){
        return (page - 1) * size;
    }
}
