package online.patologia.czaropedal.controllers;

import online.patologia.czaropedal.model.MyUser;
import online.patologia.czaropedal.model.Product;
import online.patologia.czaropedal.repo.ProductRepo;
import online.patologia.czaropedal.repo.UserRepo;
import online.patologia.czaropedal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/user/new")
    public String showNewUserForm(Model model) {
        MyUser myUser = new MyUser();
        model.addAttribute("user", myUser);
        return "register";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("user") @Valid MyUser myUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.findByUsername(myUser.getUsername()) != null) {
            model.addAttribute("accountExist",true);
            return "/register";
        }
        if (!myUser.getPassword().equals(myUser.getPasswordMatcher())) {
            model.addAttribute("wrongPass", true);
            return "/register";
        }
        myUser.setRole("ROLE_USER");
        myUser.setPassword(passwordEncoder().encode(myUser.getPassword()));
        userService.save(myUser);
        model.addAttribute("success",true);
        return "login";

    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
