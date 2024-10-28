package com.example.springgraphql.repositories;

import com.example.springgraphql.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
