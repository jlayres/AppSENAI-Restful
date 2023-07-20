package br.com.senai.inicializacao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senai.entity.Estudante;
import br.com.senai.entity.Professor;
import br.com.senai.service.EstudanteService;
import br.com.senai.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private EstudanteService estudanteService;

	@Autowired
	private ProfessorService professorService;

	/*
	 * @Autowired private EstudanteRepository repo;
	 */

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Estudante estudante1 = new Estudante();

		estudante1.setNome("Jefferson");
		estudante1.setSobreNome("Brandão");
		estudante1.setEmail("jebrandao@firjan.com.br");
		estudante1.setDataNascimento(LocalDate.of(1980, 10, 05));

		estudanteService.salvar(estudante1);

		Estudante estudante2 = new Estudante();
		estudante2.setNome("Fabricio");
		estudante2.setSobreNome("Curvello");
		estudante2.setEmail("fcurvello@firjan.com.br");
		estudante2.setDataNascimento(LocalDate.of(1975, 9, 04));

		estudanteService.salvar(estudante2);

		Estudante estudante3 = new Estudante();
		estudante3.setNome("Vicente");
		estudante3.setSobreNome("Orsino");
		estudante3.setEmail("vicente@firjan.com.br");
		estudante3.setDataNascimento(LocalDate.of(1965, 6, 14));

		estudanteService.salvar(estudante3);

		// repo.saveAll(Arrays.asList(estudante1, estudante2, estudante3));

		Professor professor1 = new Professor();

		professor1.setNome("Marcelo");
		professor1.setSobreNome("Estruc");
		professor1.setEspecializacao("Desenvolvimento");

		professorService.salvar(professor1);

		Professor professor2 = new Professor();

		professor2.setNome("Ebenezer");
		professor2.setSobreNome("Nepomuceno");
		professor2.setEspecializacao("Infra");

		professorService.salvar(professor2);

		Professor professor3 = new Professor();

		professor3.setNome("George");
		professor3.setSobreNome("Kleinau");
		professor3.setEspecializacao("Infra");

		professorService.salvar(professor3);

		// buscarTodosOsEstudantes
		List<Estudante> listaEstudante = estudanteService.buscarTodosEstudantes();
		listaEstudante.forEach(estudante -> System.out.println(estudante.getNome())); // lambda

		// buscar um estudante pelo id
		Estudante estudante = estudanteService.buscarEstudantePorId(1);
		System.out.println(estudante.getNome());

		// excluir estudante por id
		Boolean flag = estudanteService.deleteEstudante(1);
		System.out.println(flag);

		// alterar estudante
		Estudante estudante4 = new Estudante();
		estudante4.setNome("Vicente");
		estudante4.setSobreNome("Orsino da Silva");
		estudante4.setEmail("viasilva@firjan.com.br");
		estudante4.setDataNascimento(LocalDate.of(1965, 6, 14));
		Estudante estudanteAlterado = estudanteService.updateEstudante(3, estudante4);
		System.out.println(estudanteAlterado.getEmail());

		// buscarTodosOsProfessores
		List<Professor> listaProfessor = professorService.buscarTodosProfessores();
		listaProfessor.forEach(professor -> System.out.println(professor.getNome())); // lambda

		// buscar um professor pela matricula
		Professor professor = professorService.buscarProfessorPorMatricula(1);
		System.out.println(professor.getNome());

		// Excluir professor por id
		Boolean flagProf = professorService.deletarProfessor(2);
		System.out.println(flagProf);

		// alterar professor
		Professor professor4 = new Professor();
		professor4.setNome("Leonardo");
		professor4.setSobreNome("Cardia");
		professor4.setEspecializacao("IA");
		Professor professorAlterado = professorService.updateProfessor(3, professor4);
		System.out.println(professorAlterado.getEspecializacao());
	}

}
