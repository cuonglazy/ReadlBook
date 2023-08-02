package edu.poly.assignment.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.assignment.entity.Account;
import edu.poly.assignment.entity.Category;
import edu.poly.assignment.entity.Product;
import edu.poly.assignment.service.AccountService;
import edu.poly.assignment.service.CategoryService;
import edu.poly.assignment.service.ParamService;
import edu.poly.assignment.service.ProductService;
import edu.poly.assignment.service.SessionService;

import org.springframework.util.StringUtils;

@Controller
public class AdminController {
    @Autowired
    SessionService session;
    @Autowired
    ParamService paramService;
    @Autowired
    AccountService accountService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @RequestMapping("/admin/index")
    public String index() {
        return "admin/iframe-dark";
    }

    // quản lý tài khoản
    @GetMapping("/admin/profile")
    public String showProfile(Model model) {
        model.addAttribute("ACCOUNT", new Account());
        return "admin/pages/forms/customer-admin";
    }

    @PostMapping("/admin/SaveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute("ACCOUNT") Account ac, BindingResult result, Model model,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("ERROR_PHOTO", "Please select a photo");
            return "admin/pages/forms/customer-admin";
        }

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "uploads/";

            // Lưu hình ảnh vào thư mục uploads
            paramService.save(multipartFile, uploadDir);
            ac.setPhoto(fileName);
        }

        // Cập nhật thông tin người dùng
        accountService.save(ac);
        System.out.println(ac.getPhoto() + " - " + ac.getUsername());
        model.addAttribute("ACCOUNT", new Account());
        return "admin/pages/forms/customer-admin";
    }

    @GetMapping("/admin/view-profile-admin")
    public String viewAdmin(Model model) {
        List<Account> accounts = accountService.findAll();
        Collections.sort(accounts, Comparator.comparing(Account::getUsername));
        model.addAttribute("ACCOUNTS", accounts);
        return "admin/pages/tables/list-data-admin";
    }

    @GetMapping("/admin/profile/{username}")
    public String edit(@PathVariable("username") String username, Model model) {
        Optional<Account> account = accountService.findById(username);
        if (account.isPresent()) {
            model.addAttribute("ACCOUNT", account.get());
        } else {
            model.addAttribute("ACCOUNT", new Account());
        }
        return "admin/pages/forms/customer-admin";
    }

    @GetMapping(value = "/admin/view-profile-admin", params = "btnDel")
    public String delAccount(@RequestParam("username") String username, Model model) {
        accountService.deleteById(username);
        return "redirect:/admin/view-profile-admin";
    }

    // quản lý danh mục
    @GetMapping("/admin/categories")
    public String showCate(Model model) {
        model.addAttribute("CATEGORY", new Category());
        return "admin/pages/forms/categories";
    }

    @PostMapping("/admin/saveOrUpdateCate")
    public String saveOrUpdateCate(@Validated @ModelAttribute("CATEGORY") Category category, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Xử lý khi có lỗi validate
            return "admin/pages/forms/categories";
        }

        // Cập nhật thông tin danh mục
        categoryService.save(category);

        model.addAttribute("CATEGORY", new Category());
        return "admin/pages/forms/categories";
    }

    @GetMapping("/admin/view-list-categories")
    public String viewCate(Model model) {
        List<Category> categories = categoryService.findAll();
        Collections.sort(categories, Comparator.comparing(Category::getId));
        model.addAttribute("CATEGORIES", categories);
        return "admin/pages/tables/list-categories";
    }

    @GetMapping("/admin/categories/{id}")
    public String editCate(@PathVariable("id") String id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("CATEGORY", category.get());
        } else {
            model.addAttribute("CATEGORY", new Category());
        }
        return "admin/pages/forms/categories";
    }

    @GetMapping(value = "/admin/view-list-categories", params = "btnDel")
    public String delCate(@RequestParam("id") String id, Model model) {
        accountService.deleteById(id);
        return "redirect:/admin/view-list-categories";
    }

    /***********************************************
     ************** QUẢN LÝ SẢN PHẨM **************
     **********************************************/

    @GetMapping("/admin/product")
    public String showProduct(Model model) {
        model.addAttribute("PRODUCT", new Product());
        return "admin/pages/forms/product";
    }

    @PostMapping("/admin/ThemVaCapNhatSP")
    public String themVaCapNhapSP(@Validated @ModelAttribute("PRODUCT") Product pro, BindingResult result, Model model,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("ERROR_IMAGE", "Please select a photo");
            return "admin/pages/forms/product";
        }

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "uploads/";

            // Lưu hình ảnh vào thư mục uploads
            paramService.save(multipartFile, uploadDir);
            pro.setPhoto(fileName);
        }

        // Cập nhật thông tin người dùng
        productService.save(pro);
        System.out.println(pro.getPhoto() + " - " + pro.getMasach());
        model.addAttribute("PRODUCT", new Product());
        return "admin/pages/forms/product";
    }

    @GetMapping("/admin/view-list-product")
    public String viewProduct(Model model) {
        List<Product> products = productService.findAll();
        Collections.sort(products, Comparator.comparing(Product::getMasach));
        model.addAttribute("PRODUCTS", products);
        return "admin/pages/tables/list-product";
    }

    @GetMapping("/admin/product/{masach}")
    public String editPro(@PathVariable("masach") Integer masach, Model model) {
        Optional<Product> products = productService.findById(masach);
        if (products.isPresent()) {
            model.addAttribute("PRODUCT", products.get());
        } else {
            model.addAttribute("PRODUCT", new Product());
        }
        return "admin/pages/forms/product";
    }

    @GetMapping(value = "/admin/view-list-product", params = "btnDel")
    public String delPro(@RequestParam("masach") Integer masach, Model model) {
        productService.deleteById(masach);
        return "redirect:/admin/view-list-product";
    }

    /***********************************************
     ************** QUẢN LÝ ĐƠN HÀNG **************
     **********************************************/

    @GetMapping("/admin/orders")
    public String viewOrder() {
        return "admin/pages/forms/general";
    }

    @GetMapping("/admin/view-list-orders")
    public String showOrder() {
        return "admin/pages/tables/list-book";
    }

}
