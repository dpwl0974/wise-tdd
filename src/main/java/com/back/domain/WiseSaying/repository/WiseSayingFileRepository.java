package com.back.domain.WiseSaying.repository;

import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
    public WiseSaying save(WiseSaying wiseSaying) {

        if(wiseSaying.isNew()) {

            incrementLastId();
            int lastId = getLastId();
            wiseSaying.setId(lastId);
        }

        //저장 시 수정도 포함
        //if문에 걸리든 안걸리든 어짜피 함
        String jsonStr = Util.json.toString(wiseSaying.toMap());
        Util.file.set(getFilePath(wiseSaying.getId()), jsonStr);
        return wiseSaying;
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
    public Optional<WiseSaying> findById(int id) {
        String jsonStr = Util.file.get(getFilePath(id), "");

        if(jsonStr.isEmpty()){
            return Optional.empty();
        }

        Map<String, Object> map = Util.json.toMap(jsonStr);
        WiseSaying wiseSaying = new WiseSaying(map); //map 넣으면 객체로 변환

        return Optional.of(wiseSaying); //상자에 담음
    }

    public boolean delete(WiseSaying wiseSaying1) {
        return Util.file.delete(getFilePath(wiseSaying1.getId()));
    }
}
