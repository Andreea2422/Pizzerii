package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryMock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PizzaServiceIntegrationWithMockTest {

    private PizzaService service;
    private PaymentRepositoryMock paymentRepo;
    private MenuRepository menuRepo;

    @BeforeEach
    void setUp() {
        paymentRepo = new PaymentRepositoryMock();
        menuRepo = new MenuRepository();
        service = new PizzaService(menuRepo,paymentRepo);
    }

    @AfterEach
    void tearDown() {
        File file = new File("data/paymentsMock.txt");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        service = null;
    }

    @Test
    void getPaymentsWithMockEntity() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getTableNumber()).thenReturn(1);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getAmount()).thenReturn(12.00);
        Payment payment1 = mock(Payment.class);
        Mockito.when(payment1.getTableNumber()).thenReturn(2);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(20.00);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());
        service.addPayment(payment1.getTableNumber(),payment1.getType(),payment1.getAmount());

        assertEquals(2, service.getPayments().size());
    }

    @Test
    void getTotalAmountWithMockEntity() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getTableNumber()).thenReturn(1);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getAmount()).thenReturn(12.00);
        Payment payment1 = mock(Payment.class);
        Mockito.when(payment1.getTableNumber()).thenReturn(2);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(20.00);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());
        service.addPayment(payment1.getTableNumber(),payment1.getType(),payment1.getAmount());

        assertEquals(32.00, service.getTotalAmount(PaymentType.Cash));
    }


}