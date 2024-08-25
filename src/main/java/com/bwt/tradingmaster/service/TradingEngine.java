//package com.bwt.tradingmaster.service;
//
//import com.bwt.tradingmaster.utils.OrderQueue;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.RequiredArgsConstructor;
//import lombok.Synchronized;
//import java.math.BigDecimal;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * TradingEngine 类 - 交易引擎
// *
// * 该类用于处理交易对的撮合、订单管理和事件通知等功能。
// */
//@Getter // Lombok 自动生成 getter 方法
//@Setter // Lombok 自动生成 setter 方法
//@RequiredArgsConstructor // Lombok 自动生成包含 final 字段的构造函数
//public class TradingEngine {
//
//    private final String symbol; // 交易对符号
//    private final int priceDigit; // 价格小数位数
//    private final int quantityDigit; // 数量小数位数
//    private final BigDecimal miniTradeQty = BigDecimal.ONE.scaleByPowerOfTen(-quantityDigit); // 最小交易数量
//
//    private BigDecimal latestPrice; // 最新价格
//    private long latestPriceAt; // 最新价格的时间戳
//
//    private final OrderQueue askQueue = new OrderQueue(); // 卖单队列
//    private final OrderQueue bidQueue = new OrderQueue(); // 买单队列
//
//    private boolean pausePushNew = false; // 是否暂停推送新订单
//    private boolean pauseMatch = false; // 是否暂停撮合
//    private boolean triggerEvent = false; // 是否触发事件
//
//    private TradeResultListener onEventTrade; // 交易结果事件监听器
//
//    private final ReentrantLock lock = new ReentrantLock(); // 重入锁，用于线程安全
//    private final ExecutorService executor = Executors.newCachedThreadPool(); // 线程池，用于并发处理
//
//    /**
//     * 构造函数，初始化交易对和相关参数
//     *
//     * 使用 Lombok 的 @RequiredArgsConstructor 注解生成
//     */
//
//    /**
//     * 设置交易结果事件监听器
//     *
//     * @param onEventTrade 交易结果事件监听器
//     */
//    public void onEvent(TradeResultListener onEventTrade) {
//        this.onEventTrade = onEventTrade;
//    }
//
//    /**
//     * 推送新订单
//     *
//     * @param item 新订单
//     */
//    public void pushNewOrder(QueueItem item) {
//        handlerNewOrder(item);
//    }
//
//    /**
//     * 取消订单
//     *
//     * @param side     买卖方向
//     * @param orderId  订单ID
//     * @param reason   取消原因
//     */
//    public void cancelOrder(OrderSide side, String orderId, CancelType reason) {
//        if (side == OrderSide.SELL) {
//            askQueue.remove(orderId);
//        } else {
//            bidQueue.remove(orderId);
//        }
//        // 发送取消结果通知
//        notifyCancelResult(new CancelBody(orderId, reason));
//    }
//
//    /**
//     * 获取卖单队列长度
//     *
//     * @return 卖单队列长度
//     */
//    @Synchronized // 使用 Lombok 的 @Synchronized 来代替手动加锁
//    public int askLen() {
//        return askQueue.size();
//    }
//
//    /**
//     * 获取买单队列长度
//     *
//     * @return 买单队列长度
//     */
//    @Synchronized // 使用 Lombok 的 @Synchronized 来代替手动加锁
//    public int bidLen() {
//        return bidQueue.size();
//    }
//
//    /**
//     * 撮合订单
//     */
//    private void matching() {
//        while (true) {
//            if (!handlerLimitOrder()) {
//                try {
//                    Thread.sleep(60); // 60 毫秒
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            } else {
//                if (Debug) {
//                    try {
//                        Thread.sleep(1000); // 1 秒
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 处理新订单
//     *
//     * @param newOrder 新订单
//     */
//    @Synchronized
//    private void handlerNewOrder(QueueItem newOrder) {
//        if (pausePushNew) {
//            return;
//        }
//
//        if (newOrder.getOrderType() == OrderType.LIMIT) {
//            if (newOrder.getOrderSide() == OrderSide.SELL) {
//                askQueue.push(newOrder);
//            } else {
//                bidQueue.push(newOrder);
//            }
//        } else {
//            // 市价单处理
//            if (newOrder.getOrderSide() == OrderSide.SELL) {
//                doMarketSell(newOrder);
//            } else {
//                doMarketBuy(newOrder);
//            }
//        }
//    }
//
//    /**
//     * 处理限价订单撮合
//     *
//     * @return 是否成功撮合
//     */
//    @Synchronized
//    private boolean handlerLimitOrder() {
//        if (pauseMatch || askQueue.isEmpty() || bidQueue.isEmpty()) {
//            return false;
//        }
//
//        QueueItem askTop = askQueue.top();
//        QueueItem bidTop = bidQueue.top();
//
//        if (bidTop.getPrice().compareTo(askTop.getPrice()) >= 0) {
//            BigDecimal curTradeQty = bidTop.getQuantity().compareTo(askTop.getQuantity()) >= 0
//                    ? askTop.getQuantity()
//                    : bidTop.getQuantity();
//
//            askQueue.setQuantity(askTop, askTop.getQuantity().subtract(curTradeQty));
//            bidQueue.setQuantity(bidTop, bidTop.getQuantity().subtract(curTradeQty));
//
//            BigDecimal curTradePrice = askTop.getCreateTime() >= bidTop.getCreateTime()
//                    ? bidTop.getPrice()
//                    : askTop.getPrice();
//
//            sendTradeResultNotify(askTop, bidTop, curTradePrice, curTradeQty, System.nanoTime(), "");
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 处理市价买单
//     *
//     * @param item 市价订单项
//     */
//    private void doMarketBuy(QueueItem item) {
//        // 市价买单处理逻辑
//        // 完成后触发通知
//    }
//
//    /**
//     * 处理市价卖单
//     *
//     * @param item 市价订单项
//     */
//    private void doMarketSell(QueueItem item) {
//        // 市价卖单处理逻辑
//        // 完成后触发通知
//    }
//
//    /**
//     * 发送成交结果通知
//     *
//     * @param ask        卖单
//     * @param bid        买单
//     * @param price      成交价格
//     * @param tradeQty   成交数量
//     * @param tradeAt    成交时间戳
//     * @param last       标记市价订单是否已完成
//     */
//    private void sendTradeResultNotify(QueueItem ask, QueueItem bid, BigDecimal price, BigDecimal tradeQty, long tradeAt, String last) {
//        TradeResult tradelog = new TradeResult();
//        tradelog.setSymbol(this.symbol);
//        tradelog.setAskOrderId(ask.getUniqueId());
//        tradelog.setBidOrderId(bid.getUniqueId());
//
//        tradelog.setTradeBy(ask.getCreateTime() < bid.getCreateTime() ? TradeBy.BUYER : TradeBy.SELLER);
//        tradelog.setTradeQuantity(tradeQty);
//        tradelog.setTradePrice(price);
//        tradelog.setTradeTime(tradeAt); // 纳秒
//        tradelog.setLast(last); // 标记市价订单是否完成
//
//        if (tradeAt > this.latestPriceAt) {
//            this.latestPrice = price;
//            this.latestPriceAt = tradeAt;
//        }
//
//        if (onEventTrade != null) {
//            onEventTrade.onTrade(tradelog);
//        }
//    }
//
//    /**
//     * 清空所有队列
//     */
//    @Synchronized
//    public void cleanAll() {
//        askQueue.clean();
//        bidQueue.clean();
//    }
//
//    /**
//     * 处理订单深度
//     *
//     * @param queue 要处理的订单队列
//     */
//    private void depthTicker(OrderQueue queue) {
//        // 深度处理逻辑
//    }
//
//    /**
//     * 发送取消订单结果通知
//     *
//     * @param cancelBody 取消订单结果的内容
//     */
//    private void notifyCancelResult(CancelBody cancelBody) {
//        // 取消结果通知逻辑
//    }
//}
