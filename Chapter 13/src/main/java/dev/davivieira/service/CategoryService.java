package dev.davivieira.service;

import dev.davivieira.data.entity.Account;
import dev.davivieira.data.entity.Category;
import dev.davivieira.data.repository.AccountRepository;
import dev.davivieira.service.payload.CategoryPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final AccountRepository accountRepository;

    @Autowired
    public CategoryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createCategory(Account account, CategoryPayload categoryPayload) throws Exception {
        var category = getCategory(categoryPayload);
        var categories = account.getCategories();

        validateCategory(account, category);

        categories.add(category);
        accountRepository.save(account);
    }

    private void validateCategory(Account account, Category category) throws Exception {
        var categories = account.getCategories();
        if(categories.contains(category)) {
            throw new Exception("Category already exists in this account");
        }
    }

    private Category getCategory(CategoryPayload categoryPayload) {
        return Category.builder()
                .name(categoryPayload.getName())
                .id(categoryPayload.getId())
                .transactions(List.of())
                .build();
    }
}