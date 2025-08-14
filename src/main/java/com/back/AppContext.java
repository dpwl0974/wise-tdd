package com.back;

import com.back.domain.WiseSaying.controller.WiseSayingController;
import com.back.domain.WiseSaying.repository.WiseSayingFileRepository;
import com.back.domain.WiseSaying.repository.WiseSayingMemoryRepository;
import com.back.domain.WiseSaying.repository.WiseSayingRepository;
import com.back.domain.WiseSaying.service.WiseSayingService;
import com.back.system.SystemController;

import java.util.Scanner;

public class AppContext {

    public static Scanner sc;
    public static SystemController systemController;
    public static WiseSayingController wiseSayingController;
    public static WiseSayingService wiseSayingService;
    public static WiseSayingRepository wiseSayingRepository;
    public static WiseSayingFileRepository wiseSayingFileRepository;
    public static WiseSayingMemoryRepository wiseSayingMemoryRepository;

    //시스템 초기화
    public static void init(Scanner _sc, boolean isFileMode) {
        AppContext.sc = _sc;
        AppContext.wiseSayingMemoryRepository = new WiseSayingMemoryRepository();
        AppContext.wiseSayingFileRepository = new WiseSayingFileRepository();
        AppContext.wiseSayingRepository = isFileMode ? new WiseSayingFileRepository() : new WiseSayingMemoryRepository();
        AppContext.wiseSayingService = new WiseSayingService();
        AppContext.wiseSayingController = new WiseSayingController();
        AppContext.systemController = new SystemController();
    }

    public static void init() {
        init(new Scanner(System.in), true);
    }
}