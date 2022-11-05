package org.snhu.cs320.services;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.snhu.cs320.entities.PurchaseOrder;
import static org.snhu.cs320.validations.EntityValidator.validateAndDoOrThrow;

public class PurchaseOrderService {
	
	static PurchaseOrderService INSTANCE;
	
	final Map<String, PurchaseOrder> entityRepository;
	
	private PurchaseOrderService() {
		entityRepository = new ConcurrentHashMap<>();
	}
	
	public static synchronized PurchaseOrderService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new PurchaseOrderService();
		}
		return INSTANCE;
	}
	
	public PurchaseOrder create(final PurchaseOrder purchaseOrder) {
		Objects.requireNonNull(purchaseOrder);
		
		return validateAndDoOrThrow(
				purchaseOrder,
				p -> {
					if(entityRepository.containsKey(purchaseOrder.purchaseId())) {
						throw new IllegalArgumentException(String.format("An entry with ID [%s] already exists. Did you mean to update?", purchaseOrder.purchaseId()));
					}
					return entityRepository.put(purchaseOrder.purchaseId(), purchaseOrder);
				}
		);
	}
	
	public Optional<PurchaseOrder> deleteById(final String id) {
		checkId(id);
		return Optional.ofNullable(entityRepository.remove(id));
	}
	
	public Optional<PurchaseOrder> findById(final String id) {
		throw new UnsupportedOperationException();
	}
	
	public PurchaseOrder update(final PurchaseOrder purchaseOrder) {
		throw new UnsupportedOperationException();
	}
	
	private static void checkId(final String id) {
		if(id == null || id.trim().length() < 1) {
			throw new IllegalArgumentException("ID is a required argument");
		}
	}
	
}
