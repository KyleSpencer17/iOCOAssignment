package com.iOCO.assignmentOne.Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") // Only Compares on the Id
@Table(name = "Line_Item")
public class LineItemEntity {
	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;

	@JsonIgnore // Wont serialize this
	@ManyToOne(fetch = FetchType.LAZY) // Database wont load all of the invoices Data
	@JoinColumn(name = "invoice", nullable = true)
	private InvoiceEntity invoice;

	@Column(name = "item_description")
	private String description;

	@Column(name = "item_quantity")
	private Long quantity;

	@Column(name = "item_unit_price")
	private Long unitPrice;
	
	@Transient
	public BigDecimal getLineItemTotal() {
		return new BigDecimal(this.getUnitPrice() * this.getQuantity()).setScale(2,RoundingMode.HALF_UP);
	}
}
