package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.UniqueValuesEntity;
import psychotest.entity.HelpTableNameEntity;
import psychotest.service.HelpNameService;
import psychotest.service.HelpValueService;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainControllerV2 {
    private final HelpNameService helpNameService;
    private final HelpValueService helpValueService;

    @Autowired
    public MainControllerV2(HelpNameService helpNameService, HelpValueService helpValueService) {
        this.helpNameService = helpNameService;
        this.helpValueService = helpValueService;
    }

    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "menu_page";
    }

    @GetMapping("/table_name_page")
    public String tableNamePage(Model model, Map<String, Object> myVar) {
        model.addAttribute("theTempBean", new HelpTableNameEntity());
        myVar.put("myVar", 0);
        return "table_name_page";
    }

    @PostMapping("/table_name_page")
    public String tableNamePaige(@ModelAttribute("theTempBean") @Valid HelpTableNameEntity value,
                                 Map<String, Object> myVar) {
        try {
            if (value.getTableName() != null) {
                myVar.put("myVar", 1);
                helpNameService.saveTableName(value);
            }
        } catch (Exception e) {}

        return "table_name_page";
    }

    @GetMapping("/unique_values_page")
    public String uniquePage(ModelMap modelMap, Model model) {
        model.addAttribute("theTempBean", new UniqueValuesEntity());
        String table_name = helpNameService.findLastByName();
        modelMap.put("todos", helpValueService.findByTableName(table_name));
        return "unique_values_page";
    }

    @PostMapping("/unique_values_page")
    public String uniquePage(@ModelAttribute("theTempBean") @Valid UniqueValuesEntity value,
                             ModelMap modelMap) {
        String table_name = helpNameService.findLastByName();
        modelMap.put("todos", helpValueService.findByTableName(table_name));
        if(value.getStringValue() != null) {
            value.setUnique_value(true);
            value.setStringTableName(table_name);
            helpValueService.saveUniqueValue(value);
        }
        return "unique_values_page";
    }

}
