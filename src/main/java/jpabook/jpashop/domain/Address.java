package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * @author hk0305
 */
@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;

    /**
     * JPA 스펙상 기본 생성자 추가
     * JPA 스펙상 protected로 설정하는 것이 좋다.
     * cd. 리플렉션(프록시) 기술
     */
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
