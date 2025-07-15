package com.erickcosta.literatura;

import com.erickcosta.literatura.model.Livro;
import com.erickcosta.literatura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LivroService livroService;
	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args){
		Scanner scanner = new Scanner(System.in);
		int opcao = -1;

		while (opcao != 6) {
			System.out.println("\nEscolha o número de sua opção:");
			System.out.println("1 - Buscar livro pelo título (Gutendex)");
			System.out.println("2 - Listar livros registrados");
			System.out.println("3 - Listar autores registrados");
			System.out.println("4 - Listar autores vivos em um determinado ano");
			System.out.println("5 - Listar livros em um determinado idioma");
			System.out.println("6 - Sair");

			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					System.out.println("Insira o nome do livro que você deseja procurar:");
					String titulo = scanner.nextLine();

					Optional<Livro> livroOptional = livroService.buscarLivroPorTitulo(titulo);

					if (livroOptional.isPresent()) {
						Livro livro = livroOptional.get();
						System.out.println("----- LIVRO -----");
						System.out.println("Título: " + livro.getTitulo());
						System.out.println("Autor: " + livro.getAutor());
						System.out.println("Idioma: " + livro.getIdioma());
						System.out.println("Número de downloads: " + livro.getDownloadCount());
						System.out.println("-----------------");
					} else {
						System.out.println("Livro não encontrado.");
					}
					break;
				case 2:
					List<Livro> todosLivros = livroService.listarTodosLivros();
					System.out.println("\nLivros registrados:");
					todosLivros.forEach(System.out::println);
					break;
				case 3:
					Set<String> autores = livroService.listarAutores();
					System.out.println("\nAutores registrados:");
					autores.forEach(System.out::println);
					break;
				case 4:
					System.out.println("Digite o ano para verificar autores vivos:");
					Integer ano = scanner.nextInt();
					scanner.nextLine();
					Set<String> autoresVivos = livroService.listarAutoresVivosNoAno(ano);
					System.out.println("\nAutores vivos em " + ano + ":");
					autoresVivos.forEach(System.out::println);
					break;
				case 5:
					System.out.println("Digite o código do idioma (ex: en, pt, fr):");
					String idioma = scanner.nextLine();
					List<Livro> livrosPorIdioma = livroService.listarLivrosPorIdioma(idioma);
					System.out.println("\nLivros no idioma " + idioma + ":");
					livrosPorIdioma.forEach(System.out::println);
					break;
				case 6:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção ainda não implementada.");
					break;
			}
		}
		scanner.close();
	}
}
