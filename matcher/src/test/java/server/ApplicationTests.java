package server;

import server.Orderbook.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private OrderbookController orderbookController;

    @Test
    void contextLoads() throws Exception {

    }

}
