package com.example.demo.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBaseRepository<M, ID extends Serializable> extends
		JpaRepository<M, ID>, JpaSpecificationExecutor<M> {

}


