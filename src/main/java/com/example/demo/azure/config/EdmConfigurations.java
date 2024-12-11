package com.example.demo.azure.config;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ToString
@Configuration
@PropertySource("classpath:edm-gcp.properties")
@ConfigurationProperties(prefix = "edm.gcp")
public class EdmConfigurations {
    private boolean streamIngestion;
    private int poolSize;
    private boolean enablePool;
    private int maxPerRoute;
    private String projectId;
    private GcpServiceAccount serviceAccounts;

    public boolean isStreamIngestion() {
        return streamIngestion;
    }

    public void setStreamIngestion(boolean streamIngestion) {
        this.streamIngestion = streamIngestion;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public boolean isEnablePool() {
        return enablePool;
    }

    public void setEnablePool(boolean enablePool) {
        this.enablePool = enablePool;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public GcpServiceAccount getServiceAccounts() {
        return serviceAccounts;
    }

    public void setServiceAccounts(GcpServiceAccount serviceAccounts) {
        this.serviceAccounts = serviceAccounts;
    }

    @ToString
    private static class GcpServiceAccount {
        private String targetServiceAccount;
        private String credentialFilePath;

        public String getTargetServiceAccount() {
            return targetServiceAccount;
        }

        public void setTargetServiceAccount(String targetServiceAccount) {
            this.targetServiceAccount = targetServiceAccount;
        }

        public String getCredentialFilePath() {
            return credentialFilePath;
        }

        public void setCredentialFilePath(String credentialFilePath) {
            this.credentialFilePath = credentialFilePath;
        }
    }
}