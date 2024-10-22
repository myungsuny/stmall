package stmall.domain;

import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

@Data
@ToString
public class StockIncresed extends AbstractEvent {

    private Long id;
    private String productName;
    private String imgUrl;
    private Integer stock;
}
