package com.example.vhsrental.controller;

import com.example.vhsrental.entity.VHS;
import com.example.vhsrental.service.RentalService;
import com.example.vhsrental.service.UserService;
import com.example.vhsrental.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserService userService;

    @Autowired
    private VHSService vhsService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/rentals")
    public String rentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "rentals";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/vhs")
    public String vhs(Model model) {
        List<VHS> vhsList = vhsService.getAllVHS();
        model.addAttribute("vhsList", vhsList);
        return "vhs";
    }
}
