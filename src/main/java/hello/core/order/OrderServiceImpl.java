package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // orderService 입장에서는 할인을 모르고, discountPolicy가 담당하고 결과만 반환받음 -> 단일책임원칙을 잘 지킨 예
    // 할인에 대한 변경이 필요하면 할인만 수정하면 된다.
    // 확장성을 위해 grade만 넘기는 것이 아니라 member를 넘긴다. (이 부분은 고민하면 됨)
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
