package pizzashop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment(1, PaymentType.Cash, 12.00);
    }

    void getTableNumber() {
        assertEquals(1, payment.getTableNumber());
    }

    void setTableNumber() {
        payment.setTableNumber(2);
        assertEquals(2, payment.getTableNumber());
    }

    void getType() {
        assertEquals(PaymentType.Cash, payment.getType());
    }

    void setType() {
        payment.setType(PaymentType.Card);
        assertEquals(PaymentType.Card, payment.getType());
    }

    void getAmount() {
        assertEquals(12.00, payment.getAmount());
    }

    void setAmount() {
        payment.setAmount(15.00);
        assertEquals(15.00, payment.getAmount());
    }

    @Test
    void TestAll(){
        getTableNumber();
        setTableNumber();
        getType();
        setType();
        getAmount();
        setAmount();
    }
}