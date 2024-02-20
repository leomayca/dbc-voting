package com.dbc.voting.controller;

import com.dbc.voting.entity.Member;
import com.dbc.voting.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Associados", description = " /api/v1/member, utilizado para gerenciamento de associados.")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // POST: Adicionar um novo membro
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member newMember = memberService.createMember(member);
        return ResponseEntity.ok(newMember);
    }

    // GET: Listar todos os membros
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getMembers();
        return ResponseEntity.ok(members);
    }

    // GET: Buscar um membro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMember(id);
        return ResponseEntity.ok(member);
    }

    // PUT: Atualizar um membro
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(id, memberDetails);
        return ResponseEntity.ok(updatedMember);
    }

    // DELETE: Deletar um membro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}