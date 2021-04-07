package psychotest.controller.create;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import psychotest.controller.create.domain.IdPageDomain;
import psychotest.entity.ValueTypeEntity;
import psychotest.inner_datasource.config.stack.Pull;
import psychotest.inner_datasource.config.stack.TablesPull;
import psychotest.service.TableNameService;
import psychotest.service.TableNameServiceImpl;
import psychotest.service.TypeValueService;
import psychotest.service.TypeValueServiceImpl;

@Controller
public class RowValueTypeController implements Page {
    private Pull pull = TablesPull.getInstance();
    private final TypeValueService typeValueService;
    private List<String> listNamesIn;
    private List<String> listTypesIn;
    public static boolean listNamesFlag;

    @Autowired
    public RowValueTypeController(TypeValueServiceImpl typeValueService) {
        this.typeValueService = typeValueService;
        listNamesFlag = false;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private void setListNames() {
        listNamesIn = typeValueService.getColumnsNames(TableNameController.tableNameForNextStep);
        listNamesFlag = true;
    }

    private void setListTypes() {
        listTypesIn = typeValueService.getColumnsTypes(TableNameController.tableNameForNextStep);
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model, Map<String, Object> listNames, Map<String, Object> listTypes) {
        model.put("todos", typeValueService.findByTableName(pull.peek()));

        if(!listNamesFlag) {
            setListNames();
            setListTypes();
        }

        listNames.put("names", listNamesIn);
        listTypes.put("types", listTypesIn);

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

        todo.setHashTableName(pull.peek());
       // + todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid ValueTypeEntity todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setHashTableName(pull.peek());
        typeValueService.saveTypeAndValue(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-all-todo", method = RequestMethod.GET)
    public String addAllTodoPage(Map<String, Object> listNames, Map<String, Object> listTypes) {
        if(!listNamesFlag) {
            setListNames();
            setListTypes();
        }
        listNames.put("names", listNamesIn);
        listTypes.put("types", listTypesIn);
        return "add-all-todo";
    }

    @RequestMapping(value = "/add-all-todo", method = RequestMethod.POST)
    public String addAllTodoPage() {
        if(!listNamesFlag) {
            setListNames();
            setListTypes();
        }

        for (int i = 0; i < listNamesIn.size(); i++) {
            ValueTypeEntity entity = new ValueTypeEntity();
            entity.setStringValue(listNamesIn.get(i));
            entity.setStringType(listTypesIn.get(i));
            entity.setHashTableName(pull.peek());
            typeValueService.saveTypeAndValue(entity);
        }

        return "redirect:/list-todos";
    }

    @Override
    public String getId_page() {
        return IdPageDomain.VALUES;
    }
}
