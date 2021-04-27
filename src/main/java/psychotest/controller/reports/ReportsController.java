package psychotest.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

    @GetMapping("/reports_page")
    public String datasourceSetting() {
        return "reports_page";
    }
}
