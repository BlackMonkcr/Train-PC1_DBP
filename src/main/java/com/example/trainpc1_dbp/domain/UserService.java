package com.example.trainpc1_dbp.domain;

import com.example.trainpc1_dbp.infrastructure.ProductRepository;
import com.example.trainpc1_dbp.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    void saveNewProducts(User user) {
        List<Product> products = user.getProducts();
        for (int i = 0; i < products.size(); i++)  {
            for (int j = i+1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    products.remove(j);
                    j--;
                }
            }
            products.get(i).setSeller(user);
            productRepository.save(products.get(i));
        }

        user.setProducts(products);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) {
        saveNewProducts(user);
        if (!user.getProducts().isEmpty()) {
            user.setRole("seller");
        } else {
            user.setRole("buyer");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user ->
                user.getProducts().forEach(product ->
                        productRepository.deleteById(product.getId())));

        userRepository.deleteById(id);
    }

    public boolean updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setAge(user.getAge());
            userToUpdate.setRole(user.getRole());
            userToUpdate.setRating(user.getRating());
            saveNewProducts(user);
            userToUpdate.setProducts(user.getProducts());
            userRepository.save(userToUpdate);
            return true;
        }

        return false;
    }

    public boolean updateUserRating(Long id, int rating) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            float currentRating = userToUpdate.getRating();
            int currentNumberRatings = userToUpdate.getN_ratings();
            float newRating = (currentRating*(currentNumberRatings-1) + rating) / currentNumberRatings;
            userToUpdate.setRating(newRating);
            userRepository.save(userToUpdate);
            return true;
        }

        return false;
    }

    public boolean updateUserCurrentNumberRatings(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            int currentRatings = userToUpdate.getN_ratings();
            userToUpdate.setN_ratings(currentRatings+1);
            userRepository.save(userToUpdate);
            return true;
        }

        return false;
    }

    public boolean updateUserProducts(Long id, List<Product> products) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setProducts(products);
            userRepository.save(userToUpdate);
            return true;
        }

        return false;
    }


    public boolean AddUserProduct(Long id, Product product) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            List<Product> products = userToUpdate.getProducts();
            boolean exists = false;
            for (Product value : products) {
                if (value.equals(product)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                product.setSeller(userToUpdate);
                productRepository.save(product);
                products.add(product);
            }

            userToUpdate.setRole("seller");
            userToUpdate.setProducts(products);
            userRepository.save(userToUpdate);
            return true;
        }

        return false;
    }

    public boolean DeleteUserProduct(Long id, Product product) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            List<Product> products = userToUpdate.getProducts();
            products.remove(product);
            userToUpdate.setProducts(products);
            userRepository.save(userToUpdate);
            return true;
        }

        return false;
    }

}
