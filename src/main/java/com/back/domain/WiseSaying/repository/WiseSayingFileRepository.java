package com.back.domain.WiseSaying.repository;

import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;
import java.util.Objects;

public class WiseSayingFileRepository {

    //직렬화
    public void save(WiseSaying wiseSaying) {

        if(wiseSaying.isNew()) {

            incrementLastId();
            int lastId = getLastId();
            wiseSaying.setId(lastId);
            String jsonStr = Util.json.toString(wiseSaying.toMap());
            Util.file.set("db/wiseSaying/%d.json".formatted(wiseSaying.getId()), jsonStr);
        }
    }
    //기존 아이디 + 1
    private void incrementLastId(){
        Util.file.set("db/WiseSaying/lastId.txt", String.valueOf(getLastId()+1));
    }

    private int getLastId() {
        //int로 반환
        return Util.file.getAsInt("db/wiseSaying/lastId.txt", 0);
    }

    //역직렬화
    public WiseSaying FindByIdOrNull(int id) {
        String jsonStr = Util.file.get("db/wiseSaying/%d.json".formatted(id), "");

        if(jsonStr.isEmpty()){
            return null;
        }

        Map<String, Object> map = Util.json.toMap(jsonStr);
        WiseSaying wiseSaying = new WiseSaying(map); //map 넣으면 객체로 변환

        return wiseSaying;
    }
}
