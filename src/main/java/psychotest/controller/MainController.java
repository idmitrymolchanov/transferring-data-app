package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import psychotest.entity.MainEntity;
import psychotest.entity.TempCount;
import psychotest.service.MainService;
import psychotest.service.UserService;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainController {

    private final UserService userService;
    private final MainService sqlEx;

    @Autowired
    public MainController(UserService userService, MainService sqlEx) {
        this.userService = userService;
        this.sqlEx = sqlEx;
    }

    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "realindex";
    }

    @GetMapping("/main")
    public String mainPage(Model model, Map<String, Object> model2) {
        model.addAttribute("theTempBean", new TempCount());

        model.addAttribute("userForm", new MainEntity());
        model2.put("myVar", 0);
        return "main";
    }

    @PostMapping("/main")
    public String mainPagePost(@ModelAttribute("theTempBean") @Valid TempCount tempCount,
                               @ModelAttribute("userForm") @Valid MainEntity userForm2,
                               Map<String, Object> model2) {
        try {
            if (Integer.parseInt(tempCount.getCount()) > 0) {
                model2.put("myVar", Integer.parseInt(tempCount.getCount()));
            }
        } catch (Exception e) {}

        try {
            //sqlEx.toList2(userForm2);
            //sqlEx.normalToList(userForm2);
            sqlEx.sortByTwoList(userForm2);
        } catch (Exception e) {}

        return "main";
    }

    @GetMapping("/sqlPage")
    public String sqlPage(Model model, Map<String, Object> model2, Map<String, Object> model3, Map<String, Object> model4) {
        model.addAttribute("tableExist", new TempCount());
        model2.put("myVar", 0);
        model3.put("entity", sqlEx.mainEntityReturnByLast());
        model4.put("count", 0);
        return "sqlPage";
    }

    public static String exist;
    public static String quantity;

    /*public static void setQuantity(String quantity) {
        if(quantity != null)
            MainController.quantity = quantity;
    }*/

    public static void setExist(String exist) {
        if(exist != null)
            MainController.exist = exist;
    }

    @PostMapping("/sqlPage")
    public String sqlPagePost(@ModelAttribute("tableExist") @Valid TempCount tempCount, Map<String, Object> model2,
                              Map<String, Object> map2, Map<String, Object> model4) {
        setExist(tempCount.getTableName());
        if(exist.equals("NOT EXIST")) {
            model2.put("myVar", 1);
        }
        else
            model2.put("myVar", 2);
        map2.put("entity", sqlEx.mainEntityReturnByLast());
        model4.put("count", sqlEx.getQuantity());
        return "sqlPage";
    }
}
