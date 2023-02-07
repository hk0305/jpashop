package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hk0305
 */
@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    /**
     * 연관관계의 주인을 Order로 설정하여
     * mappedBy = "delivery"로 선언한다.
     * 상품 주문을 통해 배송 정보를 확인하기 때문이다.
     * 도메인 설계에 따라 Delivery가 연관관계의 주인이 될 수 있다.
     */
    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    /**
     * 배송상태
     * READY, COMP
     * 주의!
     * EnumType.ORDINAL 사용금지
     * => DB에 숫자로 들어가서 중간에 값이 추가되면 답없음(사라지면 XXX로 표기됨)
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
