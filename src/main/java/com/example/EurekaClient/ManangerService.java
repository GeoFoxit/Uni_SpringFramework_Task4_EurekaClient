package com.example.EurekaClient;

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
        return manangerRepository.getById(id);
    }

    public void delete(Integer id) {
        Mananger mananger = findById(id);
        manangerRepository.delete(mananger);
    }
}
