package server.Orderbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Matcher.MatcherApplication;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderbookController {

    private final OrderbookService orderbookService;
    @Autowired
    private MatcherApplication matcherApplication;

    @Autowired
    public OrderbookController(OrderbookService orderbookService) {
        this.orderbookService = orderbookService;
    }

    @GetMapping
    public Orderbook getOrderbook() {
        return orderbookService.getOrderbook();
    }

    @PostMapping
    public Orderbook processNewOrder(@RequestBody Order newOrder) {
        orderbookService.processNewOrder(newOrder);
        return matcherApplication.getExistingOrders();
    }
}
