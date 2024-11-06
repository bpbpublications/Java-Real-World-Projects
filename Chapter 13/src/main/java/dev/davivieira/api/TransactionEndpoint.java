package dev.davivieira.api;

import dev.davivieira.data.entity.Transaction;
import dev.davivieira.data.repository.AccountRepository;
import dev.davivieira.data.repository.CategoryRepository;
import dev.davivieira.data.repository.TransactionRepository;
import dev.davivieira.service.TransactionService;
import dev.davivieira.service.payload.TransactionPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionEndpoint {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    private TransactionEndpoint(TransactionService transactionService,
                                TransactionRepository transactionRepository,
                                AccountRepository accountRepository,
                                CategoryRepository categoryRepository
                               ) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/transaction")
    public void createTransaction(@RequestBody TransactionPayload transactionPayload) throws Exception {
        var account = accountRepository.findById(transactionPayload.getAccountId()).get();
        transactionService.createTransaction(account, transactionPayload);
    }

    @PutMapping("/{categoryId}/{transactionId}")
    public void addTransactionToCategory(@PathVariable String categoryId,
                                          @PathVariable String transactionId
    ) {
        var category = categoryRepository.findById(categoryId).get();
        var transaction = transactionRepository.findById(transactionId).get();
        transactionService.addTransactionToCategory(category, transaction);
    }

    @DeleteMapping("/{categoryId}/{transactionId}")
    public void removeTransactionFromCategory(@PathVariable String categoryId,
                                          @PathVariable String transactionId
    ) {
        var category = categoryRepository.findById(categoryId).get();
        var transaction = transactionRepository.findById(transactionId).get();
        transactionService.removeTransactionFromCategory(category, transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> allTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }
}