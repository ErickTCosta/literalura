package com.erickcosta.literatura.repository;

import com.erickcosta.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);
}
