package com.dbc.voting.service;

import com.dbc.voting.dto.MemberDTO;
import com.dbc.voting.entity.Member;
import com.dbc.voting.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void createMember(MemberDTO member) {
        Member memberEntity = new Member();
        memberEntity.setName(member.getName());
        memberRepository.save(memberEntity);
    }

    public void deleteMember(int memberId) {
        memberRepository.deleteById(memberId);
    }

    public void updateMember(Long memberId, MemberDTO memberDetails) {
        Member existingMember = memberRepository.findById(memberId).orElseThrow();

        existingMember.setName(memberDetails.getName());

        memberRepository.save(existingMember);
    }

    public MemberDTO getMember(Long memberId) {
        Member member =  memberRepository.findById(memberId).orElseThrow();
        return new MemberDTO(member.getId(), member.getName());
    }

    public List<MemberDTO> getMembers() {
        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(member -> new MemberDTO(member.getId(), member.getName()))
                .collect(Collectors.toList());
    }

    public boolean existsById(Long id) {
        return memberRepository.existsById(id);
    }

}