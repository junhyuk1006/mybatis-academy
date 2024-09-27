package com.example.springpractice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board {
    private int id;
    private int userId;
    private int readCount;
    private String title;
    private String content;
    private String author;
    private String avatarUrl;
    private Timestamp time;
}

