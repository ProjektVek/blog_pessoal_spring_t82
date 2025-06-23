package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Categoria;

public interface TemaRepository extends JpaRepository<Categoria, Long> {
	
	/* SELECT * FROM tb_temas WHERE descricao LIKE "%?%"; */
	List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
