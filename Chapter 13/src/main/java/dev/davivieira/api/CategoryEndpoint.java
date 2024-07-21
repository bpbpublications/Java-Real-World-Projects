package dev.davivieira.api;

import dev.davivieira.data.entity.Category;
import dev.davivieira.data.repository.AccountRepository;
import dev.davivieira.data.repository.CategoryRepository;
import dev.davivieira.service.CategoryService;
import dev.davivieira.service.payload.CategoryPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryEndpoint {
    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;


    @Autowired
    private CategoryEndpoint(CategoryService categoryService,
                             CategoryRepository categoryRepository,
                             AccountRepository accountRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;

    }

    @PostMapping("/category")
    private void createCategory(@RequestBody CategoryPayload categoryPayload) throws Exception {
        var account = accountRepository.findById(categoryPayload.getAccountId()).get();
        categoryService.createCategory(account, categoryPayload);
    }

    @GetMapping("/categories")
    private List<Category> allCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
}