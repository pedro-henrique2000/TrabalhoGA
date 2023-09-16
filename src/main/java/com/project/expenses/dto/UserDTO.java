package com.project.expenses.dto;

import com.project.expenses.domain.User;
import com.project.expenses.domain.UserExpensePreference;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserDTO {

    private String name;
    private LocalDate birthDate;
    private BigDecimal preferenceAmount;
    private String preferenceDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getPreferenceAmount() {
        return preferenceAmount;
    }

    public void setPreferenceAmount(BigDecimal preferenceAmount) {
        this.preferenceAmount = preferenceAmount;
    }

    public String getPreferenceDescription() {
        return preferenceDescription;
    }

    public void setPreferenceDescription(String preferenceDescription) {
        this.preferenceDescription = preferenceDescription;
    }

    public User toEntity() {
        User user = User.UserBuilder.anUser()
                .withBirthDate(birthDate)
                .withName(name)
                .build();
        if (preferenceDescription != null) {
            UserExpensePreference userExpensePreference = toUserExpensePreference(preferenceAmount, preferenceDescription);
            user.setUserExpensePreference(userExpensePreference);
        }
        return user;
    }

    private UserExpensePreference toUserExpensePreference(BigDecimal preferenceAmount, String preferenceDescription) {
        return UserExpensePreference.UserExpensePreferenceBuilder.anUserExpensePreference()
                .withAmount(preferenceAmount)
                .withDescription(preferenceDescription)
                .withIsActive(true)
                .build();
    }
}
