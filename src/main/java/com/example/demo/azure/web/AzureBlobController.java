package com.example.demo.azure.web;

import com.example.demo.azure.client.helper.ImageStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/artifacts")
public class AzureBlobController {

    private static final Logger log = LoggerFactory.getLogger(AzureBlobController.class);
    @Autowired
    private ImageStorageClient imageStorageClient;

    @PostMapping("/images")
    public String uploadImage(@RequestParam String containerName, @RequestParam MultipartFile file) throws IOException {
        log.info("Invoking uploadImage rest endpoint : containerName : {}  fileName : {} \n", containerName, file.getOriginalFilename());
        return imageStorageClient.uploadImage(containerName, file.getOriginalFilename(), file.getInputStream(), file.getSize());
    }
}
