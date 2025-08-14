package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.PageDto;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.domain.WiseSaying.repository.WiseSayingFileRepository;
import com.back.standard.util.Util;
import org.junit.jupiter.api.*;

import java.util.List;

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

        wiseSaying1 = wiseSayingFileRepository.save(wiseSaying1);

        wiseSayingFileRepository.delete(wiseSaying1); // id를 가지고 삭제

        //optional 가지고 왔을 때 비어있는 상자에 다른 값 넣겠다 -> null
        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).orElse(null);
        //null인지 확인
        assertThat(foundedWiseSaying1).isNull();

    }

    @Test
    @DisplayName("명언 수정")
    void t4() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        //찾기
        WiseSaying wiseSaying = wiseSayingFileRepository.findById(1).get();

        wiseSaying.setSaying("하하");
        wiseSaying.setAuthor("호호");

        wiseSayingFileRepository.save(wiseSaying); //수정한 객체 저장

        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).get(); //다시 꺼내기


        assertThat(foundedWiseSaying1.getId()).isEqualTo(1);
        assertThat(foundedWiseSaying1.getSaying()).isEqualTo("하하");
        assertThat(foundedWiseSaying1.getAuthor()).isEqualTo("호호");

    }

    @Test
    @DisplayName("명언 다건 조회 - 모든 명언 조회")
    void t5() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("파이팅", "아자ㅏ");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("하하하", "호호호");
        wiseSayingFileRepository.save(wiseSaying3);


        List<WiseSaying> wiseSayings = wiseSayingFileRepository.findAll();


        assertThat(wiseSayings)
                .containsExactly(wiseSaying1, wiseSaying2, wiseSaying3); //순서까지

    }

    @Test
    @DisplayName("명언 다건 조회 - content 필터링")
    void t6() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("파이팅", "아자ㅏ");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈하하하", "꿈호호호");
        wiseSayingFileRepository.save(wiseSaying3);


        // "꿈" 포함 5개 1페이지
        PageDto pageDto = wiseSayingFileRepository.findByContentContainingDesc("꿈", 5, 1);

        assertThat(pageDto.getContent())
                .containsExactly(wiseSaying3, wiseSaying1); //순서까지 , reversed 생각해서 3, 1

    }

    @Test
    @DisplayName("명언 다건 조회 - author 필터링")
    void t7() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("파이팅", "테아자ㅏ");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈하하하", "꿈호호호");
        wiseSayingFileRepository.save(wiseSaying3);


        // "꿈" 포함 5개 1페이지
        PageDto pageDto = wiseSayingFileRepository.findByAuthorContainingDesc("테", 5, 1);

        assertThat(pageDto.getContent())
                .containsExactly(wiseSaying2, wiseSaying1); //순서까지 , reversed 생각해서 3, 1

    }

    @Test
    @DisplayName("명언 다건 조회 - author, content 필터링")
    void t8() {

        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라.", "소크라테스");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        WiseSaying wiseSaying4 = new WiseSaying("잠을 잘 자야 합니다.", "꿈꾸는자");
        wiseSayingFileRepository.save(wiseSaying4);

        PageDto pageDto = wiseSayingFileRepository.findByContentContainingOrAuthorContainingDesc("꿈", 5, 1);

        assertThat(pageDto.getContent())
                .containsExactly(
                        wiseSaying4,
                        wiseSaying3,
                        wiseSaying1
                );

    }
}
