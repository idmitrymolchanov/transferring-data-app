package psychotest.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportExcelController {

    @GetMapping("/report_excel_page")
    public String datasourceSetting() {
        return "report_excel_page";
    }
}
