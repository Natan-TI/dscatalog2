package com.natanti.dscatalog.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Entity
@Data
@AllArgsConstructor
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
}
