package com.app.SpringBootApp.service;

import com.app.SpringBootApp.Exception.NullWorkerException;
import com.app.SpringBootApp.domain.Address;
import com.app.SpringBootApp.domain.Worker;
import com.app.SpringBootApp.dto.AddressDTO;
import com.app.SpringBootApp.dto.WorkerDTO;
import com.app.SpringBootApp.repository.WorkerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public WorkerDTO save(WorkerDTO workerDTO) throws NullWorkerException {

        if (checkWorker(workerDTO)) {
            Worker worker = Worker.builder()
                    .fullName(workerDTO.getFullName())
                    .surName(workerDTO.getSurName())
                    .email(workerDTO.getEmail())
                    .password(workerDTO.getPassword())
                    .addresses(workerDTO.getAddresses().stream()
                            .map(addressDTO -> Address.builder()
                                    .street(addressDTO.getStreet())
                                    .houseNumber(addressDTO.getHouseNumber())
                                    .apartments(addressDTO.getApartments())
                                    .zip(addressDTO.getZip())
                                    .build()).collect(Collectors.toList()))
                    .build();

            Worker savedWorker = workerRepository.save(worker);

            return WorkerDTO.builder()
                    .id(savedWorker.getId())
                    .fullName(savedWorker.getFullName())
                    .surName(savedWorker.getSurName())
                    .email(savedWorker.getEmail())
                    .password(savedWorker.getPassword())
                    .addresses(savedWorker.getAddresses().stream()
                            .map(address -> AddressDTO.builder()
                                    .id(address.getId())
                                    .street(address.getStreet())
                                    .houseNumber(address.getHouseNumber())
                                    .apartments(address.getApartments())
                                    .zip(address.getZip())
                                    .build()).collect(Collectors.toList()))
                    .build();
        } else {
            throw new NullWorkerException("Worker is not completely defined");
        }
    }

    public WorkerDTO findOneById(Long workerId) throws NotFoundException {
        Worker worker = workerRepository.findOneById(workerId);
        if (worker != null)
            return WorkerDTO.builder()
                    .id(worker.getId())
                    .fullName(worker.getFullName())
                    .surName(worker.getFullName())
                    .email(worker.getEmail())
                    .password(worker.getPassword())
                    .build();
        else
            throw new NotFoundException("Worker does not exist");
    }

    private boolean checkWorker(WorkerDTO workerDTO) {
        if (workerDTO.getFullName() == null
                || workerDTO.getSurName() == null
                || workerDTO.getEmail() == null
                || workerDTO.getPassword() == null
                || workerDTO.getAddresses() == null
                || isAddressNull(workerDTO.getAddresses()))
            return false;
        else
            return true;
    }

    private boolean isAddressNull(List<AddressDTO> addressesDTO) {
        AddressDTO addressDTO;
        if (addressesDTO.size() != 0)
            addressDTO = addressesDTO.get(0);
        else
            return true;

        if (addressDTO.getStreet() == null
                || addressDTO.getHouseNumber() == null
                || addressDTO.getApartments() == null
                || addressDTO.getZip() == null)
            return true;
        else
            return false;
    }
}
