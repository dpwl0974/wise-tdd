package com.back.standard.util;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

//파일 관련 TDD
public class UtilFileTest {
    //테스트 전처리
    @BeforeAll
    public static void beforeAll() {
        Util.file.mkdir("temp");
    }
    //테스트 후처리
    @AfterAll
    public static void afterAll() {
        Util.file.rmdir("temp");
    }

    @Test
    @DisplayName("파일 생성")
    void t1() {

        //세팅 - 경로
        String filePath = "temp/test.txt";

        //수행
        Util.file.touch(filePath);

        //결과 -> 실제 파일이 존재하는가?
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isTrue();

        //테스트가 끝나면 파일 삭제
        Util.file.delete(filePath);

    }

    @Test
    @DisplayName("파일 삭제")
    void t2() {

        //given
        String filePath = "temp/test.txt";
        Util.file.touch(filePath); // 파일 생성

        //when
        Util.file.delete(filePath);

        //then
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isFalse();
    }

    @Test
    @DisplayName("파일 읽기/쓰기")
    void t3() {

        //given
        String filePath = "temp/test.txt";
        Util.file.set(filePath, "hello word"); // 파일 쓰기

        //when
        String content = Util.file.get(filePath, "");

        //then
        assertThat(content).isEqualTo("hello word");
        Util.file.delete(filePath); // 후처리 -> 테스트가 끝나면 파일 삭제

    }

    @Test
    @DisplayName("파일 생성 - 경로에 폴더가 없는 경우")
    void t4() {

        // given
        String filePath = "temp/temp/test.txt";

        // when
        Util.file.touch(filePath); // 파일 생성

        // then
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isTrue();

        // 테스트가 끝나면 파일 삭제
        Util.file.delete(filePath);
    }
}
