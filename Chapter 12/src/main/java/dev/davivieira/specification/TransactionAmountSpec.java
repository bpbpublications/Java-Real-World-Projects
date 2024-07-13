package dev.davivieira.specification;

import dev.davivieira.exception.GenericSpecificationException;

public final class TransactionAmountSpec extends AbstractSpecification<Double> {

    @Override
    public boolean isSatisfiedBy(Double amount) {
        return amount > 0;
    }

    @Override
    public void check(Double amount) throws GenericSpecificationException {
        if(!isSatisfiedBy(amount))
            throw new GenericSpecificationException("Transaction value 0 is invalid");
    }
}
