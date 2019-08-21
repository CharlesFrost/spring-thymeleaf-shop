package online.patologia.czaropedal.controllers;

import online.patologia.czaropedal.model.Order;
import online.patologia.czaropedal.model.Product;
import online.patologia.czaropedal.repo.OrderRepo;
import online.patologia.czaropedal.repo.ProductRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PanelController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/panel/order/send/{id}")
    public String orderSend(@PathVariable("id") Long id,  Model model) {
        Order order = orderRepo.getOne(id);
        if(order.isSend()) {
            order.setSend(false);
        } else {
            order.setSend(true);
        }
        orderRepo.save(order);
        List<Order> orders = new ArrayList<>();
        orderRepo.findAll().forEach(o -> orders.add(o));
        model.addAttribute("orders",orders);
        return "orders";
    }

    @GetMapping("/panel")
    public String getAllProducts(Model model) {
        model.addAttribute("product",new Product());
        List<Product> products = new ArrayList<>();
        productRepo.findAll().forEach(product -> products.add(product));
        model.addAttribute("products",products);
        return "panel";
    }

    @GetMapping("/panel/orders")
    public String getOrders(Model model) {
        List<Order> orders = new ArrayList<>();
        orderRepo.findAll().forEach(order -> orders.add(order));
        model.addAttribute("orders",orders);
        return "orders";
    }
}
