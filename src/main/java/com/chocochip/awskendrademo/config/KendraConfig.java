package com.chocochip.awskendrademo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kendra.KendraClient;

@Configuration
public class KendraConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public KendraClient AmazonKendraClient() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        return KendraClient
                .builder()
                .region(Region.of(region))
                .credentialsProvider(() -> awsCredentials)
                .build();
    }

}