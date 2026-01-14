package com.example.rest.service;

import com.example.rest.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;

@Service
public class AddressService {
    
    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    private static final String ADDRESS_SERVICE_URL = "http://localhost:8001/cardservice/api/addresses";
    
    private final RestService restService;
    
    @Autowired
    public AddressService(RestService restService) {
        this.restService = restService;
    }
    
    public List<Address> forUser(Long userId) {
        if (userId == null) {
            return Collections.emptyList();
        }
        List<Address> all = findAllAddress();
        return all.stream()
                .filter(a -> Objects.equals(userId, a.getUserId()))
                .collect(Collectors.toList());
    }
    
    public List<Address> getAllAddress() {
        try {
            Address[] addresses = restService.get(ADDRESS_SERVICE_URL, Address[].class);
            if (addresses == null || addresses.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.asList(addresses);
        } catch (Exception e) {
            logger.error("Error fetching all addresses: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    
    public Address getAddressById(String addressId) {
        if (addressId == null) {
            return null;
        }
        List<Address> all = findAllAddress();
        return all.stream()
                .filter(a -> Objects.equals(addressId, a.getAddressId()))
                .findFirst()
                .orElse(null);
       /* if (addressId == null || addressId.trim().isEmpty()) {
            logger.error("Address ID cannot be null or empty");
            throw new IllegalArgumentException("Address ID cannot be null or empty");
        }
        
        try {
            logger.info("Calling address service for addressId: {}", addressId);
            
            String urlTemplate = ADDRESS_SERVICE_URL + "/{addressId}";
            Address address = restService.get(urlTemplate, Address.class, addressId);
            
            if (address == null) {
                logger.warn("No address found for addressId: {}", addressId);
                return null;
            }
            
            logger.info("Successfully retrieved address for addressId: {}", addressId);
            return address;
            
        } catch (RestClientException e) {
            logger.error("Error calling address service for addressId: {}. Error: {}", addressId, e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve address from external service", e);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching address for addressId: {}. Error: {}", addressId, e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while retrieving address", e);
        }*/
    }
    
    public List<Address> findAllAddress() {
        return getAllAddress();
    }
}
