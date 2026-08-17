package com.bridgelabz.employeepayrolladdressgreeting.service;

import com.bridgelabz.employeepayrolladdressgreeting.exception.AddressNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.AddressRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.AddressResponseDTO;
import com.bridgelabz.employeepayrolladdressgreeting.model.Address;
import com.bridgelabz.employeepayrolladdressgreeting.model.Employee;
import com.bridgelabz.employeepayrolladdressgreeting.repository.AddressRepository;
import com.bridgelabz.employeepayrolladdressgreeting.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements  AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AddressResponseDTO addAddress(Long employeeId, AddressRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Id not found"));
        if (employee.getAddress() != null) {
            throw new RuntimeException("Employee already has an address");
        }
        Address address = new Address();
        address.setEmployee(employee);
        address = addressRepository.save(convertToAddress(address, requestDTO));
        employee.setAddress(address);
        employeeRepository.save(employee);
        return convertToAddressResponseDTO(address);
    }

    @Override
    public AddressResponseDTO getAddress(Long employeeId) throws AddressNotFound {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Id not found"));
        Address address = employee.getAddress();
        if (address == null) {
            throw new AddressNotFound("Address not found");
        }
        return convertToAddressResponseDTO(address);
    }

    @Override
    public AddressResponseDTO updateAddress(Long employeeId, AddressRequestDTO requestDTO) throws AddressNotFound {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Id not found"));
        Address address = employee.getAddress();
        if (address == null) {
            throw new AddressNotFound("Address not found");
        }
        address=convertToAddress(address,requestDTO);
        employee.setAddress(address);
        employeeRepository.save(employee);
        return convertToAddressResponseDTO(address);
    }

    public Address convertToAddress(Address address, AddressRequestDTO requestDTO) {
        address.setCity(requestDTO.getCity());
        address.setZipCode(requestDTO.getZipCode());
        return address;
    }

    public AddressResponseDTO convertToAddressResponseDTO(Address address) {

        AddressResponseDTO responseDTO = new AddressResponseDTO();

        responseDTO.setId(address.getId());
        responseDTO.setCity(address.getCity());
        responseDTO.setZipCode(address.getZipCode());
        responseDTO.setEmployeeId(address.getEmployee().getId());

        return responseDTO;
    }
}
