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
        // 1. DTO에 사진파일명을 롤링 시킨다.
//        String imgName = UUID.randomUUID() + "_" + v1DTO.getImg().getOriginalFilename();
        String imgName = v1DTO.getImg().getOriginalFilename();
        // 윈도우는 경로 \ 쓰지만 Paths객체가 알아서 바꿔줌
        String profileUrl = "images/" + imgName;
        String dbUrl = "/upload/" + imgName;

        // 2. DTO에 사진을 파일로 저장 (images 폴더)
        try {
            Path path = Paths.get(profileUrl);
            // 파일을 프로젝트 폴더(upload)안의 images 폴더에 저장
            Files.write(path, v1DTO.getImg().getBytes());
            // 외부에서 접근 가능한 경로로 db에는 다르게 저장
            uploadRepository.save(v1DTO.toEntity(dbUrl));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        // 3. username + 사진의 경로를 DB에 저장
        // 3.1 Upload -> upload_tb 생성
        // 3.2 username, profileUrl 필드 생성 -> 테이블 생성 되는지 확인
        // 3.3 DB에 username과 파일 경로 저장 (images/uuid_파일명)
        // images/profile변수명

    }

    public Upload v1사진보기() {
        return uploadRepository.findById(1);
    }
}
