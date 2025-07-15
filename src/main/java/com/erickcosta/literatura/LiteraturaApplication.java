package com.erickcosta.literatura;

import com.erickcosta.literatura.model.Livro;
import com.erickcosta.literatura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

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
					if (todosLivros.isEmpty()) {
						System.out.println("Nenhum livro registrado.");
					} else {
						for (Livro livro : todosLivros) {
							System.out.println("----- LIVRO -----");
							System.out.println("Título: " + livro.getTitulo());
							System.out.println("Autor: " + livro.getAutor());
							System.out.println("Idioma: " + livro.getIdioma());
							System.out.println("Número de downloads: " + livro.getDownloadCount());
							System.out.println("-----------------");
						}
					}
					break;
				case 3:
					List<Livro> todosLivrosAutores = livroService.listarTodosLivros();

					Map<String, List<Livro>> livrosPorAutor = todosLivrosAutores.stream()
							.collect(Collectors.groupingBy(Livro::getAutor));

					for (Map.Entry<String, List<Livro>> entry : livrosPorAutor.entrySet()) {
						String autor = entry.getKey();
						List<Livro> livrosDoAutor = entry.getValue();

						// Pegamos primeiro livro só para exibir birth e death year (pois todos do mesmo autor devem ter igual)
						Livro exemploLivro = livrosDoAutor.get(0);

						System.out.println("Autor: " + autor);
						System.out.println("Ano de nascimento: " +
								(exemploLivro.getAnoNascimentoAutor() != null ? exemploLivro.getAnoNascimentoAutor() : "Desconhecido"));
						System.out.println("Ano de falecimento: " +
								(exemploLivro.getAnoFalecimentoAutor() != null ? exemploLivro.getAnoFalecimentoAutor() : "Desconhecido"));

						// Lista títulos
						List<String> titulos = livrosDoAutor.stream()
								.map(Livro::getTitulo)
								.toList();
						System.out.println("Livros: " + titulos);
						System.out.println("-----------------");
					}

					break;
				case 4:
					System.out.println("Insira o ano que deseja pesquisar:");
					int ano = scanner.nextInt();
					scanner.nextLine(); // consumir newline

					List<Livro> todosLivrosAutoresVIvos = livroService.listarTodosLivros();

					Map<String, List<Livro>> livrosPorAutorVivos = todosLivrosAutoresVIvos.stream()
							.collect(Collectors.groupingBy(Livro::getAutor));

					boolean encontrou = false;

					for (Map.Entry<String, List<Livro>> entry : livrosPorAutorVivos.entrySet()) {
						String autor = entry.getKey();
						List<Livro> livrosDoAutor = entry.getValue();

						Livro exemploLivro = livrosDoAutor.get(0);
						Integer birthYear = exemploLivro.getAnoNascimentoAutor();
						Integer deathYear = exemploLivro.getAnoFalecimentoAutor();

						// Verifica se o autor estava vivo no ano
						if (birthYear != null && deathYear != null && birthYear <= ano && deathYear >= ano) {
							System.out.println("Autor: " + autor);
							System.out.println("Ano de nascimento: " + birthYear);
							System.out.println("Ano de falecimento: " + deathYear);
							List<String> titulos = livrosDoAutor.stream()
									.map(Livro::getTitulo)
									.toList();
							System.out.println("Livros: " + titulos);
							System.out.println("-----------------");
							encontrou = true;
						}
					}

					if (!encontrou) {
						System.out.println("Nenhum autor encontrado vivo neste ano.");
					}

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
