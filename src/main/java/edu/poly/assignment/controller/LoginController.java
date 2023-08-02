package edu.poly.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.assignment.entity.Account;
import edu.poly.assignment.entity.LoginDTO;
import edu.poly.assignment.service.AccountService;
import edu.poly.assignment.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO(null, null));
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @Validated @ModelAttribute("loginDTO") LoginDTO loginDTO,
            BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            Account account = accountService.getOne(loginDTO.getUsername());

            if (account != null && account.getPassword().equals(loginDTO.getPassword())) {
                sessionService.setAttribute("user", account);
                String uri = sessionService.getAttribute("security-uri");
                if (uri != null && !uri.isEmpty()) {
                    if (uri.contains("/admin/")) {
                        return "redirect:/admin/index";
                    } else if (uri.contains("/account/")) {
                        return "redirect:/account/index";
                    }
                }
                return "index";
            } else {
                model.addAttribute("error_loginPass", "Mật khẩu không đúng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_loginUsername", "Tài khoản không tồn tại!");
        }

        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // Xử lý đăng xuất
        session.invalidate();
        return "redirect:/login"; // Điều hướng đến trang đăng nhập
    }
}
