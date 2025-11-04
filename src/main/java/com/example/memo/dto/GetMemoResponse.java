package com.example.memo.dto;

import lombok.Getter;

@Getter
public class GetMemoResponse {

    private final Long id;
    private final String contents;


    public GetMemoResponse(Long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
