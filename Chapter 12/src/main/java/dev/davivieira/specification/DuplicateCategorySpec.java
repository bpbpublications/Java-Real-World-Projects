package dev.davivieira.specification;

import dev.davivieira.entity.Category;
import dev.davivieira.exception.GenericSpecificationException;

import java.util.List;

public final class DuplicateCategorySpec extends AbstractSpecification<Category> {

    private final List<Category> categories;

    public DuplicateCategorySpec(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean isSatisfiedBy(Category category) {
        return categories.contains(category);
    }

    @Override
    public void check(Category category) throws GenericSpecificationException {
        if(isSatisfiedBy(category))
            throw new GenericSpecificationException("Category already exists");
    }
}
