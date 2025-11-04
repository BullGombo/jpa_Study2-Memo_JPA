package com.example.memo.dto;

import lombok.Getter;

@Getter
public class UpdateMemoResponse {
    private final Long id;
    private final String title;

    // 생성자
    public UpdateMemoResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
