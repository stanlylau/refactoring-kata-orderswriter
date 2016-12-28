package com.odde;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrdersWriterTest {
    Orders orders = new Orders();
    Order order111 = new Order(111);

    @Test
    public void NoOrder() {
        assertEquals("{\"orders\": []}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrder() {
        orders.AddOrder(order111);

        String order111 = "{\"id\": 111, \"products\": []}";
        assertEquals("{\"orders\": [" + order111 + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void TwoOrders() {
        orders.AddOrder(order111);
        orders.AddOrder(new Order(222));

        String order111Json = "{\"id\": 111, \"products\": []}";
        String order222Json = "{\"id\": 222, \"products\": []}";
        assertEquals("{\"orders\": [" + order111Json + ", " + order222Json + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrderWithOneProduct() {
        order111.AddProduct(new Product("Shirt", 1, 3, 2.99, "TWD"));
        orders.AddOrder(order111);

        String productShirtJson = "{\"code\": \"Shirt\", \"color\": \"blue\", \"size\": \"M\", \"price\": 2.99, \"currency\": \"TWD\"}";
        String order111Json = "{\"id\": 111, \"products\": [" + productShirtJson + "]}";
        assertEquals("{\"orders\": [" + order111Json + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrderWithOneProductNoSize() {
        order111.AddProduct(new Product("Pot", 2, -1, 16.50, "SGD"));
        orders.AddOrder(order111);

        String productPotJson = "{\"code\": \"Pot\", \"color\": \"red\", \"price\": 16.5, \"currency\": \"SGD\"}";
        String order111Json = "{\"id\": 111, \"products\": [" + productPotJson + "]}";
        assertEquals("{\"orders\": [" + order111Json + "]}", new OrdersWriter(orders).getContents());
    }
}
