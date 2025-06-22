package com.natanti.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.natanti.dscatalog.dto.ProductDTO;
import com.natanti.dscatalog.entities.Product;
import com.natanti.dscatalog.repositories.ProductRepository;
import com.natanti.dscatalog.services.exceptions.DatabaseException;
import com.natanti.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		Page<Product> list = repository.findAll(pageable);
		return list.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new EntityNotFoundException("Produto não encontrada."));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product cat = new Product();
//		cat.setName(dto.getName());
		cat = repository.save(cat);
		return new ProductDTO(cat);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product existing = repository.getReferenceById(id);
//			existing.setName(dto.getName());
			existing = repository.save(existing);
			return new ProductDTO(existing);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial!");
		}
	}
	
	
}
