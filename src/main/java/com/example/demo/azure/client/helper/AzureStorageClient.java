package com.example.demo.azure.client.helper;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
public class AzureStorageClient implements ImageStorageClient {

    private static final Logger log = LoggerFactory.getLogger(AzureStorageClient.class);

    @Autowired
    private BlobServiceClient blobServiceClient;

    @Override
    public String uploadImage(String containerName, String originalImageName, InputStream data, long length) throws FileNotFoundException {
        BlobContainerClient blobContainer = getBlobContainer(containerName);
        String newImageName = UUID.randomUUID() + originalImageName.substring(originalImageName.lastIndexOf("."));
        log.info("Document name to be uploaded is {}", newImageName);
        BlobClient blobClient = blobContainer.getBlobClient(newImageName);
        blobClient.upload(data, length, true);
        String documentUrl = blobClient.getBlobUrl();
        log.info("Uploaded to {}", documentUrl);
        return documentUrl;
    }

    private BlobContainerClient getBlobContainer(String containerName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        boolean containerCreated = blobContainerClient.createIfNotExists();
        String logMessage = containerCreated ? String.format("Container created : %s", containerName) : String.format("%s already exists", containerName);
        log.info(logMessage);
        return blobContainerClient;
    }
}
