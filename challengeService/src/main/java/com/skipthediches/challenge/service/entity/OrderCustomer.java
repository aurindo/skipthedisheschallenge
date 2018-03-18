package com.skipthediches.challenge.service.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orderCustomer")
public class OrderCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderCustomerGenerator")
    @TableGenerator(name = "orderCustomerGenerator", allocationSize = 1)
    private Long id;

    private Long orderNumber;

    private Date orderTime;

    @Enumerated(EnumType.ORDINAL)
    private OrderCustomerStatusEnum status;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;

    @ManyToMany
    private List<Product> productList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
