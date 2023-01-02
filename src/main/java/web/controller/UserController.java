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
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAll());
        return "get";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:users/get";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/users/get";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.delete(id, user);
        return "redirect:/users/get";
    }

}
