package psychotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.service.DatasourceConnectionService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class DatasourceConnectionController {
    private final DatasourceConnectionService dscService;
    public static String idForHashCon;

    @Autowired
    public DatasourceConnectionController(DatasourceConnectionService dscService) {
        this.dscService = dscService;
    }

    @GetMapping("/datasource_connection")
    public String datasourceSource(Model model, Map<String, Object> success) {
        model.addAttribute("datasourceEntity", new DatasourceEntityConnection());
        success.put("success", 0);
        return "check_connection";
    }

    @PostMapping("/datasource_connection")
    public String datasourceSource(@ModelAttribute("datasourceEntity") @Valid DatasourceEntityConnection value,
                                   Map<String, Object> success) {
        if(value.getSource_url() != null && value.getTarget_url() != null) {
            dscService.saveConnections(value);
        }

        success.put("success", 1);

        return "check_connection";
    }

    @GetMapping("/select_connection")
    public String selectConnection(Model model, Map<String, Object> success, Map<String, Object> listConn) {
        model.addAttribute("connectionEntity", new DatasourceEntityConnection());
        success.put("success", 0);
        listConn.put("todos", dscService.getAllConn());
        return "select_connection";
    }

    @PostMapping("/select_connection")
    public String selectConnection(@ModelAttribute("connectionEntity") @Valid DatasourceEntityConnection value,
                                   Map<String, Object> success, Map<String, Object> listConn) {
        if(value.getId() != null) {
            idForHashCon = value.getId().toString();
        }
        success.put("success", 1);
        listConn.put("todos", dscService.getAllConn());
        return "select_connection";
    }
}
