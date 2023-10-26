package com.chocochip.awskendrademo.application;

import com.chocochip.awskendrademo.dto.IndexRequestDTO;
import com.chocochip.awskendrademo.dto.SearchResultDTO;
import com.chocochip.awskendrademo.service.KendraService;
import com.chocochip.awskendrademo.service.QueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final QueryService demoService;
    private final KendraService kendraService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchResultDTO>> search(
            @RequestParam("keyword") String keyword, @RequestParam("id") String indexId) {
        return ResponseEntity.ok(demoService.searchForDocuments(keyword, indexId));
    }

    @PostMapping("/kendra/index")
    public ResponseEntity<String> createIndex(@RequestBody IndexRequestDTO indexRequestDTO) {
        return ResponseEntity.ok(kendraService.createIndex(indexRequestDTO));
    }
}
