package com.example.trainpc1_dbp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User seller;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "sold", nullable = false)
    private int sold;

    /* ---------------------------------------------------------------------------------------- */

    // Constructors
    public Product() {}

    public Product(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.rating = 0;
        this.sold = 0;
    }

    /* ---------------------------------------------------------------------------------------- */

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    public User getSeller() { return seller; }
    public int getRating() { return rating; }
    public int getSold() { return sold; }

    /* ---------------------------------------------------------------------------------------- */

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(int price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setSeller(User seller) { this.seller = seller; }
    public void setRating(int rating) { this.rating = rating; }
    public void setSold(int sold) { this.sold = sold; }

    /* ---------------------------------------------------------------------------------------- */

    // Methods

    @Override
    public String toString() {
        return "Product [id: " + id + ",\name: " + name + ",\ndescription: " + description +
                ",\nprice: " + price + ",\nstock: " + stock + ", \nid_seller:\n\t" + seller.toString() +
                ",\nrating: " + rating + ",\nsold: " + sold + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Product product))
            return false;
        if (Objects.equals(this.id, product.getId()) && Objects.equals(this.name, product.getName()) &&
                Objects.equals(this.description, product.getDescription()) && Objects.equals(this.price, product.getPrice()) &&
                Objects.equals(this.stock, product.getStock()) && Objects.equals(this.seller, product.getSeller()) &&
                Objects.equals(this.rating, product.getRating()) && Objects.equals(this.sold, product.getSold()))
            return true;

        return this.id.equals(product.getId());
    }

    /* ---------------------------------------------------------------------------------------- */

}
