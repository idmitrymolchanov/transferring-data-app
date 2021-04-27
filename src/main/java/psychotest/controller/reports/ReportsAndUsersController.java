package psychotest.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsAndUsersController {

    @GetMapping("/reports_and_users_page")
    public String datasourceSetting() {
        return "reports_and_users_page";
    }
}
