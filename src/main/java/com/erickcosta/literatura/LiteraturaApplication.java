package com.erickcosta.literatura;

import com.erickcosta.literatura.model.Livro;
import com.erickcosta.literatura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

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
					System.out.println("Digite o termo de busca:");
					String termo = scanner.nextLine();
					List<Livro> livros = livroService.buscarSalvarLivros(termo);
					System.out.println("\nLivros encontrados e salvos:");
					livros.forEach(System.out::println);
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
