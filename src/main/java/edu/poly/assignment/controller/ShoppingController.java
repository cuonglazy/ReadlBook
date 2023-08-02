package edu.poly.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.assignment.entity.CartItem;
import edu.poly.assignment.entity.Product;
import edu.poly.assignment.service.CartService;
import edu.poly.assignment.service.ProductService;

@Controller
@RequestMapping("cart")
public class ShoppingController {
    @Autowired
    CartService cartItemService;

    @Autowired
    ProductService productService;

    @GetMapping("views")
    public String viewCart(Model model) {
        model.addAttribute("CART_ITEMS", cartItemService.getAllCartItems());
        model.addAttribute("TOTAL", cartItemService.getAmount());
        return "cart";
    }

    @GetMapping("add/{id}")
    public String add(@PathVariable("id") Integer id) {
        Product product = productService.findProductByID(id);
        if (product != null) {
            CartItem item = new CartItem();
            item.setMasach(product);
            item.setPhoto(product.getPhoto());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQty(1);
            cartItemService.add(item);
        }
        return "redirect:/cart/views";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        cartItemService.remove(id);
        return "redirect:/cart/views";
    }

    @GetMapping("clear")
    public String clear() {
        cartItemService.clear();
        return "redirect:/cart/views";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") Integer proID, @RequestParam("qty") Integer qty) {
        System.out.println(proID + " - " + qty);
        cartItemService.update(proID, qty);
        return "redirect:/cart/views";
    }
}
