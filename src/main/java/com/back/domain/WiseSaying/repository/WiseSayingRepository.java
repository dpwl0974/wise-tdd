package com.back.domain.WiseSaying.repository;

import com.back.AppConfig;
import com.back.PageDto;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 인터페이스 -> 내용 없는 껍데기
// 다른 곳에서 사용해야 하므로 추가 -> 인터페이스 사용하는 클래스에 다 구현되어야 ⭕️
public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);
    boolean delete(WiseSaying wiseSaying);
    Optional<WiseSaying> findById(int id);
    PageDto findByContentContainingDesc(String kw, int pageSize, int pageNo);
    PageDto findByAuthorContainingDesc(String kw, int pageSize, int pageNo);
    PageDto findByContentContainingOrAuthorContainingDesc(String kw, int pageSize, int pageNo);

    List<WiseSaying> findAll();

    //구현 가능 (default 활용)
    default String build() {
        List<WiseSaying> wiseSayings = findAll();

        List<Map<String, Object>> mapList = wiseSayings.stream()
                .map(WiseSaying::toMap)
                .toList();

        String jsonStr = Util.json.toString(mapList);
        String filePath = AppConfig.getMode() + "db/wiseSaying/data.json";

        Util.file.set(filePath, jsonStr);

        return filePath;
    }
}