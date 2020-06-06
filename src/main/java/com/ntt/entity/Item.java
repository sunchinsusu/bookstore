package com.ntt.entity;

import javax.persistence.*;

@Entity
@Table(name = "ntt_item")
public class Item {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Book book;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "priceAfterDiscount")
    private float priceAfterDiscount;

    @Column(name = "total")
    private float total;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Item() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(float priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
