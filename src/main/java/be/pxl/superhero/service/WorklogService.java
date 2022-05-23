package be.pxl.superhero.service;

import be.pxl.superhero.api.Worklog.WorklogDTO;
import be.pxl.superhero.api.Worklog.WorklogRequest;

public interface WorklogService {
    public WorklogDTO createWorklog(WorklogRequest worklogRequest);
}
