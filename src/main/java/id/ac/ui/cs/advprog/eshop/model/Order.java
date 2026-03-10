package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import lombok.Getter;
import lombok.Builder;

import java.util.List;

@Builder
@Getter
public class Order {
    private String id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    private String status;

    public Order(final String id, final List<Product> products, final Long orderTime, final String author){
        this.id = id;
        this.orderTime = orderTime;
        this.author = author;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();

        if (products.isEmpty()){
            throw new IllegalArgumentException();
        } else{
            this.products = products;
        }
    }

    public Order(final String id,final List<Product> products,final Long orderTime,final String author,final String status){
        this(id,products,orderTime,author);
        if (OrderStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(final String status){
        if (OrderStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
