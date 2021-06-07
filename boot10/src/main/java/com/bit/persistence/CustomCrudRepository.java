package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.WebBoard;

public interface CustomCrudRepository 
extends CrudRepository<WebBoard, Long>, CustomWebBoard{

}
