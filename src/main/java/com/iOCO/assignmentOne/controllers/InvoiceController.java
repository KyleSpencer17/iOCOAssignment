package com.iOCO.assignmentOne.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.iOCO.assignmentOne.repositories.InvoiceRepository;
import com.iOCO.assignmentOne.repositories.LineItemRepository;
import com.iOCO.assignmentOne.services.InvoiceCreationService;
import com.iOCO.assignmentOne.utils.*;

@RestController
public class InvoiceController {
	private final InvoiceRepository invoiceRepository;
	private final LineItemRepository lineItemRepository;
	private final InvoiceCreationService service;
	
	public InvoiceController(InvoiceRepository invoiceRepository, LineItemRepository lineItemRepository,InvoiceCreationService service) {
		this.invoiceRepository = invoiceRepository;
		this.lineItemRepository = lineItemRepository;
		this.service = service;
	}

	@GetMapping(value = "/invoices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResAndReq.FullInvoiceListResponse> getInvoices() {
		ResAndReq.FullInvoiceListResponse response = new ResAndReq.FullInvoiceListResponse();
		try {
			List<ResAndReq.FullInvoice> invoices = service.getFullInvoiceList(invoiceRepository.findAll());
			response = new ResAndReq.FullInvoiceListResponse(true, "Data Retrived Successfully", invoices);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response = new ResAndReq.FullInvoiceListResponse(false, e.getMessage(), null);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(value = "/invoices/{invoiceId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResAndReq.FullInvoiceResponse> getInvoice(@PathVariable long invoiceId)  {
		InvoiceCreationService service = new InvoiceCreationService();
		ResAndReq.FullInvoiceResponse response;
		try {
			response = new ResAndReq.FullInvoiceResponse(true, "Data Retrived Successfully",
					service.getFullInvoice(invoiceRepository.findById(invoiceId).get()));
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response = new ResAndReq.FullInvoiceResponse(false, e.getMessage(), null);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping(value = "/invoices", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResAndReq.InvoiceResponse> addInvoice(@RequestBody ResAndReq.InvoiceRequest request) {
		ResAndReq.InvoiceResponse response;
		InvoiceCreationService service = new InvoiceCreationService();
		try {
			service.validateRequest(request);
			response = new ResAndReq.InvoiceResponse(true, "Data Retrived Successfully",
					service.createInvoice(request, invoiceRepository, lineItemRepository));
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response = new ResAndReq.InvoiceResponse(false, e.getMessage(), null);
			return ResponseEntity.badRequest().body(response);
		}
	}
}
