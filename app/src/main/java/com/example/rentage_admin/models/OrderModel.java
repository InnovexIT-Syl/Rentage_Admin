package com.example.rentage_admin.models;

public class OrderModel {
    String id;
    String serviceTitle;
    String orderDate;
    String orderStatus;

    public OrderModel(String id, String serviceTitle, String orderDate, String orderStatus) {
        this.id = id;
        this.serviceTitle = serviceTitle;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
