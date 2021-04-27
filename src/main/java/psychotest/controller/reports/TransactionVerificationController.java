package psychotest.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionVerificationController {

    @GetMapping("/transaction_verification_page")
    public String datasourceSetting() {
        return "transaction_verification_page";
    }
}
