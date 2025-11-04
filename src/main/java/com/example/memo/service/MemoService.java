package com.example.memo.service;

import com.example.memo.dto.*;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    // 상위 레이어가 하위 레이어를 가져와야함
    private final MemoRepository memoRepository;

    @Transactional
    public CreateMemoResponse save(CreateMemoRequest request) {
        Memo memo = new Memo(request.getContents());
        Memo savedMemo = memoRepository.save(memo);
        CreateMemoResponse response = new CreateMemoResponse(
                savedMemo.getId(),
                savedMemo.getContents()
        );
        return response;
    }

    @Transactional(readOnly = true)
    public GetMemoResponse findOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("없는 메모입니다.")
        );
        return new GetMemoResponse(
                memo.getId(),
                memo.getContents()
        );
    }

    @Transactional(readOnly = true)
    public List<GetMemoResponse> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<GetMemoResponse> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            GetMemoResponse dto = new GetMemoResponse(
                    memo.getId(),
                    memo.getContents()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UpdateMemoResponse update(Long memoId, UpdateMemoRequest request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("없는 메모입니다.")
        );  // 트렌젝션 안에서 DB를 갔다옴 -> 영속상태
        memo.updateContents((request.getContents()));   // 더티채킹
        return new UpdateMemoResponse(
                memo.getId(),
                memo.getContents()
        );
    }

    @Transactional
    public void delete(Long memoId) {
        boolean existence = memoRepository.existsById(memoId);
        // 해당 memoId가 존재하지 않으면
        if (!existence) {
            throw new IllegalStateException("없는 메모입니다.");
        }

        // memoId가 존재하면
        memoRepository.deleteById(memoId);
    }
}
