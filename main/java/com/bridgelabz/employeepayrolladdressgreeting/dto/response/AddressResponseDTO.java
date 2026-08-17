package com.bridgelabz.employeepayrolladdressgreeting.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResponseDTO {

    private Long id;
    private String city;

    private String zipCode;

    private Long employeeId;
}