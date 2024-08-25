//package com.bwt.tradingmaster.service;
//
//import com.bwt.tradingmaster.utils.TradingUtils; // 导入自定义的工具类 TradingUtils
//import lombok.Getter; // 导入 Lombok 注解，自动生成 getter 方法
//import org.springframework.beans.factory.annotation.Value; // 导入 Spring 注解，从配置文件中注入值
//import org.springframework.stereotype.Service; // 导入 Spring 注解，将此类标记为服务组件
//
//import java.math.BigDecimal; // 导入 BigDecimal 类，用于高精度的数值运算
//
//@Service // 标识该类为 Spring 服务组件
//@Getter // 使用 Lombok 注解，自动生成所有字段的 getter 方法
//public class TradingFormatService {
//
//    private final int priceDigit; // 用于格式化价格时保留的小数位数
//    private final int quantityDigit; // 用于格式化数量时保留的小数位数
//
//    /**
//     * 通过构造函数注入，将配置文件中的值注入到 priceDigit 和 quantityDigit 字段中
//     *
//     * @param priceDigit 从配置文件注入的价格小数位数
//     * @param quantityDigit 从配置文件注入的数量小数位数
//     */
//    public TradingFormatService(
//            @Value("${trading.price-digit}") int priceDigit,
//            @Value("${trading.quantity-digit}") int quantityDigit) {
//        this.priceDigit = priceDigit;
//        this.quantityDigit = quantityDigit;
//    }
//
//    /**
//     * 将 BigDecimal 类型的价格值转换为指定小数位数的字符串。
//     *
//     * @param price 要转换的 BigDecimal 价格值
//     * @return 指定小数位数的字符串形式的价格
//     */
//    public String priceToString(BigDecimal price) {
//        return TradingUtils.d2Str(price, this.priceDigit); // 调用工具方法将价格转换为字符串
//    }
//
//    /**
//     * 将 BigDecimal 类型的数量值转换为指定小数位数的字符串。
//     *
//     * @param quantity 要转换的 BigDecimal 数量值
//     * @return 指定小数位数的字符串形式的数量
//     */
//    public String qtyToString(BigDecimal quantity) {
//        return TradingUtils.d2Str(quantity, this.quantityDigit); // 调用工具方法将数量转换为字符串
//    }
//}
