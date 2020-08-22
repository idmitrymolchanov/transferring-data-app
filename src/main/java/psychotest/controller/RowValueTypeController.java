package psychotest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import psychotest.entity.ValueTypeEntity;
import psychotest.service.TableNameService;
import psychotest.service.TypeValueService;

@Controller
public class RowValueTypeController {

    private final TableNameService tableNameService;
    private final TypeValueService typeValueService;

    @Autowired
    public RowValueTypeController(TableNameService tableNameService, TypeValueService typeValueService) {
        this.tableNameService = tableNameService;
        this.typeValueService = typeValueService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        String table_name = tableNameService.findLastByName();
        model.put("todos", typeValueService.findByTableName(table_name));
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
        model.addAttribute("todo", new ValueTypeEntity());
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id) {
        typeValueService.deleteById(Integer.parseInt("" + id));
        // service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
        ValueTypeEntity valueAndTypeEntity = typeValueService.findById(Integer.parseInt(""+id));
        model.put("todo", valueAndTypeEntity);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid ValueTypeEntity todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setStringTableName(tableNameService.findLastByName());
       // + todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid ValueTypeEntity todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setStringTableName(tableNameService.findLastByName());
        typeValueService.saveTypeAndValue(todo);
        return "redirect:/list-todos";
    }
}
