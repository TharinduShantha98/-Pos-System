package entity;

import model.ItemDetail;

import java.util.ArrayList;

public class Order {

    private String orderId;
    private String orderDate;
    private String orderTime;
    private String cusId;
    private String employId;
    private Double totalCost;
    private ArrayList<ItemDetail> items;

    public Order(String orderId, String orderDate, String orderTime, String cusId, String employId, Double totalCost, ArrayList<ItemDetail> items) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setOrderTime(orderTime);
        this.setCusId(cusId);
        this.setEmployId(employId);
        this.setTotalCost(totalCost);
        this.setItems(items);
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getEmployId() {
        return employId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<ItemDetail> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetail> items) {
        this.items = items;
    }

}
