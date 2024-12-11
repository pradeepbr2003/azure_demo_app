package com.example.demo.azure.client.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AzureClientUtility {

    private static final Logger log = LoggerFactory.getLogger(AzureClientUtility.class);
    @Autowired
    private AzureConfigurations configurations;

    @Autowired
    private com.example.demo.azure.config.EdmConfigurations edmConfigurations;

    @Bean
    public BlobServiceClient getBlobServiceClient() {
        String endPoint = String.format(configurations.getEndPoint(), configurations.getAccountName());
        log.info("Endpoint : {}", endPoint);

        log.info("\n printing map values \n");
        log.info(edmConfigurations.toString());

        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(configurations.getAccountName(), configurations.getAccountKey());
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endPoint)
                .credential(credential)
                .buildClient();
        log.info("BlobServiceClient bean is created : {} ", blobServiceClient);
        return blobServiceClient;
    }

}
