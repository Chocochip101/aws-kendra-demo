package com.chocochip.awskendrademo.service;

import com.chocochip.awskendrademo.dto.IndexRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.CreateIndexRequest;
import software.amazon.awssdk.services.kendra.model.CreateIndexResponse;
import software.amazon.awssdk.services.kendra.model.DeleteIndexRequest;

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

    public void deleteIndex(String indexId) {
        DeleteIndexRequest deleteIndexRequest = DeleteIndexRequest
                .builder()
                .id(indexId)
                .build();
        kendraClient.deleteIndex(deleteIndexRequest);
    }
}
