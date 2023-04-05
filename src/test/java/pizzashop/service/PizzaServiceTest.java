package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryMock;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceTest {
    private PizzaService pizzaServ;

    @BeforeEach
    void SetUp(){
        pizzaServ = new PizzaService(new MenuRepository(), new PaymentRepositoryMock());
    }

    @AfterEach
    void TearDown(){
        File file = new File("data/paymentsMock.txt");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Order(1)
    @ValueSource(ints = {1,2,3,4,5,6,7,8})
    @DisplayName("Repeating ECP valid test")
    @Tag("VALID")
    void validPaymentECP(int table){
        PaymentType type = PaymentType.Cash;
        double amount = 25;

        pizzaServ.addPayment(table,type,amount);
        Payment payment = pizzaServ.getPayments().get(0);

        assertNotNull(payment);
        assertEquals(payment.getTableNumber(),table);
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(doubles = {0,1,10.5,23.9,30.5,35,60.9,Double.MAX_VALUE-1,Double.MAX_VALUE})
    @DisplayName("Repeating BVA valid test")
    @Tag("VALID")
    void validPaymentBVA(double amount){
        PaymentType type = PaymentType.Cash;
        int table = 7;

        pizzaServ.addPayment(table,type,amount);
        Payment payment = pizzaServ.getPayments().get(0);

        assertNotNull(payment);
        assertEquals(payment.getAmount(),amount);
    }

    @Test
    @Order(2)
    @DisplayName("Repeating ECP invalid test")
    @Tag("INVALID")
    void invalidPaymentECP(){
        PaymentType type = PaymentType.Cash;
        double amount = 10.5;
        int table = 9;

        pizzaServ.addPayment(table,type,amount);

        assertTrue(pizzaServ.getPayments().isEmpty());
    }

    @Test
    @Order(4)
    @DisplayName("Repeating BVA invalid test")
    @Tag("INVALID")
    void invalidPaymentBVA(){
        PaymentType type = PaymentType.Card;
        double amount = -1;
        int table = 3;

        pizzaServ.addPayment(table,type,amount);

        assertTrue(pizzaServ.getPayments().isEmpty());
    }
}