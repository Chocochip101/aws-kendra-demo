package com.chocochip.awskendrademo.application;

import com.chocochip.awskendrademo.dto.SearchResultDTO;
import com.chocochip.awskendrademo.service.DemoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchResultDTO>> search(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(demoService.searchForDocuments(keyword));
    }
}
