package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.domain.WiseSaying.repository.WiseSayingFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    private WiseSayingFileRepository wiseSayingFileRepository;

    public WiseSayingFileRepositoryTest() {
        AppContext.init();
        wiseSayingFileRepository = AppContext.wiseSayingFileRepository;
    }

    @Test
    @DisplayName("명언 저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("rksk","rksjk");

        wiseSayingFileRepository.save(wiseSaying);
        WiseSaying foundwiseSaying = wiseSayingFileRepository.FindByIdOrNull(1);
        assertThat(foundwiseSaying).isEqualTo(wiseSaying);
    }
}
