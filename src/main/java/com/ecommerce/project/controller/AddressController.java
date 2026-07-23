package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AuthUtil authUtil;

    @Tag(name = "Address APIs",description = "APIs for managing Address")
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO,user);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @Tag(name = "Address APIs",description = "APIs for managing Address")
    @GetMapping("/addresses")
    public  ResponseEntity<List<AddressDTO>> getAllAddresses(){
        List<AddressDTO> addressList = addressService.getAllAddresses();
        return new ResponseEntity<>(addressList,HttpStatus.OK);
    }

    @Tag(name = "Address APIs",description = "APIs for managing Address")
    @GetMapping("/addresses/{addressId}")
    public  ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddressById(addressId);
        return new ResponseEntity<>(addressDTO,HttpStatus.OK);
    }

    @Tag(name = "Address APIs",description = "APIs for managing Address")
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User loggedInUser = authUtil.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddresses(loggedInUser);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @Tag(name = "Address APIs",description = "APIs for managing Address")
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddressById(@Valid @RequestBody AddressDTO addressDTO
            ,@PathVariable Long addressId){
        AddressDTO savedAddressDTO = addressService.updateAddressById(addressId,addressDTO);
        return new ResponseEntity<>(addressDTO,HttpStatus.OK);
    }

    @Tag(name = "Address APIs",description = "APIs for managing Address")
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long addressId){
        String status = addressService.deleteAddressById(addressId);
        return new ResponseEntity<String>(status,HttpStatus.OK);
    }
}
