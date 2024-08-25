//package com.bwt.tradingmaster.utils;
//
//import java.util.PriorityQueue;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * OrderQueue 类 - 用于管理订单队列
// */
//public class OrderQueue {
//
//    private final PriorityQueue<QueueItem> pq; // 使用 Java 的 PriorityQueue 管理订单
//    private final Map<String, QueueItem> m; // 用于快速查找订单的映射
//
//    private OnEventUpdateListener onEventUpdate;
//    private OnEventRemoveListener onEventRemove;
//
//    // 构造函数，初始化优先队列和映射
//    public OrderQueue() {
//        this.pq = new PriorityQueue<>();
//        this.m = new HashMap<>();
//    }
//
//    // 推入新订单
//    public boolean push(QueueItem item) {
//        String id = item.getUniqueId();
//        if (m.containsKey(id)) {
//            return true;
//        }
//        pq.offer(item);
//        m.put(id, item);
//        if (onEventUpdate != null) {
//            onEventUpdate.onUpdate(item);
//        }
//        return false;
//    }
//
//    // 获取队列长度
//    public int len() {
//        return pq.size();
//    }
//
//    // 获取队列顶部的订单
//    public QueueItem top() {
//        return pq.peek();
//    }
//
//    // 根据唯一ID移除订单
//    public QueueItem remove(String uniqId) {
//        QueueItem item = m.remove(uniqId);
//        if (item != null) {
//            pq.remove(item);
//            if (onEventRemove != null) {
//                onEventRemove.onRemove(item);
//            }
//        }
//        return item;
//    }
//
//    // 设置订单的数量
//    public QueueItem setQuantity(QueueItem obj, BigDecimal qty) {
//        obj.setQuantity(qty);
//        if (onEventUpdate != null) {
//            onEventUpdate.onUpdate(obj);
//        }
//        return obj;
//    }
//
//    // 清空队列
//    public void clean() {
//        pq.clear();
//        m.clear();
//    }
//
//    // 事件监听器接口，用于处理订单更新和移除事件
//    public interface OnEventUpdateListener {
//        void onUpdate(QueueItem item);
//    }
//
//    public interface OnEventRemoveListener {
//        void onRemove(QueueItem item);
//    }
//
//    // 设置事件监听器
//    public void setOnEventUpdateListener(OnEventUpdateListener listener) {
//        this.onEventUpdate = listener;
//    }
//
//    public void setOnEventRemoveListener(OnEventRemoveListener listener) {
//        this.onEventRemove = listener;
//    }
//}
