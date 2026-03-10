package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orderData = new ArrayList<>();

    public Order save(final Order order){
        int i = 0;
        for (final Order savedOrder : orderData){
            if (savedOrder.getId().equals(order.getId())){
                orderData.remove(i);
                orderData.add(i,order);
                return order;
            }
            i+=1;
        }
        orderData.add(order);
        return order;
    }

    public Order findById(final String id){
        for (final Order savedOrder : orderData){
            if (savedOrder.getId().equals(id)){
                return savedOrder;
            }
        }
        return null;
    }

    public List<Order> findAllByAuthor(final String author){
        final List<Order> result = new ArrayList<>();
        for (final Order savedOrder : orderData){
            if (savedOrder.getAuthor().equals(author)){
                result.add(savedOrder);
            }
        }
        return result;
    }
}
