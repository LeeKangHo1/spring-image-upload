package com.metacoding.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UploadController {

    private final UploadService uploadService;

    // form 태그 이동
    @GetMapping("/file1")
    public String file1(Model model) {
        return "file1";
    }

    // ajax 이용
    @GetMapping("/file2")
    public String file2() {
        return "file2";
    }

    @PostMapping("/v1/upload")
    public String v1Upload(UploadRequest.V1DTO v1DTO) {
//    public String v1upload(String username, MultipartFile file) {
        System.out.println(v1DTO.getUsername());
        // 업로드된 파일 이름
        System.out.println(v1DTO.getImg().getOriginalFilename());
        // 업로드된 파일의 확장자(예시: image/jpeg)
        System.out.println(v1DTO.getImg().getContentType());

        // 요청, 응답 처리 완료(Controller의 책임을 완수) -> 다른 로직은 서비스로
        uploadService.v1사진저장(v1DTO);
        return "index";
    }

    @PostMapping("/v2/upload")
    public ResponseEntity<?> v2upload(@RequestBody UploadRequest.V2DTO v2DTO) {
        uploadService.v2사진저장(v2DTO);
        Resp resp = new Resp(true, "성공", null);
        return ResponseEntity.ok(resp);
    }

    // form 태그 이용
    @GetMapping("/file1-check")
    public String file1Check(Model model) {
        Upload upload = uploadService.사진보기();
        model.addAttribute("model", upload);
        return "file1-check";
    }
}
