package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import psychotest.entity.UserEntity;
import psychotest.service.RegistrationServiceImpl;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;

    @Autowired
    public RegistrationController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid UserEntity entityUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        registrationService.saveAll(entityUser);

        //  if (!userService.saveUser(entityUserList)){
        //      model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
        //      return "registration";
        //  }

        return "redirect:/";
    }
}
