package com.Sigma.SigmaBackEnd.repository;

import com.Sigma.SigmaBackEnd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
