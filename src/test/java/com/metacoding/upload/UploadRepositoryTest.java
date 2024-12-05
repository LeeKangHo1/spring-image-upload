package com.metacoding.upload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UploadRepository.class)
@DataJpaTest
public class UploadRepositoryTest {

    @Autowired private UploadRepository uploadRepository;

    @Test
    public void saveUrl_test() {
        Upload upload = new Upload();
        upload = Upload.builder()
                .username("테스트이름")
                .profileUrl("테스트경로")
                .build();

        uploadRepository.save(upload);

        Upload save = uploadRepository.findById(1);

        Assertions.assertNotNull(save);
        System.out.println(save.getUsername());
        System.out.println(save.getProfileUrl());
    }
}
