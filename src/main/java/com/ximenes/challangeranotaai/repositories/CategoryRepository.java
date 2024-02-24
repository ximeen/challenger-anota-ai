package com.ximenes.challangeranotaai.repositories;

import com.ximenes.challangeranotaai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> { }