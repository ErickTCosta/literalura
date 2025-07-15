package com.erickcosta.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Integer downloadCount;

    private Integer anoNascimentoAutor;
    private Integer anoFalecimentoAutor;

    public Livro(){}

    public Livro(String titulo, String autor, String idioma, Integer downloadCount, Integer anoNascimentoAutor, Integer anoFalecimentoAutor) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.downloadCount = downloadCount;
        this.anoNascimentoAutor = anoNascimentoAutor;
        this.anoFalecimentoAutor = anoFalecimentoAutor;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public  Integer getAnoNascimentoAutor() {return anoNascimentoAutor;}

    public Integer getAnoFalecimentoAutor() { return anoFalecimentoAutor;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void  setAnoNascimentoAutor(Integer anoNascimentoAutor){this.anoNascimentoAutor = anoNascimentoAutor;}

    public void  setAnoFalecimentoAutor(Integer anoFalecimentoAutor) {this.anoFalecimentoAutor = anoFalecimentoAutor;}

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", downloadCount=" + downloadCount +
                ", anoNascimento=Autor" + anoNascimentoAutor +
                ", anoFalecimentoAutor" + anoFalecimentoAutor +
                '}';
    }
}
