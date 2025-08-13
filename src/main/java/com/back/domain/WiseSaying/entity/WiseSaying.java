package com.back.domain.WiseSaying.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

//lombok 사용으로 코드 줄음
@Setter
@Getter
@ToString
@EqualsAndHashCode  //객체 비교하는 것들 정해줘야 정확 비교 가능
public class WiseSaying {
    private int id;
    private String saying;
    private String author;

    //메서드 오버로딩
    public WiseSaying(String saying, String author) {
        this.saying = saying;
        this.author = author;
    }

    //생성자 오버로딩
    public WiseSaying(Map<String, Object>map){
        this.id = (int) map.get("id");
        this.saying = (String) map.get("saying");
        this.author = (String) map.get("author");
    }

    public boolean isNew() {
        return id == 0;
    }

    //객체 바로 못넣으므로 맵으로 변환
    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>(); //순서 보장
        map.put("id", id);
        map.put("saying", saying);
        map.put("author", author);
        return map;
    }

}