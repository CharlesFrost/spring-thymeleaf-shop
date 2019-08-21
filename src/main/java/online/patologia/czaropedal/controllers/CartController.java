package online.patologia.czaropedal.controllers;

import online.patologia.czaropedal.model.*;
import online.patologia.czaropedal.repo.*;
import online.patologia.czaropedal.validators.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private ItemRepo itemRepo;



    @GetMapping("/cart/clear")
    public String clearCart(@RequestParam(name = "page", defaultValue = "0") int page, Model model,Principal principal) {
        model.addAttribute("finalizeDone",true);
        model.addAttribute("product",new Product());
        model.addAttribute("products",productRepo.findAvailable(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);

        cartRepo.deleteByUser_Id(userRepo.findByUsername(principal.getName()).getId());
        return "index";
    }



    @RequestMapping(value = "/cart/finalize/save",method = RequestMethod.POST)
    public String finalizeSave( @ModelAttribute("addressandpersonaldata") @Valid AddressAndPersonalData addressAndPersonalData,BindingResult bindingResult, Principal principal, Model model) {
        List<Cart> listOfCarts = cartRepo.findCartById(userRepo.findByUsername(principal.getName()).getId());
        List<Item> listOfItems = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            Double priceForAll=0.0;
            for (Cart cart : listOfCarts) {
                priceForAll+=cart.getPrice();
            }
            model.addAttribute("priceForAll",priceForAll);
            return "finalize";
        }


        Double priceForAll=0.0;
        for (Cart cart : listOfCarts) {
            priceForAll+=cart.getPrice();
            listOfItems.add(itemRepo.save(new Item(cart.getProduct_id(),cart.getQuantity())));
            productRepo.reduceStock(cart.getQuantity(),cart.getProduct_id());
        }

        Order order = new Order(listOfItems,addressAndPersonalData,priceForAll);
        addressRepo.save(addressAndPersonalData);
        orderRepo.save(order);

        return "redirect:/cart/clear";
        //return "redirect:/cart/clear";
    }


    @GetMapping("/cart/finalize")
    public String showFinalizeForm(Model model,Principal principal) {
        model.addAttribute("addressandpersonaldata",new AddressAndPersonalData());
        List<Cart> listOfCarts = cartRepo.findCartById(userRepo.findByUsername(principal.getName()).getId());
        Double priceForAll=0.0;
        for (Cart cart : listOfCarts) {
            priceForAll+=cart.getPrice();
        }
        model.addAttribute("priceForAll",priceForAll);

        return "finalize";
    }

    @RequestMapping(value = "/cart/add/{id}",method = RequestMethod.POST)
    public String addProducts(@ModelAttribute("cart") Cart cart, @PathVariable("id") Long product_id, Principal principal) {
        /*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }*/
        Long user_id = userRepo.findByUsername(principal.getName()).getId();
        Product product = productRepo.getOne(product_id);
        Cart newCart = new Cart();
        newCart.setProduct_id(product_id);
        newCart.setQuantity(cart.getQuantity());
        newCart.setUser_id(user_id);
        newCart.setPrice(product.getPrice()*newCart.getQuantity());
        if (cartRepo.findCartByIds(user_id,product_id).isEmpty()) {
            cartRepo.save(newCart);
        } else {
            List<Cart> cartToUpdate = cartRepo.findCartByIds(user_id,product_id);
            Cart cart1 = cartToUpdate.get(0);
            if(cart1.getQuantity()+cart.getQuantity() > product.getStock()) {
                return "redirect:/product/"+product_id+"?error=notenough";
            }
            cart1.setQuantity(cart1.getQuantity() + cart.getQuantity());
            cartRepo.save(cart1);
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(Model model,Principal principal) {
       /* Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }*/
        List<CartTest> allProducts = new ArrayList<>();
        Long user_id = userRepo.findByUsername(principal.getName()).getId();
        cartRepo.findAll().forEach(i -> {
            if (i.getUser_id() == user_id) {
                Product product = productRepo.getOne(i.getProduct_id());
//                allProducts.add(new CartTest(i.getId(),user_id,i.getProduct_id(),i.getQuantity(),
//                        productRepo.getOne(i.getProduct_id()).getName(),productRepo.getOne(i.getProduct_id()).getPrice()*i.getQuantity(),
//                        productRepo.getOne(i.getProduct_id()).getProducer(),productRepo.getOne(i.getProduct_id()).getImageSource()));
                allProducts.add(new CartTest(i.getId(),user_id,product.getId(),i.getQuantity(),
                        product.getName(),i.getPrice(),product.getProducer(),
                        product.getImageSource()));
            }
        });
        model.addAttribute("cart",allProducts);
        return "new_cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id, Model model,Principal principal) {
        List<CartTest> allProducts = new ArrayList<>();
        /*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }*/
        Long user_id = userRepo.findByUsername(principal.getName()).getId();
        Optional<Cart> cartById = cartRepo.findById(id);
        if (user_id == cartById.get().getUser_id()) {
            cartRepo.deleteById(id);
        }
        cartRepo.findAll().forEach(i -> {
            if (i.getUser_id() == user_id) {
                allProducts.add(new CartTest(i.getId(),user_id,
                        productRepo.getOne(i.getProduct_id()).getId(),i.getQuantity(),
                        productRepo.getOne(i.getProduct_id()).getName(),i.getPrice(),
                        productRepo.getOne(i.getProduct_id()).getProducer(),productRepo.getOne(i.getProduct_id()).getImageSource()));
            }
        });
        model.addAttribute("cart",allProducts);
        return "new_cart";
    }


}
