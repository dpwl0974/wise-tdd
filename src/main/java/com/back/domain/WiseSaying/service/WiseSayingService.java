package com.back.domain.WiseSaying.service;

import com.back.AppContext;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.domain.WiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying write(String saying, String author) {
        WiseSaying wiseSaying = new WiseSaying(saying, author);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findListDesc() {
        return wiseSayingRepository.findListDesc();
    }

    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }

    public WiseSaying findByIdOrNull(int id) {
        return wiseSayingRepository.findByIdOrNull(id);
    }
}
