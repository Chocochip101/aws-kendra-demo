package com.chocochip.awskendrademo.service;

import com.chocochip.awskendrademo.dto.SearchResultDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.QueryRequest;
import software.amazon.awssdk.services.kendra.model.QueryResponse;
import software.amazon.awssdk.services.kendra.model.QueryResultType;

@Service
@RequiredArgsConstructor
public class DemoService {
    @Value("${kendra.index-id}")
    private String osIndexId = "your-index-id";

    private final KendraClient kendraClient;

    public List<SearchResultDTO> searchForDocuments(String query) {
        QueryRequest queryRequest = QueryRequest
                .builder()
                .queryText(query)
                .indexId(osIndexId)
                .build();

        QueryResponse queryResponse = kendraClient.query(queryRequest);

        return queryResponse.resultItems().stream()
                .filter(item -> isValidResultType(item.type()))
                .map(item -> SearchResultDTO.builder()
                        .type(item.type().toString())
                        .title(item.documentTitle().text())
                        .excerpt(item.documentExcerpt().text())
                        .uri(item.documentURI())
                        .build())
                .collect(Collectors.toList());
    }

    private boolean isValidResultType(QueryResultType type) {
        return type == QueryResultType.ANSWER || type == QueryResultType.QUESTION_ANSWER
                || type == QueryResultType.DOCUMENT;
    }
}