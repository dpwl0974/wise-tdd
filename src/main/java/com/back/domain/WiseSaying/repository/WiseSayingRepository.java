package com.back.domain.WiseSaying.repository;

import com.back.PageDto;
import com.back.domain.WiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);
    boolean delete(WiseSaying wiseSaying);
    Optional<WiseSaying> findById(int id);
    PageDto findByContentContainingDesc(String kw, int pageSize, int pageNo);
    PageDto findByAuthorContainingDesc(String kw, int pageSize, int pageNo);
    PageDto findByContentContainingOrAuthorContainingDesc(String kw, int pageSize, int pageNo);
}