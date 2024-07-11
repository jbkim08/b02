package org.zerock.b02.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b02.domain.Reply;
import org.zerock.b02.dto.ReplyDTO;
import org.zerock.b02.repository.ReplyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    //댓글을 번호로 조회해서 리턴
    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow(); //예외처리 x
        return modelMapper.map(reply, ReplyDTO.class); //reply 엔티티 => DTO
    }

    //화면에서 수정된 댓글 내용을 DB 에서 찾은 댓글에 업데이트해 저장함
    @Override
    public void modify(ReplyDTO replyDTO) {
        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());
        Reply reply = replyOptional.orElseThrow();
        reply.changeText(replyDTO.getReplyText()); //DTO 의 댓글내용으로 업데이트
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
