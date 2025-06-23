package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Categoria;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		// SELECT * FROM tb_temas
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getAllByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria tema){
		
		if(tema.getId() == null)
			return ResponseEntity.badRequest().build();
		
		if(temaRepository.existsById(tema.getId()))
			return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Optional<Categoria> tema = temaRepository.findById(id);
		
		if(tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		temaRepository.deleteById(id); // Delete * FROM tb_temas WHERE id=?
		
	}
}
