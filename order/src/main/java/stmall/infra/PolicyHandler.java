package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StockIncresed'"
    )
    public void wheneverStockIncresed_NotifyToWaitingUsers(
        @Payload StockIncresed stockIncresed
    ) {
        StockIncresed event = stockIncresed;
        System.out.println(
            "\n\n##### listener NotifyToWaitingUsers : " +
            stockIncresed +
            "\n\n"
        );

        // Sample Logic //
        Order.notifyToWaitingUsers(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryReturned'"
    )
    public void wheneverDeliveryReturned_UpdateOrderStatus(
        @Payload DeliveryReturned deliveryReturned
    ) {
        DeliveryReturned event = deliveryReturned;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " +
            deliveryReturned +
            "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCanceled'"
    )
    public void wheneverDeliveryCanceled_UpdateOrderStatus(
        @Payload DeliveryCanceled deliveryCanceled
    ) {
        DeliveryCanceled event = deliveryCanceled;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " +
            deliveryCanceled +
            "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCompleted'"
    )
    public void wheneverDeliveryCompleted_UpdateOrderStatus(
        @Payload DeliveryCompleted deliveryCompleted
    ) {
        DeliveryCompleted event = deliveryCompleted;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " +
            deliveryCompleted +
            "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_UpdateOrderStatus(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + deliveryStarted + "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
