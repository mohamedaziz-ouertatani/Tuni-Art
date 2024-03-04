package tn.esprit;

import tn.esprit.entities.Art;
import tn.esprit.entities.Cart;
import tn.esprit.entities.Order;
import tn.esprit.services.CartService;
import tn.esprit.services.OrderService;
import tn.esprit.utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       // Create an instance of MyDatabase to get the connection
        MyDatabase myDatabase = MyDatabase.getInstance();
        Connection connection = myDatabase.getConn();

       /*  // Create an instance of CartService and OrderService
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();

        // Create an instance of Cart
        Cart cart = new Cart();
        cart.setUserId(1); // Assuming the user ID is 1

        // Save the cart to the database
        cartService.save(cart);

        // Create an instance of Order
        Order order = new Order();
        order.setUserId(1); // Assuming the user ID is 1
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(100.0f);
        order.setStatus(1); // Assuming the status is 1

        // Save the order to the database
        orderService.createOrder(order);
        */
/*
import tn.esprit.entities.Order;
import tn.esprit.services.OrderService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;*/


                OrderService orderService = new OrderService();

                // Create a new order
                Order order = new Order();
                order.setUserId(1);
                order.setTotalPrice(100.0f);
                order.setOrderDate(LocalDate.now());
                order.setStatus(1);

                try {
                    // Add the order
                    orderService.add(order);

                    // Display the list of orders
                    List<Order> orders = orderService.displayList();
                    System.out.println("List of Orders:");
                    for (Order o : orders) {
                        System.out.println(o);
                    }

                    // Update the order
                    order.setTotalPrice(150.0f);
                    orderService.update(order);

                    // Display the updated list of orders
                    orders = orderService.displayList();
                    System.out.println("\nUpdated List of Orders:");
                    for (Order o : orders) {
                        System.out.println(o);
                    }

                    // Delete the order
                    orderService.delete(order.getOrderId());

                    // Display the list of orders after deletion
                    orders = orderService.displayList();
                    System.out.println("\nList of Orders after Deletion:");
                    for (Order o : orders) {
                        System.out.println(o);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
}





