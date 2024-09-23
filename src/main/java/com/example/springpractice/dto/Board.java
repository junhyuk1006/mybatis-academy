package com.example.springpractice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board {
    private int id;
    private int userId;
    private String title;
    private String content;
    private String author;
    private Timestamp time;
}

