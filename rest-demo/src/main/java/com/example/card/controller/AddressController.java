package com.example.card.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.card.AddressService;
import com.example.card.model.Address;
import com.example.card.AddressService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    
    public AddressController(AddressService addressService) {
        this.addressService = new AddressService();
    }
    
    @GetMapping("/{userId}")
    public List<Address> getAddress(@PathVariable Long userId) {
        return addressService.getAddressByUser(userId);
    }
    
    
    @GetMapping("")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }
}
