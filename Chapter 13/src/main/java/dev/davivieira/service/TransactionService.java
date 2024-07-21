package dev.davivieira.service;

import dev.davivieira.data.entity.Account;
import dev.davivieira.data.entity.Category;
import dev.davivieira.data.entity.Transaction;
import dev.davivieira.data.repository.AccountRepository;
import dev.davivieira.data.repository.CategoryRepository;
import dev.davivieira.service.payload.TransactionPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(CategoryRepository categoryRepository,
                              AccountRepository accountRepository) {
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    public void createTransaction(Account account, TransactionPayload transactionPayload) throws Exception {
        validateAmount(transactionPayload);
        var transaction = Transaction.builder()
                .id(transactionPayload.getId())
                .name(transactionPayload.getName())
                .amount(transactionPayload.getAmount())
                .type(transactionPayload.getType())
                .timestamp(transactionPayload.getTimestamp())
                .build();
        account.getTransactions().add(transaction);
        accountRepository.save(account);
    }

    private void validateAmount(TransactionPayload transactionPayload) throws Exception {
        var amount = transactionPayload.getAmount();
        if(!(amount > 0)) {
            throw new Exception("Transaction value 0 is invalid");
        }
    }

    public boolean addTransactionToCategory(Category category, Transaction transaction) {
        category.getTransactions().add(transaction);
        categoryRepository.save(category);
        return true;
    }

    public boolean removeTransactionFromCategory(Category category, Transaction transaction) {
        category.getTransactions().remove(transaction);
        categoryRepository.save(category);
        return true;
    }
}