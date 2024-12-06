package com.metacoding.upload;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadService {
    private final UploadRepository uploadRepository;

    @Transactional
    public void v1사진저장(UploadRequest.V1DTO v1DTO) {
        String dbUrl = MyFileUtil.fileSave(v1DTO.getImg());
        uploadRepository.save(v1DTO.toEntity(dbUrl));
    }

    // 이미지 1개만 올렸을 때 확인 가능
    public Upload 사진보기() {
        return uploadRepository.findById(1);
    }

    @Transactional
    public void v2사진저장(UploadRequest.V2DTO v2DTO) {
        // DB에 username이랑 dbUrl 저장
        String dbUrl = MyFileUtil.fileSave(v2DTO.getImg());
        uploadRepository.save(v2DTO.toEntity(dbUrl));
    }
}
