package online.patologia.czaropedal.service;

import online.patologia.czaropedal.model.AddressAndPersonalData;
import online.patologia.czaropedal.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    public AddressAndPersonalData save(AddressAndPersonalData addressAndPersonalData) {
        return addressRepo.save(addressAndPersonalData);
    }
}
