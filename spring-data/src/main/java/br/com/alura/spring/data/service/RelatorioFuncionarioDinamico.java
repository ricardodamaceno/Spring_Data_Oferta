package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario");
		BigDecimal salario = scanner.nextBigDecimal();
		
		if(salario.doubleValue() == 0) {
			salario = null;
		}
		
		System.out.println("Digite a data de Contratacao");
		String data = scanner.next();
		
		LocalDate dataContratacao = null;
		if(data.equalsIgnoreCase("NULL")) {
			data = null;
		}else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionario = funcionarioRepository
				.findAll(Specification
						.where
						(SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						);
		funcionario.forEach(System.out::println);
	}
}