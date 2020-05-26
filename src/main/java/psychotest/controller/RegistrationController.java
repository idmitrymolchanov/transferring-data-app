package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import psychotest.entity.EntityUser;
import psychotest.entity.EntityUser2;
import psychotest.entity.SuperPurerEntity;
import psychotest.entity.TempCount;
import psychotest.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        EntityUser userForm = EntityUser.builder().id(Long.valueOf("10")).username("10").password("1010").passwordCONFIRM("1010").build();
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

    @GetMapping("/main")
    public String mainPage(Model model, Map<String, Object> model2) {
        model.addAttribute("theTempBean", new TempCount());

        model.addAttribute("userForm", new SuperPurerEntity());
        model2.put("myVar", 0);
        return "main";
    }

    @PostMapping("/main")
    public String mainPagePost(@ModelAttribute("theTempBean") @Valid TempCount tempCount,
                               @ModelAttribute("userForm") @Valid SuperPurerEntity userForm2,
                               BindingResult bindingResult, Model model, Map<String, Object> model2) {
        try {
            if (Integer.parseInt(tempCount.getCount()) > 0) {
                model2.put("myVar", Integer.parseInt(tempCount.getCount()));
            }
        } catch (Exception e) {}

        System.out.println(userForm2.getValue1());

        return "main";
    }
}
