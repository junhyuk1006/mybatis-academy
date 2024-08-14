package com.example.springpractice.dto;

import lombok.Data;

@Data
public class Like {
    private int id;
    private int commentId;
    private int userId;

    public Like(int commentId, int userId){
        this.commentId = commentId;
        this.userId = userId;
    }
}
