package com.odde;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrdersWriterTest {
    Orders orders = new Orders();

    @Test
    public void NoOrder() {
        assertEquals("{\"orders\": []}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrder() {
        orders.AddOrder(new Order(111));
        String order111 = "{\"id\": 111, \"products\": []}";
        assertEquals("{\"orders\": [" + order111 + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void TwoOrders() {
        orders.AddOrder(new Order(111));
        orders.AddOrder(new Order(222));

        String order111 = "{\"id\": 111, \"products\": []}";
        String order222 = "{\"id\": 222, \"products\": []}";
        assertEquals("{\"orders\": [" + order111 + ", " + order222 + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrderWithOneProduct() {
        Order order = new Order(111);
        order.AddProduct(new Product("AAShirt", 1, 3, 2.99, "TWD"));
        orders.AddOrder(order);

        String productAA = "{\"code\": \"AAShirt\", \"color\": \"blue\", \"size\": \"M\", \"price\": 2.99, \"currency\": \"TWD\"}";
        String order111 = "{\"id\": 111, \"products\": [" + productAA + "]}";
        assertEquals("{\"orders\": [" + order111 + "]}", new OrdersWriter(orders).getContents());
    }

    @Test
    public void OneOrderWithOneProductNoSize() {
        Order order = new Order(111);
        order.AddProduct(new Product("BPot", 2, -1, 16.50, "SGD"));
        orders.AddOrder(order);

        String productAA = "{\"code\": \"BPot\", \"color\": \"red\", \"price\": 16.5, \"currency\": \"SGD\"}";
        String order111 = "{\"id\": 111, \"products\": [" + productAA + "]}";
        assertEquals("{\"orders\": [" + order111 + "]}", new OrdersWriter(orders).getContents());
    }
}

