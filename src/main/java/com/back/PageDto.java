package com.back;

import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.domain.WiseSaying.repository.WiseSayingRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor //필요한 인자 전체 다 받는 생성자
//DTO => 데이터 묶는 객체
public class PageDto {
    private int page; // 현재 페이지
    private int pageSize; // 페이지당 아이템 수
    private int totalItems; // 전체 아이템 수
    private List<WiseSaying> content;
}
