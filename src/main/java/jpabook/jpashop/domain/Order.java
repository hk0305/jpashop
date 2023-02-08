package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * cascade = ALL 은 persist를 전파한다.
     * 즉 oder를 수정하면 orderItems도 같이 persist 된다.
     */
    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 연관관계의 주인을 Order로 설정하여
     * JoinColumn(name = "delivery_id")로 선언한다.
     * 상품 주문을 통해 배송 정보를 확인하기 때문이다.
     * 도메인 설계에 따라 Delivery가 연관관계의 주인이 될 수 있다.
     */
    @OneToOne(fetch = LAZY, cascade = ALL)
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

    /**
     * // == 연관관계 편의 메서드 == //
     * @param member
     */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    /**
     * 연관관계를 컨트롤하는 쪽에서
     * 연관관계 편의 메서드를 제공하자
     * @param orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /**
     * 양방향 연관관계의 경우도 마찬가지로,
     * 연관관계 편의 메서드를 제공하자
     * @param delivery
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
