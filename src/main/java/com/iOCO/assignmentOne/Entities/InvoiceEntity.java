package com.iOCO.assignmentOne.Entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "Invoice")
public class InvoiceEntity {

	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;

	@Column(name = "invoice_client")
	private String client;

	@Column(name = "invoice_vatRate")
	private Long vatRate;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER) // Invoice will load lineItems aswell
	private Set<LineItemEntity> items = new HashSet<LineItemEntity>();

	@Transient
	public BigDecimal getVatRate() {
		return new BigDecimal(this.vatRate).setScale(2, RoundingMode.HALF_UP).divide(new BigDecimal(100));
	}

	@Transient
	public BigDecimal getSubTotal() {
		BigDecimal subTotal = new BigDecimal(0);
		for (LineItemEntity item : this.items) {
			subTotal = subTotal.add(item.getLineItemTotal());
		}
		return subTotal.setScale(2, RoundingMode.HALF_UP);
	}

	@Transient
	public BigDecimal getTotal() {
		return this.getSubTotal().multiply(this.getVatRate()).add(this.getSubTotal());
	}
}
