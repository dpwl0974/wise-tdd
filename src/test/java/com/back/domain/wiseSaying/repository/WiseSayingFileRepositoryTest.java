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

    @Test
    @DisplayName("2개 이상 명언 저장")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("하하","호호");
        WiseSaying wiseSaying2 = new WiseSaying("흐흐","히히");

        wiseSayingFileRepository.save(wiseSaying1);
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying foundwiseSaying1 = wiseSayingFileRepository.FindByIdOrNull(1);
        assertThat(foundwiseSaying1).isEqualTo(wiseSaying1);

        WiseSaying foundwiseSaying2 = wiseSayingFileRepository.FindByIdOrNull(2);
        assertThat(foundwiseSaying2).isEqualTo(wiseSaying2);
    }
}
