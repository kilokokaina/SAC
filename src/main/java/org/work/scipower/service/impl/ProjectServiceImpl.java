package org.work.scipower.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.work.scipower.model.ProjectModel;
import org.work.scipower.repository.ProjectRepository;
import org.work.scipower.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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

    @Override
    public void delete(ProjectModel project) {
        projectRepository.delete(project);
    }

    @Override
    public void deleteById(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}