package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;	
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("QUAL ACÃO VOCÊ QUER EXECUTAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - BUSCAR FUNCIONARIO POR NOME");
			System.out.println("2 - BUSCAR FUNCIONARIO POR NOME, SALÁRIO, DATA CONTRATAÇÃO");
			System.out.println("3 - BUSCAR FUNCIONARIO DATA CONTRATAÇÃO");
			System.out.println("4 - PESQUISA FUNCIONARIO SALÁRIO");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscarFuncionarioNome(scanner);
				break;
			case 2:
				buscarFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacaoMaior(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscarFuncionarioNome(Scanner scanner) {
		System.out.println("Nome do Funcionario");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscarFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual o Nome do Funcionario?");
		String nome = scanner.next();
		
		System.out.println("Qual a Data de Contratação?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual o Salário?");
		BigDecimal salario = scanner.nextBigDecimal();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacaoMaior(Scanner scanner) {
		System.out.println("Qual a Data de Contratação?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: Id: " + f.getId() + " | nome: " + f.getNome()
		+ " | salário: " + f.getSalario()));
	}
}
