//package com.bwt.tradingmaster.utils;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
///**
// * TradingUtils 工具类
// *
// * 这个类包含了与交易相关的通用工具方法，
// * 可以在项目的任何部分调用这些方法。
// */
//public class TradingUtils {
//
//    /**
//     * 将 BigDecimal 值转换为指定小数位数的字符串。
//     *
//     * @param value 需要转换的 BigDecimal 值
//     * @param scale 保留的小数位数
//     * @return 转换后的小数位数字符串
//     */
//    public static String d2Str(BigDecimal value, int scale) {
//        // 将 BigDecimal 值截断为指定的小数位数，并返回字符串形式
//        return value.setScale(scale, RoundingMode.HALF_UP).toPlainString();
//    }
//
//    // 你可以在这里添加其他与交易相关的工具方法
//
//}
