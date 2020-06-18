package com.iOCO.assignmentOne;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iOCO.assignmentOne.Entities.InvoiceEntity;
import com.iOCO.assignmentOne.Entities.LineItemEntity;
import com.iOCO.assignmentOne.repositories.InvoiceRepository;
import com.iOCO.assignmentOne.repositories.LineItemRepository;

@SpringBootApplication
public class Application {
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	LineItemRepository lineItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean // Makes it a Spring bean; Spring will run method on startup
	public ApplicationRunner initRunner() {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) throws Exception {
				final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				// TODO Auto-generated method stub
				long count = invoiceRepository.count();

				if (count == 0) {
					InvoiceEntity invoice = new InvoiceEntity();
					invoice.setClient("Simon Thomas");
					Date d1 = df.parse("2019-03-22");
					invoice.setInvoiceDate(d1);
					invoice.setVatRate((long) 15.65);

					InvoiceEntity invoice2 = new InvoiceEntity();
					invoice2.setClient("Lucario Ltd");
					d1 = df.parse("2019-01-12");
					invoice2.setInvoiceDate(d1);
					invoice2.setVatRate((long) 15.65);

					invoice = invoiceRepository.save(invoice);
					invoice2 = invoiceRepository.save(invoice2);

					LineItemEntity item1 = new LineItemEntity();
					item1.setInvoice(invoice);
					item1.setQuantity((long) 5);
					item1.setDescription("Plastic Props");
					item1.setUnitPrice((long) 110);

					LineItemEntity item2 = new LineItemEntity();
					item2.setInvoice(invoice2);
					item2.setDescription("Pamphlets");
					item2.setQuantity((long) 300);
					item2.setUnitPrice((long) 50);

					lineItemRepository.save(item1);
					lineItemRepository.save(item2);
				}

			}
		};
	}
}
