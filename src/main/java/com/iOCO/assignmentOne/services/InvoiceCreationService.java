package com.iOCO.assignmentOne.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iOCO.assignmentOne.Entities.InvoiceEntity;
import com.iOCO.assignmentOne.Entities.LineItemEntity;
import com.iOCO.assignmentOne.repositories.InvoiceRepository;
import com.iOCO.assignmentOne.repositories.LineItemRepository;
import com.iOCO.assignmentOne.utils.ResAndReq;
import com.iOCO.assignmentOne.utils.ResAndReq.FullInvoice;

@Component
public class InvoiceCreationService {

	public InvoiceEntity createInvoice(ResAndReq.InvoiceRequest request, InvoiceRepository invoiceRepository,
			LineItemRepository lineItemRepository) throws Exception {
		InvoiceEntity invoice = new InvoiceEntity();
		invoice.setClient(request.getClient());
		invoice.setInvoiceDate(request.getInvoiceDate());
		invoice.setVatRate(request.getVatRate());
		invoice = invoiceRepository.save(invoice);
		if (request.getItems() != null) {
			for (ResAndReq.LineItem items : request.getItems()) {
				LineItemEntity lineItem = new LineItemEntity();
				lineItem.setDescription(items.getDescription());
				lineItem.setQuantity(items.getQuantity());
				lineItem.setUnitPrice(items.getUnitPrice());
				lineItem.setInvoice(invoice);
				lineItem = lineItemRepository.save(lineItem);
				invoice.getItems().add(lineItem);
				invoice = invoiceRepository.save(invoice);
			}
		}
		return invoice;
	}

	public boolean validateRequest(ResAndReq.InvoiceRequest request) throws Exception {
		if (request == null) {
			throw new Exception("Invalid Request Recieved");
		}
		return true;
	}

	public ResAndReq.FullInvoice getFullInvoice(InvoiceEntity invoice) throws Exception {
		ResAndReq.FullInvoice fullInvoice = new ResAndReq.FullInvoice();
		fullInvoice.setId(invoice.getId());
		fullInvoice.setClient(invoice.getClient());
		fullInvoice.setInvoiceDate(invoice.getInvoiceDate());
		fullInvoice.setItems(invoice.getItems());
		fullInvoice.setVatRate(invoice.getVatRate());
		fullInvoice.setSubTotal(invoice.getSubTotal());
		fullInvoice.setTotal(invoice.getTotal());
		return fullInvoice;
	}

	public List<ResAndReq.FullInvoice> getFullInvoiceList(List<InvoiceEntity> invoices) throws Exception {
		List<FullInvoice> invoiceList = new ArrayList<FullInvoice>();
		for (InvoiceEntity invoice : invoices) {
			ResAndReq.FullInvoice fullInvoice = new ResAndReq.FullInvoice();
			fullInvoice.setId(invoice.getId());
			fullInvoice.setClient(invoice.getClient());
			fullInvoice.setInvoiceDate(invoice.getInvoiceDate());
			fullInvoice.setItems(invoice.getItems());
			fullInvoice.setVatRate(invoice.getVatRate());
			fullInvoice.setSubTotal(invoice.getSubTotal());
			fullInvoice.setTotal(invoice.getTotal());
			invoiceList.add(fullInvoice);
		}
		return invoiceList;
	}
}
