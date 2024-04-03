package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member(); // 객체 생성
        member.setName("spring");
        repository.save(member); // 저장

        Member result = repository.findById(member.getId()).get(); // id로 찾기. Optional에서 꺼낼 때는 .get()
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member(); // 객체 생성
        member1.setName("spring1");
        repository.save(member1); // 저장

        Member member2 = new Member(); // 객체 생성
        member2.setName("spring2");
        repository.save(member2); // 저장

        Member result = repository.findByName("spring1").get(); // name으로 찾기. Optional에서 꺼낼 때는 .get()

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member(); // 객체 생성
        member1.setName("spring1");
        repository.save(member1); // 저장

        Member member2 = new Member(); // 객체 생성
        member2.setName("spring2");
        repository.save(member2); // 저장

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
