package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import psychotest.entity.UniqueValuesEntity;
import psychotest.entity.TableNameEntity;
import psychotest.service.*;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TableAndUniqueController {
    private final TableNameService tableNameService;
    private final TypeValueService typeValueService;
    private final UniqueValueService uniqueValueService;

    @Autowired
    public TableAndUniqueController(TableNameService tableNameService, TypeValueService typeValueService,
                                    UniqueValueService uniqueValueService) {
        this.tableNameService = tableNameService;
        this.typeValueService = typeValueService;
        this.uniqueValueService = uniqueValueService;
    }

    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "menu_page";
    }

    @GetMapping("/table_name_page")
    public String tableNamePage(Model model, Map<String, Object> myVar) {
        model.addAttribute("theTempBean", new TableNameEntity());
        myVar.put("myVar", 0);
        return "table_name_page";
    }

    @PostMapping("/table_name_page")
    public String tableNamePaige(@ModelAttribute("theTempBean") @Valid TableNameEntity value,
                                 Map<String, Object> myVar) {
        try {
            if (value.getTableName() != null) {
                myVar.put("myVar", 1);
                tableNameService.saveTableName(value);
            }
        } catch (Exception e) {}

        return "table_name_page";
    }

    @GetMapping("/unique_values_page")
    public String uniquePage(ModelMap modelMap, Model model) {
        model.addAttribute("theTempBean", new UniqueValuesEntity());
        String table_name = tableNameService.findLastByName();
        modelMap.put("todos", typeValueService.findByTableName(table_name));
        return "unique_values_page";
    }

    @PostMapping("/unique_values_page")
    public String uniquePage(@ModelAttribute("theTempBean") @Valid UniqueValuesEntity value,
                             ModelMap modelMap) {
        String table_name = tableNameService.findLastByName();
        modelMap.put("todos", typeValueService.findByTableName(table_name));
        if(value.getStringValue() != null) {
            value.setUnique_value(true);
            value.setStringTableName(table_name);
            uniqueValueService.saveUniqueValue(value);
        }
        return "unique_values_page";
    }

}
