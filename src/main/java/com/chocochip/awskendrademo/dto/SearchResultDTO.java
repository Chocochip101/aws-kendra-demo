package com.chocochip.awskendrademo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchResultDTO {
    private String type;
    private String title;
    private String excerpt;
    private String uri;

    @Builder
    public SearchResultDTO(String type, String title, String excerpt, String uri) {
        this.type = type;
        this.title = title;
        this.excerpt = excerpt;
        this.uri = uri;
    }
}
