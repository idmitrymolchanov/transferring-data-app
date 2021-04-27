package psychotest.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import psychotest.config.profile.ConfigLocal;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.service.DatasourceConnectionService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ReportConnectionController {

    private final DatasourceConnectionService dscService;
    private static String selectedConnection;
    private static String selectedTable;

    @Autowired
    public ReportConnectionController(DatasourceConnectionService dscService) {
        this.dscService = dscService;
    }

    public static String getSelectedConnection() {
        return selectedConnection;
    }

    public static String getSelectedTable() {
        return selectedTable;
    }

    @GetMapping("/report_connection_page")
    public String selectConnection(Model model, Map<String, Object> success, Map<String, Object> listConn) {
        model.addAttribute("connectionEntity", new DatasourceEntityConnection());
        success.put("success", 0);
        listConn.put("todos", dscService.getAllConn());

        return "report_connection_page";
    }

    @PostMapping("/report_connection_page")
    public String selectConnection(@ModelAttribute("connectionEntity") @Valid DatasourceEntityConnection value,
                                   Map<String, Object> success, Map<String, Object> listConn) {
        if(value.getId() != null) {
            selectedConnection = dscService.getHashById(value.getId().toString());
            selectedTable = value.getHash_connection();

            DatasourceEntityConnection entityConnection = dscService.getConnById(value.getId());
            List<DatasourceEntity> entityList = dscService.getSourceTargetConfigs(entityConnection);
            ConfigLocal.datasourceEntitySource = entityList.get(0);
        }
        success.put("success", 1);
        listConn.put("todos", dscService.getAllConn());

        return "report_connection_page";
    }
}
