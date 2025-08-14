package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.domain.WiseSaying.repository.WiseSayingFileRepository;
import com.back.standard.util.Util;
import org.junit.jupiter.api.*;

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
        WiseSayingFileRepository.clear();
    }

    @AfterEach
    void afterEach() {
        WiseSayingFileRepository.clear();
    }

    @Test
    @DisplayName("명언 저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("rksk","rksjk");

        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundedWiseSaying = wiseSayingFileRepository.findById(1).get();
        assertThat(foundedWiseSaying).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("2개 이상 명언 저장")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("하하","호호");
        WiseSaying wiseSaying2 = new WiseSaying("흐흐","히히");

        wiseSayingFileRepository.save(wiseSaying1);
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).get();
        assertThat(foundedWiseSaying1).isEqualTo(wiseSaying1);

        WiseSaying foundedWiseSaying2 = wiseSayingFileRepository.findById(2).get();
        assertThat(foundedWiseSaying2).isEqualTo(wiseSaying2);
    }

    @Test
    @DisplayName("명언 삭제")
    void t3() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라", "소크라테스");

        wiseSaying1 = wiseSayingFileRepository.save(wiseSaying1);
        wiseSaying2 = wiseSayingFileRepository.save(wiseSaying2);

        wiseSayingFileRepository.delete(wiseSaying1); // id를 가지고 삭제

        //optional 가지고 왔을 때 비어있는 상자에 다른 값 넣겠다 -> null
        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).orElse(null);
        //null인지 확인
        assertThat(foundedWiseSaying1).isNull();

    }
}
