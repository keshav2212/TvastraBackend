package com.tvastra.category;

import com.tvastra.TvastraApplication;
import com.tvastra.gallery.category.Category;
import com.tvastra.gallery.category.CategoryRepository;
import com.tvastra.gallery.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TvastraApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Test
    public void shouldSaveCategoryWhenCategoryNameIsGiven() {
        String testCategory = "testCategory";
        Category category = Category.builder().name(testCategory).build();
        when(categoryRepository.save(category)).thenReturn(category);

        categoryService.save(testCategory);

        verify(categoryRepository, times(1)).save(category);

    }

    @Test
    public void shouldReturnCategoryWhenCategoryIdIsGiven() {
        Category expected = Category.builder().id(1L).build();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expected));

        Category actual = categoryService.getById(1L);

        assertEquals(expected, actual);
        verify(categoryRepository, times(1)).findById(anyLong());

    }

    @Test
    public void shouldReturnAllCategories(){
        Category firstCategory = Category.builder().build();
        Category secondCategory = Category.builder().build();
        List<Category> categories = new ArrayList<>();
        categories.add(firstCategory);
        categories.add(secondCategory);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> actualCategories = categoryService.getAllCategory();

        assertEquals(actualCategories, categories);
    }

}
