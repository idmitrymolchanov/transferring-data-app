package psychotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import psychotest.entity.SchedulerEntity;
import psychotest.entity.TempCount;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class SchedulerController {

    @GetMapping("/scheduler")
    public String main(Model model, Map<String, Object> model1) {
        model.addAttribute("theTempBean", new SchedulerEntity());
        model1.put("next", 0);
        return "scheduler";
    }

    @PostMapping("/scheduler")
    public String save(@ModelAttribute("theTempBean") @Valid SchedulerEntity schedulerEntity, Model model, Map<String, Object> model1) {
       // model.addAttribute("user", schedulerEntity);
        if(schedulerEntity.getDate()!=null)
            model1.put("next", 1);
        return "scheduler";
    }
}
