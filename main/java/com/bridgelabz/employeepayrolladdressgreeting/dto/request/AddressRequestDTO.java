package com.bridgelabz.employeepayrolladdressgreeting.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressRequestDTO {
    @NotBlank(message = "city should not be blank")
    private String city;

    @NotBlank(message = "zip should not be blank")
    private String zipCode;
}
