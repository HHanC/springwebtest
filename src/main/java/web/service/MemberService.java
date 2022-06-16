package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.MemberEntity;
import web.domain.MemberRepository;
import web.dto.MemberDto;

@Service
public class MemberService {

@Autowired
private MemberRepository memberRepository;

    public boolean board(MemberDto memberDto){

        MemberEntity memberEntity = memberDto.toentity();
        memberRepository.save(memberEntity);
        return true;
    }

}
