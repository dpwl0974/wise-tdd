package com.back.system;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SystemControllerTest {

    @Test
    @DisplayName("종료")
    void t1() {
        String out = AppTestRunner.run("");
        assertThat(out).contains("프로그램을 종료합니다.");
    }

}