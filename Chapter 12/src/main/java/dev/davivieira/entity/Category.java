package dev.davivieira.entity;

import dev.davivieira.specification.DuplicateCategorySpec;
import dev.davivieira.vo.Id;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public record Category(Id id, String name, List<Transaction> transactions) {

    public static Category createCategory(Account account, String name) {
        var category = getCategory(name);
        var categories = account.categories();

        new DuplicateCategorySpec(categories).check(category);

        categories.add(category);
        return category;
    }

    private static Category getCategory(String name) {
        return Category.builder()
                .name(name)
                .id(Id.withoutId())
                .transactions(new ArrayList<>())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
