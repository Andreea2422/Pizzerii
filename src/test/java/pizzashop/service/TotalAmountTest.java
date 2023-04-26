package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryMock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TotalAmountTest {
    private PizzaService pizzaServMock;

    @BeforeEach
    void SetUp(){
        pizzaServMock=new PizzaService(new MenuRepository(),new PaymentRepositoryMock());
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

    @Test
    void testListEmpty(){
        PaymentType type=PaymentType.Cash;

        double total= pizzaServMock.getTotalAmount(type);

        assertEquals(0.0f, total);

    }

    @Test
    void testListSize1AndTypeWrong(){
        PaymentType type=PaymentType.Cash;
        pizzaServMock.addPayment(4,PaymentType.Card,13.97f);

        double total= pizzaServMock.getTotalAmount(type);

        assertEquals(0.0f, total);

    }

    @Test
    void testListSize1AndTypeRight(){
        PaymentType type=PaymentType.Cash;
        pizzaServMock.addPayment(4,PaymentType.Cash,13.97f);

        double total= pizzaServMock.getTotalAmount(type);

        assertEquals(13.97f, total);


    }

    @Test
    void testListSize2(){
        PaymentType type=PaymentType.Cash;
        pizzaServMock.addPayment(4,PaymentType.Cash,13.97f);
        pizzaServMock.addPayment(1,PaymentType.Card,3.45f);

        double total= pizzaServMock.getTotalAmount(type);

        assertEquals(13.97f, total);


    }

    @Test
    void testListSizeN(){
        PaymentType type=PaymentType.Cash;
        pizzaServMock.addPayment(4,PaymentType.Cash,13.97f);
        pizzaServMock.addPayment(1,PaymentType.Card,3.45f);
        pizzaServMock.addPayment(3,PaymentType.Cash,12f);
        pizzaServMock.addPayment(2,PaymentType.Card,45.7f);
        pizzaServMock.addPayment(1,PaymentType.Card,67.3f);
        pizzaServMock.addPayment(6,PaymentType.Cash,10.03f);
        pizzaServMock.addPayment(5,PaymentType.Card,29.6f);
        pizzaServMock.addPayment(7,PaymentType.Card,39.7f);
        pizzaServMock.addPayment(8,PaymentType.Card,38.5f);
        pizzaServMock.addPayment(2,PaymentType.Card,98.7f);
        pizzaServMock.addPayment(3,PaymentType.Cash,25f);
        pizzaServMock.addPayment(2,PaymentType.Card,35.7f);
        pizzaServMock.addPayment(7,PaymentType.Cash,15f);
        pizzaServMock.addPayment(8,PaymentType.Card,94.6f);
        pizzaServMock.addPayment(4,PaymentType.Card,50f);
        pizzaServMock.addPayment(2,PaymentType.Cash,60f);
        pizzaServMock.addPayment(3,PaymentType.Card,72.5f);
        pizzaServMock.addPayment(7,PaymentType.Card,48.5f);
        pizzaServMock.addPayment(1,PaymentType.Card,85.7f);
        pizzaServMock.addPayment(5,PaymentType.Card,19.7f);

        double total= pizzaServMock.getTotalAmount(type);

        assertEquals(136f, total);


    }




}