package edu.poly.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.assignment.dao.CartItemDAO;
import edu.poly.assignment.entity.CartItem;

@SuppressWarnings("deprecation")
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartItemDAO cartItemDAO;


    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemDAO.findById(cartItemId).orElse(null);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemDAO.findAll();
    }

    @Override
    public void add(CartItem item) {
        CartItem cartItem = cartItemDAO.findByMasach(item.getMasach());
        if (cartItem == null) {
            cartItemDAO.save(item);
        } else {
            cartItem.setQty(cartItem.getQty() + item.getQty());
            cartItemDAO.save(cartItem);
        }
    }

    @Override
    public void remove(int id) {
        cartItemDAO.deleteById((long) id);
    }

    @Override
    public CartItem update(int proID, int qty) {
        CartItem cartItem = cartItemDAO.findById((long) proID).orElse(null);
        if (cartItem != null) {
            cartItem.setQty(qty);
            return cartItemDAO.save(cartItem);
        }
        return null;
    }

    @Override
    public void clear() {
        cartItemDAO.deleteAll();
    }

    @Override
    public int getCount() {
        return cartItemDAO.findAll().size();
    }

    @Override
    public double getAmount() {
        List<CartItem> cartItems = cartItemDAO.findAll();
        double amount = 0;
        for (CartItem item : cartItems) {
            amount += item.getQty() * item.getPrice();
        }
        return amount;
    }
}
