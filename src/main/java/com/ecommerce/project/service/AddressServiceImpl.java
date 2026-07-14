package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO,User loggedInUser) {
        Address address = modelMapper.map(addressDTO, Address.class);

        List<Address> addressList = loggedInUser.getAddresses();
        addressList.add(address);
        loggedInUser.setAddresses(addressList);

        address.setUser(loggedInUser);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        if(addressList.isEmpty()){
            throw new APIException("No Address exist!");
        }
        return addressList.stream().map(address -> modelMapper.map(address,AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address","addressId",addressId));
        return modelMapper.map(address,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User loggedInUser) {
        List<Address> addressList = loggedInUser.getAddresses();
        return addressList.stream().map(address -> modelMapper.map(address,AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO) {
        Address addressFromDatabase = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address","addressId",addressId));

        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setPincode(addressDTO.getPincode());
        addressFromDatabase.setState((addressDTO.getState()));
        addressFromDatabase.setCountry((addressDTO.getCountry()));
        addressFromDatabase.setStreet((addressDTO.getStreet()));
        addressFromDatabase.setBuildingName((addressDTO.getBuildingName()));

        Address updatedAddress = addressRepository.save(addressFromDatabase);

        User user= addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);

        userRepository.save(user);

        return modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddressById(Long addressId) {
        addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address","addressId",addressId));
        addressRepository.deleteById(addressId);
        return "Address deleted successfully!!!";
    }
}
