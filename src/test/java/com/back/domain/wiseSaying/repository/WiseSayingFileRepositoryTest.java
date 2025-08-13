package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.domain.WiseSaying.repository.WiseSayingFileRepository;
import com.back.standard.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    private WiseSayingFileRepository wiseSayingFileRepository;

    public WiseSayingFileRepositoryTest() {
        AppContext.init();
        wiseSayingFileRepository = AppContext.wiseSayingFileRepository;
    }

    // 각 테스트 케이스마다 전처리 실행
    // 각 테스트 케이스 독립성 보장
    @BeforeEach
    void beforeEach() {
            Util.file.delete("db/WiseSaying");
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

        WiseSaying foundWiseSaying1 = wiseSayingFileRepository.FindByIdOrNull(1);
        assertThat(foundWiseSaying1).isEqualTo(wiseSaying1);

        WiseSaying foundWiseSaying2 = wiseSayingFileRepository.FindByIdOrNull(2);
        assertThat(foundWiseSaying2).isEqualTo(wiseSaying2);
    }
}
