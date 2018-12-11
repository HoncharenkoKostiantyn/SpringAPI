package com.app.SpringBootApp.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {

    private Long id;
    private String fullName;
    private String surName;
    private String email;
    private String password;
    private List<AddressDTO> addresses;

}
