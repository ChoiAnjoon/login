package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get(); // get 말고 다른것으로 꺼내는 것이 좋음 (optional 공부 필요)
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }

//        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
//        return byLoginId.filter(m -> m.getPassword().equals(password)).orElse(null);

        return memberRepository.findByLoginId(loginId) // optional type으로 return
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
