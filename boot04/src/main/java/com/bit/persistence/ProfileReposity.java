package com.bit.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Profile;

public interface ProfileReposity extends CrudRepository<Profile,Long>{

	
}
