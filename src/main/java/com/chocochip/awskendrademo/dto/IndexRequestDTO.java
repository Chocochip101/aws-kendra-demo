package com.chocochip.awskendrademo.dto;

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
}
