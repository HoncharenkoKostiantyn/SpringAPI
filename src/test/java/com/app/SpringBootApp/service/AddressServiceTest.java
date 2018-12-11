package com.app.SpringBootApp.service;

import com.app.SpringBootApp.Exception.NullAddressException;
import com.app.SpringBootApp.domain.Address;
import com.app.SpringBootApp.dto.AddressDTO;
import com.app.SpringBootApp.repository.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddressServiceTest {

    @Mock
    AddressRepository addressRepository;

    static AddressDTO defAddressDTO;

    static Address defAddress;

    @Before
    public void setUp() {
        defAddressDTO = AddressDTO.builder()
                .id(1L)
                .street("Nadbystrzycka")
                .houseNumber(44)
                .apartments(911)
                .zip("501-20")
                .build();

        defAddress = Address.builder()
                .id(1L)
                .street("Nadbystrzycka")
                .houseNumber(44)
                .apartments(911)
                .zip("501-20")
                .build();

        initMocks(this);
        when(addressRepository.save(any())).thenReturn(defAddress);
    }

    @Test
    public void testSaveAddress() throws NullAddressException {
        //given
        AddressService addressService = new AddressService(addressRepository);
        //when
        AddressDTO savedAddress = addressService.save(defAddressDTO);
        //then
        assertEquals(savedAddress, defAddressDTO);
    }

    @Test(expected = NullAddressException.class)
    public void testSaveIncorrectAddress() throws NullAddressException {
        //given
        AddressService addressService = new AddressService(addressRepository);
        //when
        defAddressDTO.setStreet(null);
        AddressDTO savedAddress = addressService.save(defAddressDTO);
        //then
        assertEquals(savedAddress, defAddressDTO);
    }

}