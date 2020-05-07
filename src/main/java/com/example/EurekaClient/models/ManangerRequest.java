package com.example.EurekaClient.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ManangerRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    @NotNull(message = "Age cannot be null")
    private Integer age;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotNull(message = "Count of clients cannot be blank")
    private Integer clientsCount;

    public ManangerRequest() {}

    public ManangerRequest(String name, String phone, Integer age, String address, Integer clientsCount) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.clientsCount = clientsCount;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setAddress(String address) { this.address = address; }
    public void setClientsCount(Integer clientsCount) { this.clientsCount = clientsCount; }

    public String getName() {
        return this.name;
    }
    public String getPhone() {
        return this.phone;
    }
    public Integer getAge() {
        return this.age;
    }
    public String getAddress() { return this.address; }
    public Integer getClientsCount() { return this.clientsCount; }
}
