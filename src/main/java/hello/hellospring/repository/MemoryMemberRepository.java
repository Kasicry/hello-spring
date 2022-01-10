package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;



public class MemoryMemberRepository implements MemberRepository{


    // HashMap - 키나 값에 Null 저장이 가능하다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 실무에서는 동시성문제 때문에 AtomicLong을 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
            // Null을 처리하는 방법중 하나로 ofNullable 사용
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // findAny(), 처음부터 끝까지 찾고 없으면 optional에 null
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }
}

// 검증하는 가장 좋은방법은 테스트케이스를 작성하는 것!
