package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	/* SELECT * FROM tb_temas WHERE descricao LIKE "%?%"; */
	List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
