package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import psychotest.entity.RegistrationEntity;
import psychotest.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new RegistrationEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid RegistrationEntity entityUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        List<RegistrationEntity> entityUserList = new ArrayList<>();
        entityUserList.add(entityUser);

        userService.saveUser(entityUserList);

      //  if (!userService.saveUser(entityUserList)){
      //      model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
      //      return "registration";
      //  }

        return "redirect:/";
    }
}
