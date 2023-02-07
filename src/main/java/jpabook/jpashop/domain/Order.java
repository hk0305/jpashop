package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hk0305
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 연관관계의 주인을 Order로 설정하여
     * JoinColumn(name = "delivery_id")로 선언한다.
     * 상품 주문을 통해 배송 정보를 확인하기 때문이다.
     * 도메인 설계에 따라 Delivery가 연관관계의 주인이 될 수 있다.
     */
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    /**
     * 자바8부터 하이버네이트가 날짜 타입 지원
     */
    private LocalDateTime orderDate;

    /**
     * 주문상태
     * [ORDER, CACEL]
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
