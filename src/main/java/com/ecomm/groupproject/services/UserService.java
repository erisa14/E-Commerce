package com.ecomm.groupproject.services;

import com.ecomm.groupproject.models.LoginUser;
import com.ecomm.groupproject.models.Role;
import com.ecomm.groupproject.models.User;
import com.ecomm.groupproject.models.ShoppingCart;
import com.ecomm.groupproject.models.Wishlist;
import com.ecomm.groupproject.repositories.RoleRepository;
import com.ecomm.groupproject.repositories.ShoppingCartRepository;
import com.ecomm.groupproject.repositories.UserRepository;
import com.ecomm.groupproject.repositories.WishlistRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private WishlistRepository wishlistRepository;

    public User register(User newUser, BindingResult result){

        Optional<User> potentionalUser=this.userRepository.findByEmail(newUser.getEmail());

        boolean isFirstUser = userRepository.count() == 0;

        // Assign roles based on the user count
        Role defaultRole = roleRepository.findByName("Customer");
        Role adminRole = roleRepository.findByName("Admin");

        //Email taken
        if (potentionalUser.isPresent()){
            result.rejectValue("email", "EmailTaken", "Email address is already in use!");
        }

        //Password doesn't match confirmation
        if (!newUser.getPassword().equals(newUser.getConfirm())){
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }

        //Return null if result has errors
        if (result.hasErrors()){
            return null;
        }

        //Save user
        else{
            String hashed= BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            if (isFirstUser) {
                newUser.setRole(adminRole);
            } else {
                newUser.setRole(defaultRole);
            }




            // instead of    --   return userRepository.save(newUser);
            User savedUser = userRepository.save(newUser);
            // Automatically create a shopping cart for the user (role - customer)
            if (!isFirstUser) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCartRepository.save(shoppingCart);
                shoppingCart.setUser(savedUser);
                savedUser.setShoppingCart(shoppingCart);
                userRepository.save(savedUser); // Save the updated user
            }
            // Automatically create a wishlist for the user (role - customer)
            if (!isFirstUser) {
                Wishlist wishlist = new Wishlist();
                wishlistRepository.save(wishlist);
                wishlist.setUser(savedUser);
                savedUser.setWishlist(wishlist);
                userRepository.save(savedUser); // Save the updated user
            }
            return savedUser;
        }
    }

    public User login(LoginUser newLogin, BindingResult result){

        Optional<User> potentionalUser=this.userRepository.findByEmail(newLogin.getEmail());

        //User doesn't exist
        if (!potentionalUser.isPresent()){
            result.rejectValue("email", "EmailNotFound", "No user found with that email address");
        }
        else {
            if (!BCrypt.checkpw(newLogin.getPassword(), potentionalUser.get().getPassword())){
                //BCrypt password match fails
                result.rejectValue("password", "Matches", "Invalid password");
            }
        }
        //Return null if result has errors
        if (result.hasErrors()){
            return null;
        }
        else {
            return potentionalUser.get();
        }
    }
    public User findUserById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }






    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
