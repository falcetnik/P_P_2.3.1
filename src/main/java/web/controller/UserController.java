package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "get")
    public String showUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAll());
        return "get";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "new";
    }


    @GetMapping("/{id}/edit")
    public String editUser(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users/get";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/users/get";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.delete(id, user);
        return "redirect:/users/get";
    }

}
