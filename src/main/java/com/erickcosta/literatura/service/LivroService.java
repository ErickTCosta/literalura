package com.erickcosta.literatura.service;


import com.erickcosta.literatura.dto.GutendexBook;
import com.erickcosta.literatura.dto.GutendexResponse;
import com.erickcosta.literatura.model.Livro;
import com.erickcosta.literatura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String URL_API = "https://gutendex.com/books/?search=";

    public List<Livro> buscarSalvarLivros(String termo) {
        String url = URL_API + termo;

        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        List<Livro> livros = new ArrayList<>();

        if (response != null && response.getResults() != null) {
            for (GutendexBook b : response.getResults()) {
                String autor = b.getAuthors().isEmpty() ? "Desconhecido" : b.getAuthors().get(0).getName();
                Integer birth = b.getAuthors().isEmpty() ? null : b.getAuthors().get(0).getBirth_year();
                Integer death = b.getAuthors().isEmpty() ? null : b.getAuthors().get(0).getDeath_year();
                String idioma = b.getLanguages().isEmpty() ? "N/A" : b.getLanguages().get(0);
                Integer downloads = b.getDownload_count() != null ? b.getDownload_count() : 0;


                Livro livro = new Livro(b.getTitle(), autor, idioma, downloads, birth, death);
                livros.add(livro);
                livroRepository.save(livro);
            }
        }
        return livros;
    }
    public List<Livro> listarTodosLivros(){
        return livroRepository.findAll();
    }

    public Set<String> listarAutores(){
        return livroRepository.findAll().stream()
                .map(Livro::getAutor)
                .collect(Collectors.toSet());
    }

    public List<Livro> listarLivrosPorIdioma(String idioma){
        return livroRepository.findByIdioma(idioma);
    }

    public Set<String> listarAutoresVivosNoAno(Integer ano) {
        return livroRepository.findAll().stream()
                .filter((l -> (l.getAnoNascimentoAutor() == null || l.getAnoNascimentoAutor() <= ano) &&
                        l.getAnoFalecimentoAutor()== null || l.getAnoFalecimentoAutor() >=ano))
                .map(Livro::getAutor)
                .collect(Collectors.toSet());
    }
}
