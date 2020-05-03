package com.example.rentage_admin.models;

public class OrderModel {
    String id;
    String serviceTitle;
    String orderDate;
    String orederStatus;

    public OrderModel(String id, String serviceTitle, String orderDate, String orederStatus) {
        this.id = id;
        this.serviceTitle = serviceTitle;
        this.orderDate = orderDate;
        this.orederStatus = orederStatus;
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

    public String getOrederStatus() {
        return orederStatus;
    }

    public void setOrederStatus(String orederStatus) {
        this.orederStatus = orederStatus;
    }
}
