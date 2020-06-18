package com.iOCO.assignmentOne.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iOCO.assignmentOne.Entities.LineItemEntity;

public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {

}
