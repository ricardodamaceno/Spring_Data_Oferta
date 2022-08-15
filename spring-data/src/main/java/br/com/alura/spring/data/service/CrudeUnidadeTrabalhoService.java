package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudeUnidadeTrabalhoService {

	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;	
	private Boolean system = true;
	
	public CrudeUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("QUAL ACÃO VOCÊ QUER EXECUTAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - SALVAR");
			System.out.println("2 - ATUALIZAR");
			System.out.println("3 - VISUALIZAR");
			System.out.println("4 - DELETAR");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		UnidadeTrabalho unidadeTrabalho =  new UnidadeTrabalho();
		
		System.out.println("Digite o nome da unidade");
		String descricao = scanner.next();
		unidadeTrabalho.setDescricao(descricao);
		
		System.out.println("Endereço da Unidade");
		String endereco = scanner.next();
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("SALVO!");
	}
	
	private void atualizar(Scanner scanner) {
		UnidadeTrabalho unidadeTrabalho =  new UnidadeTrabalho();
		
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		unidadeTrabalho.setId(id);
		
		System.out.println("Digite o nome da unidade");
		String descricao = scanner.next();
		unidadeTrabalho.setDescricao(descricao);
		
		System.out.println("Endereço da Unidade");
		String endereco = scanner.next();
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("ATUALIZADO!");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findAll();
		unidadeTrabalho.forEach(trabalho -> System.out.println(trabalho));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("DELETADO!");
	}
}