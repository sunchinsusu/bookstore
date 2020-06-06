package com.ntt.repository;

import com.ntt.entity.Book;
import com.ntt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findById(int id);
    List<Book> findBookByNameLike(String name);
    List<Book> findByAuthor(String name);
    List<Book> findByCategory(Category category);
    List<Book> findBySalePriceBetween(float min, float max);
}
