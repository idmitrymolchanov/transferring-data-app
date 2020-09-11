package psychotest.controller.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import psychotest.controller.create.domain.IdPageDomain;
import psychotest.entity.UniqueValuesEntity;
import psychotest.inner_datasource.config.stack.Pull;
import psychotest.inner_datasource.config.stack.TablesPull;
import psychotest.service.TypeValueService;
import psychotest.service.UniqueValueService;

import javax.validation.Valid;

@Controller
public class UniquePageController implements Page {
    private final TypeValueService typeValueService;
    private final UniqueValueService uniqueValueService;
    private Pull pull = TablesPull.getInstance();

    @Autowired
    public UniquePageController(TypeValueService typeValueService, UniqueValueService uniqueValueService) {
        this.typeValueService = typeValueService;
        this.uniqueValueService = uniqueValueService;
    }

    @GetMapping("/unique_values_page")
    public String uniquePage(ModelMap modelMap, Model model) {
        model.addAttribute("theTempBean", new UniqueValuesEntity());
        modelMap.put("todos", typeValueService.findByTableName(pull.peek()));
        return "unique_values_page";
    }

    @PostMapping("/unique_values_page")
    public String uniquePage(@ModelAttribute("theTempBean") @Valid UniqueValuesEntity value,
                             ModelMap modelMap) {
        modelMap.put("todos", typeValueService.findByTableName(pull.peek()));
        if(value.getStringValue() != null) {

            value.setHashTableName(pull.peek());
            uniqueValueService.saveUniqueValue(value);
        }
        return "unique_values_page";
    }

    @Override
    public String getId_page() {
        return IdPageDomain.UNIQUE;
    }
}
