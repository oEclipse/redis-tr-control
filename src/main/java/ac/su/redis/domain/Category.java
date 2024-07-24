package ac.su.redis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
public class Category {
    @Id
    private Long id;

    private String name;
    private int depth;

    @ManyToOne
    @JoinColumn(name= "parent_id")
    private Category parent;  // 상위 카테고리 참조

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> children;  // 하위 카테고리 목록

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products; // 카테고리 지정된 상품 목록

    // id 번호가 오름차순 -> 카ㅔ고리 계츠구조가 상위에서 올라옴
    @JsonIgnore
    public List<Category> getParentsAsc() {
        List<Category> parents = new ArrayList<>();
        Category parentPointer = this.parent;
        while(parentPointer != null) {
            parents.add(parentPointer);
            parentPointer = parentPointer.getParent();
        }
        Collections.reverse(parents);
        return parents;
    }

    @JsonIgnore
    public List<Map<String,String>> getParentsMapAsc() {
        List<Map<String, String>> parents = new ArrayList<>();
        Category parentPointer = this.parent;
        while(parentPointer != null) {
            parents.add(Map.of(
                    parentPointer.getName(), "/categories/" +parentPointer.getId().toString()
            ));
            parentPointer = parentPointer.getParent();
        }
        Collections.reverse(parents);
        return parents;
    }
}

// 브랜드 의류 depth 1
// parent : null
// children : 브랜드 여성 의류, 브랜드 남성 의류, 브랜드 캐주얼 의류






