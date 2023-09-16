package com.project.expenses.client;

import com.project.expenses.dto.ExpenseDTO;
import com.project.expenses.dto.UserDTO;
import com.project.expenses.service.CategoryService;
import com.project.expenses.service.ExpenseService;
import com.project.expenses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
@Transactional
public class DataInitializer implements ApplicationRunner {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    ExpenseService expenseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long foodId = categoryService.saveCategory("FOOD");
        Long billsId = categoryService.saveCategory("BILLS");
        Long sportsId = categoryService.saveCategory("SPORTS");

        UserDTO userDTO = new UserDTO();
        userDTO.setBirthDate(LocalDate.now().minusYears(18L));
        userDTO.setName("Usuario Teste");
        userDTO.setPreferenceAmount(BigDecimal.valueOf(500L));
        userDTO.setPreferenceDescription("Limite de gasto para o mes de " + LocalDate.now().getMonth());
        Long userId = userService.save(userDTO);

        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setAmount(BigDecimal.TEN);
        expenseDTO.setDescription("Lanche");
        expenseDTO.setUserId(userId);
        expenseDTO.setCategoryIds(Set.of(foodId));
        expenseService.save(expenseDTO);

        ExpenseDTO expenseDTO1 = new ExpenseDTO();
        expenseDTO1.setDescription("AULA DE NATACAO");
        expenseDTO1.setAmount(BigDecimal.valueOf(100));
        expenseDTO1.setCategoryIds(Set.of(sportsId, billsId));
        expenseDTO1.setUserId(userId);
        expenseService.save(expenseDTO1);
    }

}
