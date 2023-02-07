package org.snhu.cs320.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseOrderTest {

    private final Validator validator = (Validation.buildDefaultValidatorFactory()).getValidator();

    @Test
    void testPurchaseId_TooManyCharacters() {
        final PurchaseOrder p = createPurchaseOrder(
                "123456789012345",
                "1234",
                "1234567890"
        );

        assertThat(validator.validate(p).stream().findFirst())
                .isPresent()
                .get()
                .extracting(ConstraintViolation::getMessage)
                .isEqualTo("PurchaseId cannot be longer than 10 characters");
    }

    private static PurchaseOrder createPurchaseOrder(final String purchaseId, final String customerId, final String contactPhone) {
        return new PurchaseOrder(
                purchaseId,
                customerId,
                contactPhone,
                List.of(
                        new LineItem("1234", 1L, new BigDecimal("1.24"))
                )
        );
    }

}
