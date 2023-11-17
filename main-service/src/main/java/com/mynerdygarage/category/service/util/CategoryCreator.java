package com.mynerdygarage.category.service.util;

import com.mynerdygarage.category.dto.CategoryFullDto;
import com.mynerdygarage.category.dto.NewCategoryDto;
import com.mynerdygarage.category.model.Category;
import com.mynerdygarage.user.model.User;

public class CategoryCreator {

    public static Category createFromNewDto(NewCategoryDto newCategoryDto, User creator) {

        Category category = new Category();

        category.setName(newCategoryDto.getName());
        category.setDescription(newCategoryDto.getDescription());
        category.setCreator(creator);

        return category;
    }

    public static Category createFromFullDto(CategoryFullDto categoryFullDto) {

        Category category = new Category();

        category.setName(categoryFullDto.getName());
        category.setDescription(categoryFullDto.getDescription());
        category.setCreator(categoryFullDto.getCreator());

        return category;
    }
}
