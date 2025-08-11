package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    @DisplayName("'== 명언 앱 ==' 출력")
    void t1() {
        String out = AppTestRunner.run("");
        assertThat(out).contains("== 명언 앱 ==");
    }

}
