package com.example.memo.controller;

import com.example.memo.dto.*;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    // 상위 레이어가 하위 레이어를 가져와야함
    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<CreateMemoResponse> createMemo(@RequestBody CreateMemoRequest request) {
//        CreateMemoResponse result = memoService.save(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
        // .save() 메서드의 타입도 CreateMemoResponse 이다
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.save(request));
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<GetMemoResponse> getOneMemo(@PathVariable Long memoId) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findOne(memoId));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<GetMemoResponse>> getAllMemos() {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findAll());
    }

    // 단일 컨텐츠 수정
    @PatchMapping("/memos/{memoId}")    //                                  대상 Id                           수정 내용
    public ResponseEntity<UpdateMemoResponse> updateMemoContents(@PathVariable Long memoId, @RequestBody UpdateMemoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.update(memoId, request));
    }

    @DeleteMapping("/memos/{memoId}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long memoId) {
        memoService.delete(memoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
