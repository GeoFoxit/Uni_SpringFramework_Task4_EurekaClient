package com.example.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class ManangerController {
    @Autowired
    private ManangerService manangerService;

    @PostMapping("")
    public ResponseEntity<?> createMananger(@Valid @RequestBody Mananger mananger, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Mananger newMananger = manangerService.save(mananger);
        return new ResponseEntity<Mananger>(newMananger, HttpStatus.CREATED);
    }

    @PatchMapping("/{mananger_id}")
    public ResponseEntity<?> patchManangerById(@PathVariable Integer mananger_id, @Valid @RequestBody Mananger mananger, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Mananger finded_mananger = manangerService.findById(mananger_id);
        finded_mananger.setName(mananger.getName());
        finded_mananger.setPhone(mananger.getPhone());
        finded_mananger.setAddress(mananger.getAddress());
        finded_mananger.setAge(mananger.getAge());
        finded_mananger.setClientsCount(mananger.getClientsCount());
        Mananger man = manangerService.save(finded_mananger);
        return new ResponseEntity<Mananger>(man, HttpStatus.OK);
    }

    @GetMapping("/")
    public Iterable<Mananger> getAllManangers() {
        return manangerService.findAll();
    }

    @GetMapping("/{mananger_id}")
    public ResponseEntity<?> getManangerById(@PathVariable Integer mananger_id) {
        Mananger mananger = manangerService.findById(mananger_id);
        if (mananger == null) {
            return new ResponseEntity<String>("Entity is not found. Check the ID", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Mananger>(mananger,HttpStatus.OK);
    }

    @DeleteMapping("/{mananger_id}")
    public ResponseEntity<?> deleteMananger(@PathVariable Integer mananger_id) {
        try {
            manangerService.delete(mananger_id);
            return new ResponseEntity<String>("Mananger deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Entity is not found. Check the ID", HttpStatus.BAD_REQUEST);
        }
    }
}
