package com.example.memo.dto;

import lombok.Getter;

@Getter
public class CreateMemoResponse {

    private final long id;
    private final String content;

    public CreateMemoResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }

}
