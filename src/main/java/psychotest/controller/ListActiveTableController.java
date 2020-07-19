package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import psychotest.service.HelpNameService;

@Controller
public class ListActiveTableController {
    private final HelpNameService helpNameService;

    @Autowired
    public ListActiveTableController(HelpNameService helpNameService) {
        this.helpNameService = helpNameService;
    }

    @RequestMapping(value = "/all_tables_page", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        model.put("todos", helpNameService.getAllTable());
        return "all_tables_page";
    }

    @RequestMapping(value = "/delete_table", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id) {
        helpNameService.deleteTableById(Integer.parseInt("" + id));
        // service.deleteTodo(id);
        return "redirect:/all_tables_page";
    }

    @RequestMapping(value = "/update_table", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
     //   HelpValueAndTypeEntity valueAndTypeEntity = helpNameService.findById(Integer.parseInt(""+id));
     //   model.put("todo", valueAndTypeEntity);
        return "todo";
    }
}
