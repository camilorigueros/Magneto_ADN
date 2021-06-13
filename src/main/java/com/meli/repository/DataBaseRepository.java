package com.meli.repository;

import com.meli.entity.Adn;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;


public interface DataBaseRepository extends CassandraRepository<Adn, String> {

    @Query("select count(*) from adn where response = ?0 ")
    Long findByResponse(boolean response);

}
