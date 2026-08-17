package com.bridgelabz.employeepayrolladdressgreeting.controller;

import com.bridgelabz.employeepayrolladdressgreeting.exception.AddressNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.AddressRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.AddressResponseDTO;
import com.bridgelabz.employeepayrolladdressgreeting.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/{id}/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping
    public AddressResponseDTO addAddress(@PathVariable("id") Long id,@Valid @RequestBody AddressRequestDTO requestDTO){
        return addressService.addAddress(id,requestDTO);
    }

    @GetMapping
    public AddressResponseDTO getAddress(@PathVariable("id") Long id) throws AddressNotFound {
        return addressService.getAddress(id);
    }

    @PutMapping
    public AddressResponseDTO updateAddress(@PathVariable("id") Long id,@Valid @RequestBody AddressRequestDTO requestDTO) throws AddressNotFound {
        return addressService.updateAddress(id,requestDTO);
    }

}
