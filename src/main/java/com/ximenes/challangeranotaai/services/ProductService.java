package com.ximenes.challangeranotaai.services;

import com.ximenes.challangeranotaai.domain.category.Category;
import com.ximenes.challangeranotaai.domain.category.CategoryDTO;
import com.ximenes.challangeranotaai.domain.category.exceptions.CategoryNotFoundException;
import com.ximenes.challangeranotaai.domain.product.Product;
import com.ximenes.challangeranotaai.domain.product.ProductDTO;
import com.ximenes.challangeranotaai.domain.product.exceptions.ProductNotFoundException;
import com.ximenes.challangeranotaai.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository repository;

    public Product insert(ProductDTO productData){
        Category category = this.categoryService
                .getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }
    public List<Product> getAll(){return this.repository.findAll();}
    public Product update(String id, ProductDTO productData){
        Product product = this.repository
                .findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());
        this.repository.save(product);
        return product;
    }
    public void delete(String id){
        Product product = this.repository
                .findById(id)
                .orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }

}
