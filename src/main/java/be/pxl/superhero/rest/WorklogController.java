package be.pxl.superhero.rest;

import be.pxl.superhero.api.Mission.MissionDTO;
import be.pxl.superhero.api.Worklog.WorklogRequest;
import be.pxl.superhero.domain.Worklog;
import be.pxl.superhero.service.MissionService;
import be.pxl.superhero.service.WorklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/worklog")
public class WorklogController {

    private final MissionService missionService;
    private final WorklogService worklogService;

    @Autowired
    public WorklogController(MissionService missionService, WorklogService worklogService)
    {
        this.missionService = missionService;
        this.worklogService = worklogService;
    }

    @GetMapping("/enter_worklog")
    public String enterWorklog(Model model, WorklogRequest worklogRequest) {
        List<MissionDTO> missions = missionService.findAllUncompletedMissions();
        model.addAttribute("missions", missions);
        model.addAttribute("workLogRequest", new WorklogRequest());
        return "enter_worklog";
    }

    @PostMapping("/add_worklog")
    public String addWorklog(@Valid @ModelAttribute("workLogRequest") WorklogRequest workLogRequest, BindingResult result, Model model) {
        model.addAttribute("workLogRequest", workLogRequest);
        if (result.hasErrors())
        {
            List<MissionDTO> missions = missionService.findAllUncompletedMissions();
            model.addAttribute("missions", missions);
            return "enter_worklog";
        }

        worklogService.createWorklog(workLogRequest);
        return "worklog";
    }

}

