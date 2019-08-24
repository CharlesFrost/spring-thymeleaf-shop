package online.patologia.czaropedal.service;

import online.patologia.czaropedal.model.Item;
import online.patologia.czaropedal.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;

    public Item save(Item item) {
        return itemRepo.save(item);
    }


}
