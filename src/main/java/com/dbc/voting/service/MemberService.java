package com.dbc.voting.service;

import com.dbc.voting.entity.Member;
import com.dbc.voting.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(int memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member updateMember(Long memberId, Member memberDetails) {
        Member existingMember = memberRepository.findById(memberId).orElseThrow();

        existingMember.setName(memberDetails.getName());

        return memberRepository.save(existingMember);
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public boolean existsById(Long id) {
        return memberRepository.existsById(id);
    }

}