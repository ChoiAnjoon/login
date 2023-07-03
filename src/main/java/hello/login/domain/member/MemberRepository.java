package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence=0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member m : all) {
//            if (m.getLoginId().equals(loginId)) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();

        // 위의 코드를 lamda와 스트림 사용
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst(); // 먼저 나오는애 받아다가 바로 반환 --> 반환 타입은 optional
        // 조건에 맞는 요소 하나를 찾는 것, 조건에 맞는 것이 없을 수 도(null)있기 때문에 Optional 타입으로 반환
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
