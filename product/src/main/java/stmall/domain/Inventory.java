package stmall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.ProductApplication;
import stmall.domain.SoldOut;
import stmall.domain.StockDecresed;
import stmall.domain.StockIncresed;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private String imgUrl;

    private Integer stock;

    @PostUpdate
    public void onPostUpdate() {
        StockDecresed stockDecresed = new StockDecresed(this);
        stockDecresed.publishAfterCommit();

        SoldOut soldOut = new SoldOut(this);
        soldOut.publishAfterCommit();

        StockIncresed stockIncresed = new StockIncresed(this);
        stockIncresed.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreseStock(DeliveryCompleted deliveryCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        StockDecresed stockDecresed = new StockDecresed(inventory);
        stockDecresed.publishAfterCommit();
        SoldOut soldOut = new SoldOut(inventory);
        soldOut.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryCompleted.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);

            StockDecresed stockDecresed = new StockDecresed(inventory);
            stockDecresed.publishAfterCommit();
            SoldOut soldOut = new SoldOut(inventory);
            soldOut.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increseStock(DeliveryReturned deliveryReturned) {
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        StockIncresed stockIncresed = new StockIncresed(inventory);
        stockIncresed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryReturned.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);

            StockIncresed stockIncresed = new StockIncresed(inventory);
            stockIncresed.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
