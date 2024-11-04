package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private  final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    * 회원가입
    * */

    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 안된다.
        // ctrl + T 누르고 Ex...Methode 누르면 메소드화 할수있다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        // cmd + opt + v Oprtional 변수 "" 으로 반환된다.

        // ifPresent 는 값이 있으면 {} 안에 있는 내용들을 반환한다.
        memberRepository.findByNmae(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        });
    }

    /*
    * 전체회원 조회
    * */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
