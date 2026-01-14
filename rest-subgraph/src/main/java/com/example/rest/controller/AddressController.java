package com.example.rest.controller;

import com.example.rest.model.Address;
import com.example.rest.model.User;
import com.example.rest.service.AddressService;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AddressController {
    
    private final AddressService addressService;
    
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    
    @QueryMapping
    public List<Address> allAddress() {
        return addressService.findAllAddress();
    }
    
    @EntityMapping
    public User user(@Argument Long userId) { return new User(userId);}
    
    @EntityMapping
    public Address address(@Argument String addressId) {return addressService.getAddressById(addressId);}
    
    @SchemaMapping(typeName = "User", field = "addresses")
    public List<Address> addresses(User user) {
        return addressService.forUser(user.getUserId());
    }
}
