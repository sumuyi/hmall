package com.hmall.common.constants;

public class RabbitMqConstants {
    public static final String RABBIT_MQ_HOST = "rabbitmq.host";

    /*清除购物车*/
    public static final String EXCHANGE_DIRECT_CART = "hmall.direct.cart";
    public static final String QUEUE_CART_CLEAR = "cart.clear.queue";
    public static final String ROUTING_KEY_CART_CLEAR = "cart.clear";
}
