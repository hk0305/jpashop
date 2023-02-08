package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

/**
 * @author hk0305
 */
@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    /**
     * 실무에서는 다대다를 사용하지 않는다.
     * 다대다의 관계형 데이터 베이스에서 추가되는 컬럼이 반드시 있기 때문이다.
     * ex. 등록일, 수정일 등 추가 불가
     */
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    /**
     * 같은 엔티디에서의 셀프 연관관계 맵핑
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /**
     * 연관관계 편의 메서드
     * @param child
     */
    public void addChildCategory(Category child) {
        this.child.add(child);
        this.setParent(this);
    }

}
