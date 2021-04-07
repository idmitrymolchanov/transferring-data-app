package psychotest.controller.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import psychotest.controller.create.domain.IdPageDomain;
import psychotest.entity.TableNameEntity;
import psychotest.inner_datasource.config.stack.Pull;
import psychotest.inner_datasource.config.stack.TablesPull;
import psychotest.service.*;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TableNameController implements Page {
    private final TableNameService tableNameService;
    private Pull pull = TablesPull.getInstance();
    public static String tableNameForNextStep;

    @Autowired
    public TableNameController(TableNameService tableNameService) {
        this.tableNameService = tableNameService;
    }

    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "menu_page";
    }

    @GetMapping("/table_name_page")
    public String tableNamePage(Model model, Map<String, Object> myVar, Map<String, Object> listNames) {
        model.addAttribute("theTempBean", new TableNameEntity());
        myVar.put("myVar", 0);

        listNames.put("todos", tableNameService.getTablesNames());

        return "table_name_page";
    }

    @PostMapping("/table_name_page")
    public String tableNamePaige(@ModelAttribute("theTempBean") @Valid TableNameEntity value,
                                 Map<String, Object> myVar) {
        try {
            if (value.getTableName() != null) {
                myVar.put("myVar", 1);
                value.setHash_connection(pull.peek());
                tableNameService.saveTableName(value);
                tableNameForNextStep = value.getTableName();
            }
        } catch (Exception ignored) {}

        return "table_name_page";
    }

    @Override
    public String getId_page() {
        return IdPageDomain.TABLE_NAME;
    }
}
