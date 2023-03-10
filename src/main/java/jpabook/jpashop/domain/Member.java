package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hk0305
 */
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memeber_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    /**
     * order의 member의 값에 맵핑
     * 컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.
     * 컬렉션을 바꾸지 말자
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
