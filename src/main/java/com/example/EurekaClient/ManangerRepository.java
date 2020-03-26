package com.example.EurekaClient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManangerRepository extends CrudRepository<Mananger, Integer> {
    Mananger getById(Integer id);
}
