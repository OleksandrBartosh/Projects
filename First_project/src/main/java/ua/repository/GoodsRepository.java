package ua.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Goods;

public interface GoodsRepository extends JpaNameRepository<Goods, Integer>, JpaSpecificationExecutor<Goods>{

}