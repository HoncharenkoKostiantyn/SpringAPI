package com.app.SpringBootApp.controller;

import com.app.SpringBootApp.Exception.NullAddressException;
import com.app.SpringBootApp.dto.AddressDTO;
import com.app.SpringBootApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("saveAddress")
    public void saveAddressToDb(@RequestBody AddressDTO address) throws NullAddressException {
        addressService.save(address);
    }

}
