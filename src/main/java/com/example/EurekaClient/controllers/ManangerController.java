package com.example.EurekaClient.controllers;

import com.example.EurekaClient.models.Mananger;
import com.example.EurekaClient.services.ManangerService;
import com.google.gson.Gson;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ManangerController {
    @Autowired
    private ManangerService manangerService;

    @Autowired
    private Gson gson;

    @PostMapping("")
    public ResponseEntity<String> createMananger(@Valid @RequestBody Mananger mananger) {
        try {
            Mananger createdMananger = manangerService.save(mananger);
            return new ResponseEntity<String>(gson.toJson(createdMananger), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //decide what exception is
        }
    }

    @PatchMapping("/{mananger_id}")
    public ResponseEntity<String> patchManangerById(@PathVariable Integer mananger_id, @Valid @RequestBody Mananger mananger) {
        try {
            Mananger findedMananger = manangerService.findById(mananger_id);
            findedMananger.setName(mananger.getName());
            findedMananger.setPhone(mananger.getPhone());
            findedMananger.setAddress(mananger.getAddress());
            findedMananger.setAge(mananger.getAge());
            findedMananger.setClientsCount(mananger.getClientsCount());
            return new ResponseEntity<String>(gson.toJson(manangerService.save(findedMananger)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //decide what exception is
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> getAllManangers() {
        try {
            return new ResponseEntity<String>(gson.toJson(manangerService.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{mananger_id}")
    public ResponseEntity<String> getManangerById(@PathVariable Integer mananger_id) {
        try {
            return new ResponseEntity<String>(gson.toJson(manangerService.findById(mananger_id)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST); //decide what exception is
        }
    }

    @DeleteMapping("/{mananger_id}")
    public ResponseEntity<String> deleteMananger(@PathVariable Integer mananger_id) {
        try {
            manangerService.delete(mananger_id);
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST); //decide what exception is
        }
    }
}
