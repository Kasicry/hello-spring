package hello.hellospring.repository;

import hello.hellospring.domain.Member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository(); //MemberRepository 인터페이스기능 get

    // 모든 테스트는 순서가 정해져있지않다.
    // 테스트1개가 끝나면 그 데이터들은 초기화가 되어야한다.

    @AfterEach // 하나의 테스트가 끝날때마다 실행
    public void afterEach(){

        repository.clearStore();
    }



    @Test
    public void save(){

        Member member =  new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + F6 : Rename 한번에 하는 기능!
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + F6 : Rename 한번에 하는 기능!
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}

// TDD : 테스트주도개발 - 테스트를 먼저 만들고 테스트함
// 여러 명이 개발할 때는 특히 테스트코드가 꼭 필요!!!