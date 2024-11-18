package org.work.scipower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.scipower.model.ProjectModel;

public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
}
