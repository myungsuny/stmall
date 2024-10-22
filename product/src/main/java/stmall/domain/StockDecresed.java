package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class StockDecresed extends AbstractEvent {

    private Long id;
    private String productName;
    private String imgUrl;
    private Integer stock;

    public StockDecresed(Inventory aggregate) {
        super(aggregate);
    }

    public StockDecresed() {
        super();
    }
}
//>>> DDD / Domain Event
