package com.app.SpringBootApp.controller;

import com.app.SpringBootApp.Exception.NullWorkerException;
import com.app.SpringBootApp.dto.WorkerDTO;
import com.app.SpringBootApp.service.WorkerService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PutMapping("saveWorker")
    public WorkerDTO saveWorkerToDb(@RequestBody WorkerDTO worker) throws NullWorkerException {
        return workerService.save(worker);
    }

    @GetMapping("findWorker")
    public WorkerDTO saveWorkerToDb(@RequestBody String workerId) throws NotFoundException {
        return workerService.findOneById(Long.parseLong(workerId));
    }

}
