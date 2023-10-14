package com.donatus.activityTracker.controller;

import com.donatus.activityTracker.dto.UserRequestDTO;
import com.donatus.activityTracker.dto.mapper.UserDTORequestMapperService;
import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.exception.InvalidUserPassword;
import com.donatus.activityTracker.servies.UserRegistrationAndLoginServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginAndRegistrationController {
    private final UserRegistrationAndLoginServices loginServices;
    private final UserDTORequestMapperService requestMapperService;

    @Autowired
    public UserLoginAndRegistrationController(UserRegistrationAndLoginServices loginServices, UserDTORequestMapperService requestMapperService) {
        this.loginServices = loginServices;
        this.requestMapperService = requestMapperService;
    }

    @GetMapping("/user/register")
    public String registration(Model model){
        model.addAttribute("userRegister", new UserRequestDTO());
        return "register";
    }

    @PostMapping("/user/register")
    public String saveUser(
            @Valid @ModelAttribute("userRegister") UserRequestDTO userReg, BindingResult result,
            RedirectAttributes re){

        if (result.hasErrors()) {
            return "register";
        }

        Users newUser = requestMapperService.mapper(userReg);

        loginServices.registerUser(newUser);

        re.addFlashAttribute("message", "Registered Successfully. Sign in.");
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login(Model model){
        model.addAttribute("userLogin", new UserRequestDTO());
        return "login";
    }

    @PostMapping("/user/login")
    public String loginVerification(UserRequestDTO userRequestDTO, RedirectAttributes re, HttpServletRequest request) throws InvalidUserPassword {

        Users verifiedUser = loginServices.verifyUser(userRequestDTO.getEmail(), userRequestDTO.getPassword());

        if (verifiedUser == null){
            re.addFlashAttribute("message", "Wrong Email or password.");
            return "redirect:/user/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", verifiedUser.getUserId());
        session.setAttribute("firstName", verifiedUser.getFirstName());
        return "redirect:/user/home";
    }
}
