package com.iOCO.assignmentOne;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.iOCO.assignmentOne.Entities.InvoiceEntity;
import com.iOCO.assignmentOne.controllers.InvoiceController;
import com.iOCO.assignmentOne.repositories.InvoiceRepository;
import com.iOCO.assignmentOne.repositories.LineItemRepository;
import com.iOCO.assignmentOne.services.InvoiceCreationService;
import com.iOCO.assignmentOne.utils.ResAndReq.FullInvoiceResponse;
import com.iOCO.assignmentOne.utils.ResAndReq.InvoiceRequest;
import com.iOCO.assignmentOne.utils.ResAndReq.InvoiceResponse;

@RunWith(SpringRunner.class)
@DataJpaTest
class PostRequestTests {

	InvoiceController restController;

	InvoiceCreationService service;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private LineItemRepository lineItemRepository;

	@BeforeEach
	void setUp() throws Exception {
		restController = new InvoiceController(invoiceRepository, lineItemRepository, service);
	}

	@Test
	void addInvoiceTest() {
		InvoiceRequest request = new InvoiceRequest();
		InvoiceEntity invoice = new InvoiceEntity();
		request.setClient("Jim Jones");
		request.setVatRate((long)15);
		
		ResponseEntity<InvoiceResponse> test = restController.addInvoice(request);
		assertAll(() -> assertNotNull(test),
				  () -> assertEquals("Jim Jones", test.getBody().getInvoice().getClient()),
				  () -> assertNotNull(test.getBody().getInvoice().getId()));
	}

}
