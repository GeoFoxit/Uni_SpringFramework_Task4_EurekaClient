package com.example.EurekaClient.controllers;

import com.example.EurekaClient.ManangerValidationException;
import com.example.EurekaClient.models.Mananger;
import com.example.EurekaClient.models.ManangerRequest;
import com.example.EurekaClient.repos.ManangerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class ManangerController {
    @Autowired
    private ManangerRepository manangerService;

    @Autowired
    private Gson gson;

    @PostMapping("")
    public ResponseEntity<String> createMananger(@Valid @RequestBody ManangerRequest mananger) {
        Mananger validMananger = Validate(mananger);
        Mananger createdMananger = manangerService.save(validMananger);
        return new ResponseEntity<String>(gson.toJson(createdMananger), HttpStatus.CREATED);
    }

    @PatchMapping("/{mananger_id}")
    public ResponseEntity<String> patchManangerById(@PathVariable Integer mananger_id, @Valid @RequestBody ManangerRequest mananger) {
        Mananger findedMananger = manangerService.getById(mananger_id);
        if (findedMananger == null) {
            throw new ManangerValidationException("Mananger with id: " + mananger_id + " doesn't exists");
        }
        Mananger validMananger = Validate(mananger);
        findedMananger.setName(validMananger.getName());
        findedMananger.setPhone(validMananger.getPhone());
        findedMananger.setAddress(validMananger.getAddress());
        findedMananger.setAge(validMananger.getAge());
        findedMananger.setClientsCount(validMananger.getClientsCount());
        return new ResponseEntity<String>(gson.toJson(manangerService.save(findedMananger)), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<String> getAllManangers() {
        return new ResponseEntity<String>(gson.toJson(manangerService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{mananger_id}")
    public ResponseEntity<String> getManangerById(@PathVariable Integer mananger_id) {
        Mananger mananger = manangerService.getById(mananger_id);
        if (mananger == null) {
            throw new ManangerValidationException("Mananger with id: " + mananger_id + " doesn't exists");
        }
        return new ResponseEntity<String>(gson.toJson(mananger), HttpStatus.OK);
    }

    @DeleteMapping("/{mananger_id}")
    public ResponseEntity<String> deleteMananger(@PathVariable Integer mananger_id) {
        Mananger mananger = manangerService.getById(mananger_id);
        if (mananger == null) {
            throw new ManangerValidationException("Mananger with id: " + mananger_id + " doesn't exists");
        }
        manangerService.deleteById(mananger_id);
        return new ResponseEntity<String>("Mananger has been deleted.", HttpStatus.OK);
    }

    private Mananger Validate(ManangerRequest mananger) {
        Mananger result = new Mananger();
        //Validation (Throws ValidationExceptions)
        if (!mananger.getName().matches("([A-Z]{1}+[a-z]{2,10}+ +[A-Z]{1}+[a-z]{2,10})")) {
            throw new ManangerValidationException("Name is not valid! Valid example: Joe Peterson");
        }
        if (!mananger.getPhone().matches("(\\+{1}+[\\d]{8,12})")) {
            throw new ManangerValidationException("Phone is not valid! Valid example: +32497982374");
        }
        if (mananger.getAge() < 18 || mananger.getAge() > 70) {
            throw new ManangerValidationException("Age is not valid! Valid age is lower than 70 and bigger than 18");
        }
        if (mananger.getClientsCount() > 500 || mananger.getClientsCount() < 0) {
            throw new ManangerValidationException("Counts of clients is not valid! Valid count is lower than 500 and bigger or equal 0");
        }
        result.setName(mananger.getName());
        result.setPhone(mananger.getPhone());
        result.setAge(mananger.getAge());
        result.setAddress(mananger.getAddress());
        result.setClientsCount(mananger.getClientsCount());

        return result;
    }
}
