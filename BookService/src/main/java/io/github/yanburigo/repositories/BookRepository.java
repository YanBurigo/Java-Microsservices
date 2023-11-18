package io.github.yanburigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.yanburigo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
