package module.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, String> {

}
