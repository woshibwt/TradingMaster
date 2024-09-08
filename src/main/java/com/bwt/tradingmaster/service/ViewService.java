package com.bwt.tradingmaster.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewService {

    public void runView() {
        // 模拟视图模块启动
        System.out.println("视图模块已启动");
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("message", "欢迎进入交易系统");
        return "dashboard"; // 返回 Thymeleaf 模板
    }
}
