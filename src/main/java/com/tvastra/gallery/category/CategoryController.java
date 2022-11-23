package com.tvastra.gallery.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<CategoryDTO> getCategory(){
        List<Category> categories = categoryService.getAllCategory();
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        return categoryDTOS;
    }

    @PostMapping
    public void postCategory(@RequestParam("category") String category){
        categoryService.save(category);
    }
}
