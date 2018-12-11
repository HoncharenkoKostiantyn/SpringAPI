package com.app.SpringBootApp.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String street;
    private Integer houseNumber;
    private Integer apartments;
    private String zip;

}
