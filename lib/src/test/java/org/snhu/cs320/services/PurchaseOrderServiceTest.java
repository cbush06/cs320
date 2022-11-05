package org.snhu.cs320.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.snhu.cs320.entities.LineItem;
import org.snhu.cs320.entities.PurchaseOrder;

import jakarta.validation.ValidationException;

public class PurchaseOrderServiceTest {
	
	PurchaseOrderService service;
	
	@BeforeEach
	void init() {
		PurchaseOrderService.INSTANCE = null;
		service = PurchaseOrderService.getInstance();
		service.entityRepository.putAll(Map.of(
                "9999888800", new PurchaseOrder("9999888800", "9999888800", "9998887771", List.of(new LineItem("ABCD1234", 1L, new BigDecimal("1.00")))),
                "9999888801", new PurchaseOrder("9999888801", "9999888801", "9998887772", List.of(new LineItem("ABCD1234", 1L, new BigDecimal("1.00")))),
                "9999888802", new PurchaseOrder("9999888802", "9999888802", "9998887773", List.of(new LineItem("ABCD1234", 1L, new BigDecimal("1.00")))),
                "9999888803", new PurchaseOrder("9999888803", "9999888803", "9998887774", List.of(new LineItem("ABCD1234", 1L, new BigDecimal("1.00"))))
        ));
	}
	
	@Test
	public void create_PreventsNullArguments() {
		assertThrows(
				NullPointerException.class,
				() -> service.create(null)
		);
	}
	
	@Test
	public void create_PreventsAddingExistingPurchaseOrder() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> service.create(new PurchaseOrder("9999888800", "Hello", "2222222222", List.of(new LineItem("ABCD", 1L, BigDecimal.ZERO))))
		);
	}
	
	@Test
	public void create_Successful() {
		service.create(new PurchaseOrder("1234567890", "1234", "5553334444", List.of(new LineItem("ABCD1234", 1L, BigDecimal.ONE))));
		assertThat(service.entityRepository).containsKey("1234567890");
	}
	
	@Test
	public void create_InvalidEntityBlocked() {
		assertThrows(ValidationException.class, () -> service.create(new PurchaseOrder("", "", "", Collections.emptyList())));
	}
	
	@Test
	public void deleteById_IdExists() {
		assertThat(service.deleteById("9999888800"))
			.isNotEmpty()
			.get()
			.extracting(p -> p.purchaseId())
			.isEqualTo("9999888800");
		
		assertThat(service.entityRepository)
			.doesNotContainKey("9999888800");
	}
	
	@Test
	public void deleteById_IdDoesNotExist() {
		assertThat(service.deleteById("abcd"))
			.isEmpty();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
		"     ",
		"\n\n\r\r",
		""
	})
	public void deleteById_InvalidId(final String value) {
		assertThrows(IllegalArgumentException.class, () -> service.deleteById(value));
	}
	
	@Test
	public void deleteById_NullId() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteById(null));
	}
	
	public void findById() {
	}
	
	public void update() {
	}
}
