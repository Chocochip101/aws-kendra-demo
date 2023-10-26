package com.chocochip.awskendrademo.service;

import com.chocochip.awskendrademo.dto.S3DataSourceRequestDTO;
import com.chocochip.awskendrademo.dto.SearchResultDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.CreateDataSourceRequest;
import software.amazon.awssdk.services.kendra.model.CreateDataSourceResponse;
import software.amazon.awssdk.services.kendra.model.DataSourceConfiguration;
import software.amazon.awssdk.services.kendra.model.DataSourceType;
import software.amazon.awssdk.services.kendra.model.DocumentsMetadataConfiguration;
import software.amazon.awssdk.services.kendra.model.QueryRequest;
import software.amazon.awssdk.services.kendra.model.QueryResponse;
import software.amazon.awssdk.services.kendra.model.QueryResultType;
import software.amazon.awssdk.services.kendra.model.S3DataSourceConfiguration;
import software.amazon.awssdk.services.kendra.model.StartDataSourceSyncJobRequest;

@Service
@RequiredArgsConstructor
public class IndexService {
    private final KendraClient kendraClient;

    public List<SearchResultDTO> searchForDocuments(String query, String indexId) {
        QueryRequest queryRequest = QueryRequest
                .builder()
                .queryText(query)
                .indexId(indexId)
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

    public String addS3DataSource(S3DataSourceRequestDTO s3DataSourceRequestDTO, String indexId) {
        DataSourceConfiguration dataSourceConfiguration
                = getDataSourceConfiguration(s3DataSourceRequestDTO);

        CreateDataSourceRequest createDataSourceRequest = CreateDataSourceRequest
                .builder()
                .indexId(indexId)
                .name(s3DataSourceRequestDTO.getDataSourceName())
                .roleArn(s3DataSourceRequestDTO.getRoleArn())
                .description(s3DataSourceRequestDTO.getDescription())
                .clientToken(s3DataSourceRequestDTO.getClientToken())
                .configuration(dataSourceConfiguration)
                .type(DataSourceType.S3).build();
        CreateDataSourceResponse dataSource = kendraClient.createDataSource(createDataSourceRequest);
        return dataSource.id();
    }

    private DataSourceConfiguration getDataSourceConfiguration(S3DataSourceRequestDTO s3DataSourceRequestDTO) {
        DocumentsMetadataConfiguration documentsMetadataConfiguration = DocumentsMetadataConfiguration
                .builder()
                .s3Prefix(s3DataSourceRequestDTO.getS3PrefixMetaData())
                .build();

        S3DataSourceConfiguration s3DataSourceConfiguration = S3DataSourceConfiguration
                .builder()
                .documentsMetadataConfiguration(documentsMetadataConfiguration)
                .bucketName(s3DataSourceRequestDTO.getBucketName())
                .build();

        return DataSourceConfiguration
                .builder()
                .s3Configuration(s3DataSourceConfiguration)
                .build();
    }

    public void syncDataSource(String indexId, String dataSourceId) {
        StartDataSourceSyncJobRequest startDataSourceSyncJobRequest = StartDataSourceSyncJobRequest
                .builder()
                .indexId(indexId)
                .id(dataSourceId)
                .build();
        kendraClient.startDataSourceSyncJob(startDataSourceSyncJobRequest);
    }
}
