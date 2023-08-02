package edu.poly.assignment.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.poly.assignment.entity.Account;
import edu.poly.assignment.entity.PasswordForm;
import edu.poly.assignment.entity.Product;
import edu.poly.assignment.service.AccountService;
import edu.poly.assignment.service.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    ProductService productService;
    @Autowired
    AccountService accountService;

    @GetMapping("/account/index")
    public String index(Model model) {
        List<Product> pr = productService.findAll();
        model.addAttribute("LIST_PRODUCT", pr);
        return "index";
    }

    @GetMapping("/account/my-account")
    public String account(Model model, HttpSession session) {
        Account loggedInAccount = (Account) session.getAttribute("user");
        if (loggedInAccount != null) {
            model.addAttribute("account", loggedInAccount);
            model.addAttribute("passwordForm", new PasswordForm());
            return "my-account";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/account/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/account/change-password")
    public String changePassword(@ModelAttribute("passwordForm") PasswordForm passwordForm, Model model) {
        // Kiểm tra tính hợp lệ của dữ liệu đầu vào
        if (passwordForm.getNewPassword().equals(passwordForm.getConfirmPassword())) {
            // Lấy thông tin người dùng đang đăng nhập
            Optional<Account> optionalAccount = accountService.findById(passwordForm.getUsername());
            Account loggedInAccount = optionalAccount.orElse(null);

            if (loggedInAccount != null && loggedInAccount.getPassword().equals(passwordForm.getCurrentPassword())) {

                // Thực hiện cập nhật mật khẩu mới
                loggedInAccount.setPassword(passwordForm.getNewPassword());
                accountService.save(loggedInAccount);
                model.addAttribute("successMessage", "Đổi mật khẩu thành công!");
            } else {
                model.addAttribute("errorMessage", "Mật khẩu hiện tại không đúng!");
            }
        } else {
            model.addAttribute("errorMessage", "Xác nhận mật khẩu mới không khớp!");
        }
        // Điều hướng trở lại trang my-account
        return "redirect:/account/my-account";
    }

    @PostMapping("/account/register")
    public String registerUser(@Validated @ModelAttribute("account") Account account, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        accountService.save(account);
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");

        model.addAttribute("account", new Account());
        return "redirect:/account/login";
    }
}
