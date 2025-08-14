package com.back;

import com.back.domain.WiseSaying.repository.WiseSayingRepository;

public class AppConfig {

    //개발 모드
    public static String mode = "dev";

    public static void setTestMode() {
        mode = "test";
    }

    public static void setDevMode() {
        mode = "dev";
    }

    public static String getMode() {
        return mode;
    }
}
