package com.back.domain.WiseSaying.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//lombok 사용으로 코드 줄음
@Setter
@Getter
@ToString
public class WiseSaying {
    private int id;
    private String saying;
    private String author;

    public WiseSaying(String saying, String author) {
        this.saying = saying;
        this.author = author;
    }

    public boolean isNew() {
        return id == 0;
    }
}