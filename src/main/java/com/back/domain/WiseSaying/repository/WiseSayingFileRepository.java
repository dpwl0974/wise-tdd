package com.back.domain.WiseSaying.repository;

import com.back.AppConfig;
import com.back.PageDto;
import com.back.domain.WiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.*;

public class WiseSayingFileRepository implements WiseSayingRepository{
    private static String dbPath = AppConfig.getMode() + "/db/wiseSaying";

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

    public List<WiseSaying> findAll() {
        return Util.file.walkRegularFiles(dbPath, "^\\d+\\.json$")
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap)
                .map(WiseSaying::new)
                .toList();

    }

    public PageDto findByContentContainingDesc(String kw, int pageSize, int pageNo) {
        List<WiseSaying> filteredWiseSayings = findAll().stream()
                .filter(wiseSaying -> wiseSaying.getSaying().contains(kw))
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(filteredWiseSayings, pageNo, pageSize);
    }

    private PageDto pageOf(List<WiseSaying> filteredContent, int pageNo, int pageSize) {

        List<WiseSaying> content = filteredContent.stream()
                .skip((pageNo-1) * pageSize)
                .limit(pageSize)
                .toList();

        int totalItems = filteredContent.size();
        return new PageDto(pageNo, pageSize, totalItems, content);
    }

    public PageDto findByAuthorContainingDesc(String kw, int pageSize, int pageNo) {
        List<WiseSaying> filteredWiseSayings = findAll().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(kw))
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(filteredWiseSayings, pageNo, pageSize);
    }

    public PageDto findByContentContainingOrAuthorContainingDesc(String kw, int pageSize, int pageNo) {
        List<WiseSaying> filteredWiseSayings = findAll().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(kw) || wiseSaying.getSaying().contains(kw))
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(filteredWiseSayings, pageNo, pageSize);
    }
}
