package dev.davivieira.specification;

import dev.davivieira.exception.GenericSpecificationException;

public abstract sealed class AbstractSpecification<T> implements Specification<T> permits AndSpecification, DuplicateCategorySpec, TransactionAmountSpec
{

    public abstract boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws GenericSpecificationException;

    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }
}
