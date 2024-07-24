package ac.su.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor
public class CategoryResponseDTO {
    private String id;
    private String name;
    private int depth;

    // 가장 단순한 형태로 참조 문제 해결
    private Long parentId;  // 상위 카테고리 객체 타입이 아니라 id 로만 다루기

    public static CategoryResponseDTO fromCategory(Category category) {
        return new CategoryResponseDTO(
                "/categories/" +category.getId(),
                category.getName(),
                category.getDepth(),
                category.getParent() != null ? category.getParent().getId() : null,
                null
        );
    }

    private List<Map<String,String>> parentsUrl;

    public static CategoryResponseDTO fromCategoryWithParentsUrlList(Category category) {
        return new CategoryResponseDTO(
                "/categories/" +category.getId(),
                category.getName(),
                category.getDepth(),
                category.getParent() != null ? category.getParent().getId() : null,
                category.getParentsMapAsc()
        );
    }
}
