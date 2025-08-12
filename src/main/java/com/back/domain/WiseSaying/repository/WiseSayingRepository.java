package com.back.domain.WiseSaying.repository;

import com.back.PageDto;
import com.back.domain.WiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying save(WiseSaying wiseSaying) {
        if(wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;
    }

    public boolean delete(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    public WiseSaying findByIdOrNull(int id) {

        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 명언 조회
    // limit 무조건 filter 다음에 와야 함. (순서 중요 ⭐️)️
    // 페이지 하나씩 넘어갈 때마다 n-1번 skip 할거임 -> 즉, (pageNo) - 1 * pageSize
    public List<WiseSaying> findByContentContainingDesc(String kw, int pageSize, int pageNo) {
        return wiseSayings.reversed().stream()
                .filter(w -> w.getSaying().contains(kw))
                .skip(pageSize * (pageNo - 1))
                .limit(pageSize)
                .toList();
    }

    //작가 조회
    public List<WiseSaying> findByAuthorContainingDesc(String kw, int pageSize, int pageNo) {

        return wiseSayings.reversed().stream()
                .filter(w -> w.getAuthor().contains(kw))
                .skip(pageSize * (pageNo - 1))
                .limit(pageSize)
                .toList();
    }

    //명언 또는 작가 조회
    public PageDto findByContentContainingOrAuthorContainingDesc(String kw, int pageSize, int pageNo) {
        List<WiseSaying> filteredContent = wiseSayings.reversed().stream()
                .filter(w -> w.getAuthor().contains(kw) || w.getSaying().contains(kw))
                .toList();

        List<WiseSaying> content = filteredContent.stream()
                .skip(pageSize * (pageNo - 1))
                .limit(pageSize)
                .toList();

        int totalItems = filteredContent.size();
        return new PageDto(pageNo, pageSize, totalItems, content);
    }
}