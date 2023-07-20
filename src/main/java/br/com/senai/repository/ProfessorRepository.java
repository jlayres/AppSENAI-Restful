package br.com.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
