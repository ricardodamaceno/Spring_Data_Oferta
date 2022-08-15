package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudeCargoService {

	private final CargoRepository cargoRepository;	
	private Boolean system = true;
	
	public CrudeCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
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
		Cargo cargo =  new Cargo();
		System.out.println("Descricao  do cargo");
		String descricao = scanner.next(); //para pegar o valor inserido no console
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("SALVO!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		System.out.println("Descricao  do cargo");
		String descricao = scanner.next();
		
		Cargo cargo =  new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("ATUALIZADO!");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("DELETADO!");
	}
}