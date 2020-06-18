package com.iOCO.assignmentOne.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.iOCO.assignmentOne.Entities.InvoiceEntity;
import com.iOCO.assignmentOne.Entities.LineItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResAndReq {

	@Data
	@AllArgsConstructor
	public static class InvoiceListResponse {
		private boolean status;
		private String description;
		private List<InvoiceEntity> list;
	}

	@Data
	@AllArgsConstructor
	public static class InvoiceResponse {
		private boolean status;
		private String description;
		private InvoiceEntity invoice;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class LineItemResponse {
		private boolean status;
		private String description;
		private Set<LineItemEntity> list;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class InvoiceRequest {
		private String client;
		private long vatRate;
		private Date invoiceDate;
		List<LineItem> items;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class LineItem {
		private String description;
		private long unitPrice;
		private long quantity;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FullInvoice {
		private long id;
		private String client;
		private BigDecimal vatRate;
		private Date invoiceDate;
		private Set<LineItemEntity> items;
		private BigDecimal subTotal;
		private BigDecimal total;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FullInvoiceResponse {
		private boolean status;
		private String description;
		private FullInvoice invoice;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FullInvoiceListResponse {
		private boolean status;
		private String description;
		private List<FullInvoice> invoiceList;
	}
}
