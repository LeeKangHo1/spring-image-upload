package com.metacoding.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public class UploadRequest {

    @Data
    public static class V2DTO {
        private String username;
        private String img;

        public Upload toEntity(String profileUrl) {
            Upload upload = Upload.builder()
                    .username(this.username)
                    .profileUrl(profileUrl)
                    .build();
            return upload;
        }
    }

    @Data
    public static class V1DTO {
        private String username;
        private MultipartFile img;

        public Upload toEntity(String profileUrl) {
            Upload upload = Upload.builder()
                    .username(this.username)
                    .profileUrl(profileUrl)
                    .build();
            return upload;
        }
    }
}