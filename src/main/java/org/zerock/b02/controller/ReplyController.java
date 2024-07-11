package org.zerock.b02.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.zerock.b02.service.ReplyService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> register(@Valid @RequestBody ReplyDTO replyDTO,
                                                     BindingResult bindingResult) throws BindException {
        log.info("Registering new reply: " + replyDTO);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String,Long> map = new HashMap<>();
        Long rno = replyService.register(replyDTO);
        map.put("rno", rno);
        return ResponseEntity.ok(map); //ResponseEntity 는 상태코드와 함께 객체 전달
    }
}
