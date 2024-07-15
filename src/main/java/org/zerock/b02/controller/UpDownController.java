package org.zerock.b02.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zerock.b02.dto.UploadFileDTO;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

    //프로퍼티에서 가져온 업로드 주소
    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);
        if(uploadFileDTO.getFiles() != null){
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                String originalName = multipartFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString(); //중복안되는 ID
                Path savePath = Paths.get(uploadPath, uuid+"_"+originalName);
                try {
                    multipartFile.transferTo(savePath); //업로드폴더에 저장
                } catch (IOException e) {
                   e.printStackTrace(); //에러발생시 출력
                }
            });
        }
        return null;
    }
}
