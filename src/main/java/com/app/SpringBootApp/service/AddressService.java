package com.app.SpringBootApp.service;

import com.app.SpringBootApp.Exception.NullAddressException;
import com.app.SpringBootApp.domain.Address;
import com.app.SpringBootApp.dto.AddressDTO;
import com.app.SpringBootApp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO save(AddressDTO addressDTO) throws NullAddressException {

        if (checkAddress(addressDTO)) {
            Address address = Address.builder()
                    .street(addressDTO.getStreet())
                    .houseNumber(addressDTO.getHouseNumber())
                    .apartments(addressDTO.getApartments())
                    .zip(addressDTO.getZip())
                    .build();

            Address addressSaved = addressRepository.save(address);

            return AddressDTO.builder()
                    .id(addressSaved.getId())
                    .street(addressSaved.getStreet())
                    .houseNumber(addressSaved.getHouseNumber())
                    .apartments(addressSaved.getApartments())
                    .zip(addressSaved.getZip())
                    .build();
        } else {
            throw new NullAddressException("Address is not completely defined");
        }
    }

    private boolean checkAddress(AddressDTO addressDTO) {
        if (addressDTO.getStreet() == null
                || addressDTO.getHouseNumber() == null
                || addressDTO.getApartments() == null
                || addressDTO.getZip() == null)
            return false;
        else
            return true;
    }

}
