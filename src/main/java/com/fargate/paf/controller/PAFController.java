package com.fargate.paf.controller;

import com.fargate.paf.pojo.PAF;
import com.fargate.paf.repository.PAFDataRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author Sachith Dickwella
 */
@RestController
@RequestMapping("/paf")
public class PAFController {

    private final PAFDataRepository pafRepo;

    @Autowired
    public PAFController(@NotNull PAFDataRepository pafRepo) {
        this.pafRepo = pafRepo;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PAF>> all(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "20") int size) {
        return ResponseEntity.ok(pafRepo.findAll(PageRequest.of(page - 1, size).next()));
    }

    @GetMapping(path = "/all/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> allFile(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "20") int size) {

        var pafString = pafRepo.findAll(PageRequest.of(page, size).first())
                .stream()
                .map(PAF::toString)
                .collect(Collectors.joining("\n"));

        var resource = new ByteArrayResource(pafString.getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok()
                .contentLength(pafString.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paf-download.csv")
                .body(resource);
    }
}
