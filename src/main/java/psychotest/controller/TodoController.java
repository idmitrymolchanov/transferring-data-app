package psychotest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import psychotest.entity.HelpValueAndTypeEntity;
import psychotest.service.HelpNameService;
import psychotest.service.HelpValueService;

@Controller
public class TodoController {

    private final HelpNameService helpNameService;
    private final HelpValueService helpValueService;

    @Autowired
    public TodoController(HelpNameService helpNameService, HelpValueService helpValueService) {
        this.helpNameService = helpNameService;
        this.helpValueService = helpValueService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        String table_name = helpNameService.findLastByName();
        model.put("todos", helpValueService.findByTableName(table_name));
        // model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

   /* private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }*/

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo", new HelpValueAndTypeEntity());
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id) {
        helpValueService.deleteById(Integer.parseInt("" + id));
        // service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
        HelpValueAndTypeEntity valueAndTypeEntity = helpValueService.findById(Integer.parseInt(""+id));
        model.put("todo", valueAndTypeEntity);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid HelpValueAndTypeEntity todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setStringTableName(helpNameService.findLastByName());
       // + todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid HelpValueAndTypeEntity todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setStringTableName(helpNameService.findLastByName());
        helpValueService.saveTypeAndValue(todo);
        return "redirect:/list-todos";
    }
}
