package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PaymentRepositoryTest {

    private Payment model;
    private PaymentRepositoryMock repo;

    @BeforeEach
    public void SetUp(){
        model = mock(Payment.class);
        repo = new PaymentRepositoryMock();
    }

    @Test
    void add() {
        repo.add(model);
        assertEquals(1,repo.getAll().size());
    }

    @Test
    void getAll() {
        List<Payment> list = repo.getAll();
        assertEquals(0,list.size());
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

}