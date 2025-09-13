package com.ptm.crudapp.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E, K extends Serializable> extends JpaRepository<E, K> {
}
