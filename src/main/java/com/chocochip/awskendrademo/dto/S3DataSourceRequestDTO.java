package com.chocochip.awskendrademo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class S3DataSourceRequestDTO {
    private String clientToken;
    private String dataSourceName;
    private String description;
    private String languageCode;
    private String roleArn;
    private String bucketName;
    private String s3PrefixMetaData;

    @Builder
    public S3DataSourceRequestDTO(String clientToken, String dataSourceName, String description, String languageCode,
                                  String roleArn, String bucketName, String s3PrefixMetaData) {
        this.clientToken = clientToken;
        this.dataSourceName = dataSourceName;
        this.description = description;
        this.languageCode = languageCode;
        this.roleArn = roleArn;
        this.bucketName = bucketName;
        this.s3PrefixMetaData = s3PrefixMetaData;
    }
}
