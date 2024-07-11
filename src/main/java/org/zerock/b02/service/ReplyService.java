package org.zerock.b02.service;

import org.zerock.b02.dto.ReplyDTO;

public interface ReplyService {
    //댓글 등록
    Long register(ReplyDTO replyDTO);
}
