package com.chocochip.awskendrademo.service;

import com.chocochip.awskendrademo.dto.IndexRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.CreateIndexRequest;
import software.amazon.awssdk.services.kendra.model.CreateIndexResponse;

@Service
@RequiredArgsConstructor
public class KendraService {
    private final KendraClient kendraClient;

    public String createIndex(IndexRequestDTO indexRequestDTO) {
        CreateIndexRequest createIndexRequest = CreateIndexRequest
                .builder()
                .name(indexRequestDTO.getName())
                .clientToken(indexRequestDTO.getClientToken())
                .roleArn(indexRequestDTO.getRoleArn())
                .description(indexRequestDTO.getDescription())
                .build();
        CreateIndexResponse index = kendraClient.createIndex(createIndexRequest);
        return index.id();
    }

}
