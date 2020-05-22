package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import psychotest.entity.EntityUser;
import psychotest.entity.EntityUser2;
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
        model.addAttribute("userForm", EntityUser.builder().id(Long.parseLong("8")).username("oiu").password("iilihi").passwordCONFIRM("iilihi").build());
        System.out.println("- - - - - - - - - - - -  - - - - - - -  - -get");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid EntityUser2 userForm2, BindingResult bindingResult, Model model) {
        System.out.println("- - - - - - - - - - - -  - - - - - - -  - -post");
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        EntityUser userForm = EntityUser.builder().id(Long.valueOf("9")).username("").password("").passwordCONFIRM("").build();
        List<EntityUser> entityUserList = new ArrayList<>();
        entityUserList.add(userForm);

        System.out.println(entityUserList.get(0).getId() + ";wsnlnlnl-d-d-d-d-d-d-d-d-d-d-d-d-d-e-wfsaf-sd-d-d-dfsad-fa-j");
        userService.saveUser(entityUserList);

      //  if (!userService.saveUser(entityUserList)){
      //      model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
      //      return "registration";
      //  }

        return "redirect:/";
    }
}
