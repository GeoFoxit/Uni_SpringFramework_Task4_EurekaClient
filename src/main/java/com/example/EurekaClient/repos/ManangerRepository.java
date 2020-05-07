package com.example.EurekaClient.repos;

import com.example.EurekaClient.models.Mananger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManangerRepository extends CrudRepository<Mananger, Integer> {
    Mananger getById(Integer id);
}
