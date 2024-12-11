package com.example.demo.azure.client.helper;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ImageStorageClient {
    String uploadImage(String containerName, String originalImageName, InputStream data, long length) throws FileNotFoundException;
}
