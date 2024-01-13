package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션의 설정정보, 구성정보
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository() 호출
    // @Bean orderService -> new MemoryMemberRepository() 호출
    // 이러면 싱글톤이 깨지는 것 아닐까? 라고 생각이 들 수 있다. -> 테스트 코드로 실험

    // 호출 예상 (메서드 호출 순서는 보장하지 않음) -> 실제는 1번씩만 호출됨
    // call AppConfig.memberService 호출
    // call AppConfig.memberRepository 호출
    // call AppConfig.memberRepository 호출
    // call AppConfig.orderService 호출
    // call AppConfig.memberRepository 호출

    // 구현
    @Bean // 스프링 컨테이너에 등록됨, 기본적으로 메서드명인 memberService로 등록됨
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // 역할, return 타입은 인터페이스로 선택
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
