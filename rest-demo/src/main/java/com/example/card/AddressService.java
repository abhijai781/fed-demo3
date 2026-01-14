package com.example.card;

import org.springframework.stereotype.Service;
import com.example.card.model.Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class AddressService {

    private final List<Address> addressList = new ArrayList<>();

    public AddressService() {
        addressList.add(new Address(1l, "address1", "123 Main St, Springfield, IL"));
        addressList.add(new Address(1l, "address2", "122 Second St, Texas, TX"));
        addressList.add(new Address(2l, "address1", "456 Elm St, Shelbyville, IL"));
        addressList.add(new Address(3l, "address1", "789 Oak St, Capital City, IL"));
    }

    public List<Address> getAddressByUser(Long userId) {
            return addressList.stream()
                    .filter(c -> Objects.equals(c.getUserId(), userId))
                    .collect(Collectors.toList());
        }

        public List<Address> getAllAddress() {
            return addressList;
        }
 }