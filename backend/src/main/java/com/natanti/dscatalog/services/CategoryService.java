package com.natanti.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanti.dscatalog.dto.CategoryDTO;
import com.natanti.dscatalog.entities.Category;
import com.natanti.dscatalog.repositories.CategoryRepository;
import com.natanti.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return listDTO;
	}
	
	public CategoryDTO findById(Long id) {
		Optional<Category> entity = repository.findById(id);
		CategoryDTO dto = new CategoryDTO(entity.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada.")));
		return dto;
	}
}
