package com.donatus.activityTracker.controller;

import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.servies.UserRegistrationAndLoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginAndRegistrationController {
    private final UserRegistrationAndLoginServices loginServices;

    @Autowired
    public UserLoginAndRegistrationController(UserRegistrationAndLoginServices loginServices) {
        this.loginServices = loginServices;
    }

    @GetMapping("/user/register")
    public String registration(Model model){
        model.addAttribute("userRegister", new Users());
        return "register";
    }

    @PostMapping("/user/register")
    public String saveUser(Users userReg, RedirectAttributes re){
        Users registeredUser = loginServices.registerUser(userReg);

        if (registeredUser.getUserId() <= 0){
            re.addFlashAttribute("message", "Registration Unsuccessful. Reload page and try again.");
            return "redirect:/user/register";
        }

        re.addFlashAttribute("message", "Registered Successfully. Sign in.");
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login(Model model){
        model.addAttribute("userLogin", new Users());
        return "login";
    }

    @PostMapping("/user/login")
    public String loginVerification(Users user, RedirectAttributes re){
        Users verifiedUser = loginServices.verifyUser(user.getUserName(), user.getPassword());

        if (verifiedUser == null){
            re.addFlashAttribute("message", "Wrong Email or password.");
            return "redirect:/user/login";
        }

        System.out.println(verifiedUser);
        return "home";
    }
}
