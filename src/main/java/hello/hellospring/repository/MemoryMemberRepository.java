package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 저장할 곳
    private static long sequence = 0L; // sequence: 0, 1, 2 이렇게 키값 생성해주는 애
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member에 id값 세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store에서 get해서 받아옴. null이면 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) { // 받아온 name과 name이 같은 거 하나라도 있으면 return
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // values는 store에 있는 Member들
    }

    public void clearStore() {
        store.clear();
    }
}
