package com.chocochip.awskendrademo.application;

import com.chocochip.awskendrademo.dto.IndexRequestDTO;
import com.chocochip.awskendrademo.dto.S3DataSourceRequestDTO;
import com.chocochip.awskendrademo.dto.SearchResultDTO;
import com.chocochip.awskendrademo.service.KendraService;
import com.chocochip.awskendrademo.service.IndexService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final IndexService indexService;
    private final KendraService kendraService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchResultDTO>> search(
            @RequestParam("keyword") String keyword, @RequestParam("index-id") String indexId) {
        return ResponseEntity.ok(indexService.searchForDocuments(keyword, indexId));
    }

    @PostMapping("/datasource/s3")
    public ResponseEntity<String> addDataSource(
            @RequestBody S3DataSourceRequestDTO s3DataSourceRequestDTO, @RequestParam("index-id") String indexId) {
        return ResponseEntity.ok(indexService.addS3DataSource(s3DataSourceRequestDTO, indexId));
    }

    @GetMapping("/sync")
    public ResponseEntity<Boolean> sync(
            @RequestParam("index-id") String indexId, @RequestParam("datasource-id") String dataSourceId) {
        indexService.syncDataSource(indexId, dataSourceId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/kendra/index")
    public ResponseEntity<String> createIndex(@RequestBody IndexRequestDTO indexRequestDTO) {
        return ResponseEntity.ok(kendraService.createIndex(indexRequestDTO));
    }

    @DeleteMapping("/kendra/index")
    public ResponseEntity<Boolean> deleteIndex(@RequestParam("index-id") String indexId) {
        kendraService.deleteIndex(indexId);
        return ResponseEntity.ok(true);
    }
}
