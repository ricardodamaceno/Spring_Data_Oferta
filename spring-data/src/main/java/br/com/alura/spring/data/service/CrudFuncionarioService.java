package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {

	private final FuncionarioRepository funcionarioRepository;	
	private Boolean system = true;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
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
				visualizar(scanner);
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
		Funcionario funcionario =  new Funcionario();
		
		System.out.println("Nome do Funcionario");
		String nome = scanner.next(); 
		funcionario.setNome(nome);
		
		System.out.println("CPF do Funcionario");
		String cpf = scanner.next(); 
		funcionario.setCpf(cpf);
		
		System.out.println("Salario do Funcionario");
		BigDecimal salario = scanner.nextBigDecimal(); 
		funcionario.setSalario(salario);
		
//		System.out.println("Cargo do Funcionario");
//		String nomeCargo = scanner.next();
//		funcionario.setCargo(nomeCargo);
		
		funcionario.setDataContratacao(LocalDate.now());
		
		funcionarioRepository.save(funcionario);
		System.out.println("SALVO!");
	}
	
	private void atualizar(Scanner scanner) {
		Funcionario funcionario =  new Funcionario();
		
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		funcionario.setId(id);
		
		System.out.println("Nome do Funcionario");
		String nome = scanner.next(); 
		funcionario.setNome(nome);
		
		System.out.println("CPF do Funcionario");
		String cpf = scanner.next(); 
		funcionario.setCpf(cpf);
		
		System.out.println("Salario do Funcionario");
		BigDecimal salario = scanner.nextBigDecimal(); 
		funcionario.setSalario(salario);
		
		funcionario.setDataContratacao(LocalDate.now());
		
		funcionarioRepository.save(funcionario);
		System.out.println("SALVO!");
	}
	
	private void visualizar(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar?");
		Integer page =  scanner.nextInt();
		
		Pageable pageble = PageRequest.of(page, 3, Sort.by(Sort.Direction.ASC, "nome")); //ordena por nome
		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageble);
		
		System.out.println(funcionarios);
		System.out.println("Pagina atual " + funcionarios.getNumber());
		System.out.println("Total de elementos " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("DELETADO!");
	}
}