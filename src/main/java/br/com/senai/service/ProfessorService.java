package br.com.senai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.senai.entity.Estudante;
import br.com.senai.entity.Professor;
import br.com.senai.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepo;
	
	public Professor salvar(Professor professor) {
		return professorRepo.save(professor);
	}
	
	public List<Professor> buscarTodosProfessores() {
		return professorRepo.findAll();
	}
	
	public Professor buscarProfessorPorMatricula(Integer matricula) {
		return professorRepo.findById(matricula).orElse(null);
	}
	
	public Boolean deletarProfessor(Integer matricula) {
		Professor professor = professorRepo.findById(matricula).orElse(null);
		if(professor != null) {
			professorRepo.deleteById(matricula);
			return true;
		}
		return false;
	}
	
	public Professor updateProfessor(Integer matricula, Professor professor) {
		Professor professorBD = professorRepo.findById(matricula).orElse(null);
		if(professorBD != null) {
			professorBD.setNome(professor.getNome());
			professorBD.setSobreNome(professor.getSobreNome());
			professorBD.setEspecializacao(professor.getEspecializacao());
			return professorRepo.save(professorBD);
		} else {
			return null;
		}
	}
	
	public Page<Professor> buscarProfessorPorPaginacao(PageRequest page) {
		return professorRepo.findAll(page);
		
	}
}
