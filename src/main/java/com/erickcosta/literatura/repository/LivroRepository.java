package com.erickcosta.literatura.repository;

import com.erickcosta.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
