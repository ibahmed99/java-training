package server;

import server.Orderbook.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    private OrderbookController orderbookController;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(orderbookController).isNotNull();
    }


    @Test
    public void greetingShouldReturnMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("hello CoinFake user");
    }

    @Test
    public void gettingOrderbookShouldReturnCorrectJSON() throws Exception {
        PrivateOrderbook expectedJSON = (new Orderbook()).privatiseOrderbook();
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/order",
                String.class)).contains("" + expectedJSON + "");
    }

}
