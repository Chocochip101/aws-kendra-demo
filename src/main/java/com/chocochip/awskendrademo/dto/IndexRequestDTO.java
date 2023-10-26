package com.chocochip.awskendrademo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IndexRequestDTO {
    private String name;
    private String clientToken;
    private String roleArn;
    private String description = "";

    @Builder
    public IndexRequestDTO(String name, String clientToken, String roleArn, String description) {
        this.name = name;
        this.clientToken = clientToken;
        this.roleArn = roleArn;
        this.description = description;
    }
}
