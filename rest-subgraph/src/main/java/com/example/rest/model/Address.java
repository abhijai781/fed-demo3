package com.example.rest.model;

public class Address {
    private String addressId;
    private String fullAddress;
    private Long userId;
    
    public Address() {
    }
    
    public Address(Long userId, String addressId, String fullAddress) {
        this.addressId = addressId;
        this.fullAddress = fullAddress;
        this.userId = userId;
    }
    
    public String getAddressId() {
        return addressId;
    }
    
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
    public String getFullAddress() {
        return fullAddress;
    }
    
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}