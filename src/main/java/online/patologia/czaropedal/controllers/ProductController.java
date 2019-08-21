package online.patologia.czaropedal.controllers;

import online.patologia.czaropedal.model.Cart;
import online.patologia.czaropedal.model.MyUser;
import online.patologia.czaropedal.model.Product;
import online.patologia.czaropedal.repo.ProductRepo;
import online.patologia.czaropedal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Controller
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getAllProductsForEveryone(Model model,@RequestParam(defaultValue = "0") int page) {
        model.addAttribute("product",new Product());

/*        List<Product> products = new ArrayList<>();
        productRepo.findAll(new PageRequest(page, 5)).forEach(product -> {
            if (product.getStock() > 0) {
                products.add(product);
            }
        });*/
        model.addAttribute("products",productRepo.findAvailable(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        boolean userIsAdmin = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
//
//        if (userIsAdmin == true) {
//            return "panel";
//        } else {
            return "index";
//        }
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String getFilteredProducts(@ModelAttribute("product") Product selected, Model model,@RequestParam(defaultValue = "0") int page) {
        List<Product> products = new ArrayList<>();
        if (selected.getCategory().equals("ALL")) {
            model.addAttribute("products",productRepo.findAvailable(PageRequest.of(page,5)));
        }  else {
            model.addAttribute("products",productRepo.findAvailableByCategory(selected.getCategory(),PageRequest.of(page,5)));
        }

        model.addAttribute("currentPage",page);
        return "index";
    }



    @RequestMapping("/product/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "new_product";
        } else {
            productRepo.save(product);
            model.addAttribute("products",productRepo.findAll());
            return "panel";
        }
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_product";
        } else {
            productRepo.save(product);
            model.addAttribute("products",productRepo.findAll());
            return "panel";
        }
    }


    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        productRepo.deleteById(id);
        return "redirect:/panel";
    }

    @RequestMapping("/product/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productRepo.getOne(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/product/{id}")
    public ModelAndView showProduct(@PathVariable(name = "id") Long id ) {
        ModelAndView mav = new ModelAndView("product");
        mav.addObject("cart",new Cart());
        Product product = productRepo.getOne(id);
        mav.addObject("product", product);
        mav.addObject("tooMuch",false);
        return mav;
    }


}
