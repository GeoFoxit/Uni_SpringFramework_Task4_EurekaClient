package com.example.EurekaClient.services;

import com.example.EurekaClient.repos.ManangerRepository;
import com.example.EurekaClient.models.Mananger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManangerService {
    @Autowired
    private ManangerRepository manangerRepository;

    public Mananger save(Mananger mananger) {
        return manangerRepository.save(mananger);
    }

    public Iterable<Mananger> findAll() {
        return manangerRepository.findAll();
    }

    public Mananger findById(Integer id) {
        Mananger mananger = manangerRepository.getById(id);
        if (mananger == null) { throw new RuntimeException("Mananger is not found"); }
        return mananger;
    }

    public void delete(Integer id) {
        manangerRepository.delete(findById(id));
    }
}
