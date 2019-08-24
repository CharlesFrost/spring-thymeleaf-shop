package online.patologia.czaropedal.service;

import online.patologia.czaropedal.model.Order;
import online.patologia.czaropedal.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Order getOne(Long id) {
        return orderRepo.getOne(id);
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

}
