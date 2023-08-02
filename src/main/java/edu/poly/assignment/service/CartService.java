package edu.poly.assignment.service;

import java.util.Collection;
import java.util.List;

import edu.poly.assignment.entity.CartItem;

public interface CartService {

    CartItem getCartItemById(Long cartItemId);

    List<CartItem> getAllCartItems();

    void add(CartItem item);

    void remove(int id);

    CartItem update(int proID, int qty);

    void clear();

    int getCount();

    double getAmount();
}
