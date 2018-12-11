package com.app.SpringBootApp.service;

import com.app.SpringBootApp.Exception.NullWorkerException;
import com.app.SpringBootApp.domain.Address;
import com.app.SpringBootApp.domain.Worker;
import com.app.SpringBootApp.dto.AddressDTO;
import com.app.SpringBootApp.dto.WorkerDTO;
import com.app.SpringBootApp.repository.WorkerRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    private static AddressDTO defAddressDTO;
    private static ArrayList<AddressDTO> addressesDTO;
    private static WorkerDTO defWorkerDTO;

    private static Address defAddress;
    private static ArrayList<Address> addresses;
    private static Worker defWorker;

    @Before
    public void setUp() {
        defAddressDTO = AddressDTO.builder()
                .id(1L)
                .street("Nadbystrzycka")
                .houseNumber(44)
                .apartments(911)
                .zip("501-20")
                .build();
        addressesDTO = new ArrayList<>();

        defWorkerDTO = WorkerDTO.builder()
                .id(1L)
                .fullName("Kostia")
                .surName("Honch")
                .email("email")
                .password("qwerty")
                .build();

        defAddress = Address.builder()
                .id(1L)
                .street("Nadbystrzycka")
                .houseNumber(44)
                .apartments(911)
                .zip("501-20")
                .build();
        addresses = new ArrayList<>();

        defWorker = Worker.builder()
                .id(1L)
                .fullName("Kostia")
                .surName("Honch")
                .email("email")
                .password("qwerty")
                .build();

        addressesDTO.add(defAddressDTO);
        defWorkerDTO.setAddresses(addressesDTO);

        addresses.add(defAddress);
        defWorker.setAddresses(addresses);

        initMocks(this);
        when(workerRepository.save(any())).thenReturn(defWorker);
        when(workerRepository.findOneById(1L)).thenReturn(defWorker);
    }

    @Test
    public void testSaveWorker() throws NullWorkerException {
        //given
        WorkerService workerService = new WorkerService(workerRepository);
        //when
        WorkerDTO savedWorkerDTO = workerService.save(defWorkerDTO);
        //then
        assertEquals(savedWorkerDTO, defWorkerDTO);
    }

    @Test(expected = NullWorkerException.class)
    public void testSaveIncorrectWorker() throws NullWorkerException {
        //given
        WorkerService workerService = new WorkerService(workerRepository);
        //when
        defWorkerDTO.setFullName(null);
        WorkerDTO savedWorkerDTO = workerService.save(defWorkerDTO);
        //then
        assertEquals(savedWorkerDTO, defWorkerDTO);
    }

    @Test(expected = NullWorkerException.class)
    public void testSaveWorkerWithNullAddress() throws NullWorkerException {
        //given
        WorkerService workerService = new WorkerService(workerRepository);
        //when
        defWorkerDTO.setAddresses(new ArrayList<AddressDTO>());
        WorkerDTO savedWorkerDTO = workerService.save(defWorkerDTO);
        //then
        assertEquals(savedWorkerDTO, defWorkerDTO);
    }

    @Test
    public void testFindWorkerById() throws NotFoundException {
        //given
        WorkerService workerService = new WorkerService(workerRepository);
        //when
        WorkerDTO savedWorkerDTO = workerService.findOneById(1L);
        //then
        assertEquals(savedWorkerDTO.getEmail(), defWorkerDTO.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void testFindWorkerByIncorrectId() throws NotFoundException {
        //given
        WorkerService workerService = new WorkerService(workerRepository);
        //when
        WorkerDTO savedWorkerDTO = workerService.findOneById(45L);
        //then
        assertEquals(savedWorkerDTO.getEmail(), defWorkerDTO.getEmail());
    }
}