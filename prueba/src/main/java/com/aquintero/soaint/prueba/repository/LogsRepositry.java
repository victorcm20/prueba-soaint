package com.aquintero.soaint.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aquintero.soaint.prueba.entity.Logs;

@Repository
public interface LogsRepositry extends CrudRepository<Logs, Long>{

}
