package com.example.springpractice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardList {
    private int id;
    private String title;
    private String author;
    private int likeCount;
    private int readCount;
    private Timestamp time;
}
