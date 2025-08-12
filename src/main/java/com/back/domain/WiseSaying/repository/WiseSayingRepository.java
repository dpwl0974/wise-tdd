package com.back.domain.WiseSaying.repository;

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

    //명언 조회
    // limit 무조건 filter 다음에 와야 함. (순서 중요 ⭐️)️
    public List<WiseSaying> findByContentContainingDesc(String kw) {
        return wiseSayings.reversed().stream()
                .filter(w -> w.getSaying().contains(kw))
                .limit(5)
                .toList();
    }

    //작가 조회
    public List<WiseSaying> findByAuthorContainingDesc(String kw) {

        return wiseSayings.reversed().stream()
                .filter(w -> w.getAuthor().contains(kw))
                .limit(5)
                .toList();
    }

    //명언 또는 작가 조회
    public List<WiseSaying> findByContentContainingOrAuthorContainingDesc(String kw) {
        return wiseSayings.reversed().stream()
                .filter(w -> w.getAuthor().contains(kw) || w.getSaying().contains(kw))
                .limit(5)
                .toList();
    }
}