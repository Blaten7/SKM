package com.example.skmj.service;

import com.example.skmj.entity.Member;
import com.example.skmj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public void register(String username, String password) {
        Member m = new Member();
        m.setUsername(username);
        m.setPassword(encoder.encode(password));
        m.setCreated_at(LocalDateTime.now());

        memberRepository.save(m);
    }
}
