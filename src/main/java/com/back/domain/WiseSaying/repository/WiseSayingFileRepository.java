package com.back.domain.WiseSaying.repository;

import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;
import java.util.Objects;

public class WiseSayingFileRepository {

    //직렬화
    public void save(WiseSaying wiseSaying) {

        if(wiseSaying.isNew()){
            wiseSaying.setId(1);
            String jsonStr = Util.json.toString(wiseSaying.toMap()); //객체 바로 넣으면 안되므로 문자열로 변환
            Util.file.set("db/WiseSaying/1.json", jsonStr);
        }
    }
    //역직렬화
    public WiseSaying FindByIdOrNull(int i) {
        String jsonStr = Util.file.get("db/WiseSaying/1.json","");

        if(jsonStr.isEmpty()){
            return null;
        }

        Map<String, Object> map = Util.json.toMap(jsonStr);
        WiseSaying wiseSaying = new WiseSaying(map); //map 넣으면 객체로 변환

        return wiseSaying;
    }
}
