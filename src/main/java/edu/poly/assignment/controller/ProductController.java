package edu.poly.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.assignment.dao.ProductDAO;
import edu.poly.assignment.entity.Product;
import edu.poly.assignment.entity.Report;
import edu.poly.assignment.service.ProductService;
import edu.poly.assignment.service.SessionService;

@Controller
public class ProductController {
    @Autowired
    ProductDAO productDAO;

    @Autowired
    SessionService sessionService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin/report")
    public String report(Model model) {
        List<Report> reports = productDAO.getInventoryByCategory();
        model.addAttribute("REPORTS", reports);
        return "admin/pages/tables/report";
    }

    @GetMapping("/assert/cart")
    public String viewCart() {
        return "cart";
    }

    @GetMapping("/assert/shop")
    public String viewShop(Model model) {
        List<Product> pr = productService.findAll();
        model.addAttribute("LIST_PRODUCT", pr);
        return "shop";
    }

    @GetMapping("/assert/page")
    public String paginate(Model model, @RequestParam("ls") Optional<Integer> ls) {
        int currentPage = ls.orElse(0); // Sửa giá trị mặc định thành 0 nếu không có giá trị ls

        if (currentPage < 0) { // Kiểm tra nếu currentPage nhỏ hơn 0
            currentPage = 0; // Đặt currentPage thành 0 để tránh lỗi
        }

        Pageable pageable = PageRequest.of(currentPage, 16);
        Page<Product> page = productService.findAll(pageable);
        model.addAttribute("LIST_PRODUCT", page);

        int totalPages = page.getTotalPages();
        List<Integer> pageNumbers = new ArrayList<>();

        if (currentPage > 0) { // Kiểm tra nếu currentPage không phải trang đầu tiên
            pageNumbers.add(currentPage - 1);
        }
        pageNumbers.add(currentPage);

        if (currentPage < totalPages - 1) {
            pageNumbers.add(currentPage + 1);
        }
        if (currentPage < totalPages - 2) {
            pageNumbers.add(currentPage + 2);
        }
        model.addAttribute("PAGE_NUMBERS", pageNumbers);

        return "shop";
    }

    @GetMapping("assert/product-details")
    public String viewProductDetail() {

        return "product-details";
    }

}
