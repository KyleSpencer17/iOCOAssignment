package com.iOCO.assignmentOne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iOCO.assignmentOne.Entities.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

	@Query("Select i.id from InvoiceEntity i where client =:clientName")
	long getIdFromName(@Param("clientName") String clientName);

}
