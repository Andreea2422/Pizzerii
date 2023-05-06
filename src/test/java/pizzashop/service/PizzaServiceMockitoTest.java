package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryMock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceMockitoTest {

    private PizzaService service;
    private PaymentRepository paymentRepo;
    private MenuRepository menuRepo;

    @BeforeEach
    public void SetUp() {
        paymentRepo = mock(PaymentRepository.class);
        menuRepo = mock(MenuRepository.class);
        service = new PizzaService(menuRepo, paymentRepo);
    }

    @Test
    void getPayments() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getTableNumber()).thenReturn(1);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getAmount()).thenReturn(12.00);
        Payment payment1 = mock(Payment.class);
        Mockito.when(payment1.getTableNumber()).thenReturn(2);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(20.00);

        Mockito.when(paymentRepo.getAll()).thenReturn(Arrays.asList(payment, payment1));

        assertEquals(2, service.getPayments().size());
        assertEquals(Arrays.asList(payment, payment1), service.getPayments());

        Mockito.verify(paymentRepo, times(2)).getAll();
    }

    @Test
    void getTotalAmount() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getTableNumber()).thenReturn(1);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getAmount()).thenReturn(12.00);
        Payment payment1 = mock(Payment.class);
        Mockito.when(payment1.getTableNumber()).thenReturn(2);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(20.00);

        Mockito.when(paymentRepo.getAll()).thenReturn(Arrays.asList(payment, payment1));

        assertEquals(32.00, service.getTotalAmount(PaymentType.Cash));

        Mockito.verify(paymentRepo).getAll();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }
}