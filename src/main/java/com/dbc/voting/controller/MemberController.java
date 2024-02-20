package com.dbc.voting.controller;

import com.dbc.voting.entity.Member;
import com.dbc.voting.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Associados", description = " /api/v1/member, utilizado para gerenciamento de associados.")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        try {
            Member newMember = memberService.createMember(member);
            return ResponseEntity.ok(newMember);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        try {
            List<Member> members = memberService.getMembers();
            return ResponseEntity.ok(members);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        try {
            Member member = memberService.getMember(id);
            return ResponseEntity.ok(member);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        try {
            Member updatedMember = memberService.updateMember(id, memberDetails);
            return ResponseEntity.ok(updatedMember);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable int id) {
        if (!memberService.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}