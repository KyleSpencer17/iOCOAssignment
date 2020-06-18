package com.iOCO.assignmentOne;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.iOCO.assignmentOne.Entities.InvoiceEntity;
import com.iOCO.assignmentOne.controllers.InvoiceController;
import com.iOCO.assignmentOne.repositories.InvoiceRepository;
import com.iOCO.assignmentOne.repositories.LineItemRepository;
import com.iOCO.assignmentOne.services.InvoiceCreationService;
import com.iOCO.assignmentOne.utils.ResAndReq;
import com.iOCO.assignmentOne.utils.ResAndReq.FullInvoice;
import com.iOCO.assignmentOne.utils.ResAndReq.FullInvoiceListResponse;
import com.iOCO.assignmentOne.utils.ResAndReq.FullInvoiceResponse;

class InvoiceControllerTest {
	
	InvoiceController restController;
	
	@Mock
	InvoiceCreationService service;
	
	@Mock
	InvoiceRepository invoiceRepository;

	@Mock
	LineItemRepository lineItemRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		restController = new InvoiceController(invoiceRepository, lineItemRepository, service);
	}

	@Test
	void testGetInvoices() {
		List<FullInvoice> invoices = new ArrayList<FullInvoice>();
		FullInvoice invoice = new FullInvoice();
		invoice.setId((long)9);
		invoice.setClient("Jim Jones");
		invoice.setVatRate(new BigDecimal(15));
		
		FullInvoice invoice2 = new FullInvoice();
		invoice2.setId((long)10);
		invoice2.setClient("Rick Harris");
		invoice.setVatRate(new BigDecimal(15));
		
		invoices.add(invoice);
		invoices.add(invoice2);
		try {
			 
			when(service.getFullInvoiceList(invoiceRepository.findAll())).thenReturn(invoices);
			ResponseEntity<FullInvoiceListResponse> test = restController.getInvoices();
			assertAll(
					  () -> assertEquals(2, test.getBody().getInvoiceList().size()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testGetInvoice() {
		InvoiceEntity invoice = new InvoiceEntity();
		invoice.setId((long)9);
		invoice.setClient("Jim Jones");
		invoice.setVatRate((long)15);
		Optional<InvoiceEntity> opt = Optional.ofNullable(invoice);
		
		when(invoiceRepository.findById(anyLong())).thenReturn(opt);
		ResponseEntity<FullInvoiceResponse> test = restController.getInvoice((long)1);
		assertAll(() -> assertNotNull(test),
				  () -> assertEquals("Jim Jones", test.getBody().getInvoice().getClient()));
	}
}
