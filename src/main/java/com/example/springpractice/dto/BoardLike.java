package com.example.springpractice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardLike {
    private int id;
    private int userId;
    private int boardId;
    private Timestamp time;

    public BoardLike(int userId, int boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }
}

