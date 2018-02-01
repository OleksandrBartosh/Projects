package ua.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Model;

public interface ModelRepository extends JpaNameRepository<Model, Integer>, JpaSpecificationExecutor<Model> {

}
