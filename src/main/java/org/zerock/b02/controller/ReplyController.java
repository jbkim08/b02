package org.zerock.b02.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b02.dto.ReplyDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> register(@Valid @RequestBody ReplyDTO replyDTO,
                                                     BindingResult bindingResult) throws BindException {
        log.info(replyDTO);
        if(bindingResult.hasErrors()) {
            //유효성검사 에러시 바인드예외를 발생시킴
            throw new BindException(bindingResult);
        }

        Map<String,Long> resultMap = Map.of("rno", 111L);

        return ResponseEntity.ok(resultMap); //ResponseEntity 는 상태코드와 함께 객체 전달
    }
}
