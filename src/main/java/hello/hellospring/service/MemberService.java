package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository; // 회원 리포지토리가 있어야 함

    @Autowired
    public MemberService(MemberRepository memberRepository) { // memberRepository를 직접 내가 new해서 생성하는 게 아닌, 외부에서 넣어주게 바꿈
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 저장해주면 회원 가입 끝!
        return member.getId(); // id만 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // findById 했을 때 memberId 반환하도록
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
