package org.work.scipower.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.work.scipower.model.ProjectModel;
import org.work.scipower.repository.ProjectRepository;
import org.work.scipower.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ApplicationContext context;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ApplicationContext context) {
        this.projectRepository = projectRepository;
        this.context = context;
    }

    @Override
    public ProjectModel save(ProjectModel project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectModel findById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public List<ProjectModel> findAll() {
        return projectRepository.findAll();
    }

    public void chooseProject(ProjectModel project) {
        var currentProject = (ProjectModel) context.getBean("currentProject");

        currentProject.setProjectId(project.getProjectId());
        currentProject.setProjectName(project.getProjectName());
        currentProject.setProjectDescription(project.getProjectDescription());
        currentProject.setProjectDirectory(project.getProjectDirectory());
    }

    @Override
    public void delete(ProjectModel project) {
        projectRepository.delete(project);
    }

    @Override
    public void deleteById(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}