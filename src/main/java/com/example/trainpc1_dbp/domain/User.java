package com.example.trainpc1_dbp.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String email;

    private String password;

    @Column(name = "age", nullable = true)
    private int age;

    private String role;

    private float rating;

    private int N_ratings;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    /* ---------------------------------------------------------------------------------------- */

    // Constructors
    public User() {}

    public User(String username, String email, String password, int age, List<Product> products) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.products = products;
        this.N_ratings = 0;
        this.rating = 0;
    }

    /* ---------------------------------------------------------------------------------------- */

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
    public String getRole() { return role; }
    public float getRating() { return rating; }
    public int getN_ratings() { return N_ratings; }
    public List<Product> getProducts() { return products; }

    /* ---------------------------------------------------------------------------------------- */

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setAge(int age) { this.age = age; }
    public void setRole(String role) { this.role = role; }
    public void setRating(float rating) { this.rating = rating; }
    public void setN_ratings(int N_ratings) { this.N_ratings = N_ratings; }
    public void setProducts(List<Product> products) { this.products = products; }

    /* ---------------------------------------------------------------------------------------- */

    // Methods

    @Override
    public String toString() {
        return "User [id: " + id + ",\nusername: " + username + ",\nemail: " + email +
                ",\npassword: " + password + ",\nage: " + age + ",\nrole: " + role +
                ",\nrating: " + rating + ",\nN_ratings: " + N_ratings + "\n]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User user))
            return false;
        return (this.id.equals(user.getId()) || this.username.equals(user.getUsername()));
    }

    /* ---------------------------------------------------------------------------------------- */


}
