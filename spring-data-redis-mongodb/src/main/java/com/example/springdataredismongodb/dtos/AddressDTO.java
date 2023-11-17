package com.example.springdataredismongodb.dtos;

import com.example.springdataredismongodb.models.mongo.Address;

import java.io.Serializable;

public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;
    private String number;
    private String district;
    private CityDTO city;

    public AddressDTO() {}

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.district = address.getDistrict();
        this.city = new CityDTO(address.getCity());
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
