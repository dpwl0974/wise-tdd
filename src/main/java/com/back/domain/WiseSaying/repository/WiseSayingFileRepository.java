package com.back.domain.WiseSaying.repository;

import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;
import java.util.Objects;

public class WiseSayingFileRepository {
    private static String dbPath = "db/wiseSaying";

    public static void clear() {
        Util.file.delete(dbPath);
    }

    private String getFilePath(int id) {
        return dbPath + "/%d.json".formatted(id);
    }

    private String getLastIdPath() {
        return dbPath + "/lastId.txt";
    }


    //직렬화
    public void save(WiseSaying wiseSaying) {

        if(wiseSaying.isNew()) {

            incrementLastId();
            int lastId = getLastId();
            wiseSaying.setId(lastId);
            String jsonStr = Util.json.toString(wiseSaying.toMap());
            Util.file.set(getFilePath(wiseSaying.getId()), jsonStr);
        }
    }
    //기존 아이디 + 1
    private void incrementLastId(){
        Util.file.set(getLastIdPath(), String.valueOf(getLastId() + 1));
    }

    private int getLastId() {
        //int로 반환
        return Util.file.getAsInt(getLastIdPath(), 0);
    }

    //역직렬화
    public WiseSaying FindByIdOrNull(int id) {
        String jsonStr = Util.file.get(getFilePath(id), "");

        if(jsonStr.isEmpty()){
            return null;
        }

        Map<String, Object> map = Util.json.toMap(jsonStr);
        WiseSaying wiseSaying = new WiseSaying(map); //map 넣으면 객체로 변환

        return wiseSaying;
    }
}
