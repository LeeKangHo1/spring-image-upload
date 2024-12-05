package com.metacoding.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println(v1DTO.getImg().getOriginalFilename());
        System.out.println(v1DTO.getImg().getContentType());

        // 요청, 응답 처리 다함 -> 다른 건 책임 전가(서비스로)
        uploadService.v1사진저장(v1DTO);
        return "index";
    }

    @PostMapping("/v2/upload")
    public String v2upload() {
        return "index";
    }

    // form 태그 이용
    @GetMapping("/file1-check")
    public String file1Check(Model model) {
        Upload upload = uploadService.v1사진보기();
        model.addAttribute("model", upload);
        return "file1-check";
    }
}
