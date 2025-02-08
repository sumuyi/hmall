package com.hmall.trade.listener;

import com.hmall.api.client.CartClient;
import com.hmall.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Set;
import static com.hmall.common.constants.RabbitMqConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClearCartsListener {
    private final CartClient cartClient;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QUEUE_CART_CLEAR, durable = "true"),
            exchange = @Exchange(name = EXCHANGE_DIRECT_CART),
            key = ROUTING_KEY_CART_CLEAR
    ))
    public void listenClearCart(Set<Long> itemIds, Message message) {
        Long userId = message.getMessageProperties().getHeader("user-info");
        log.error("监听到清除用户【{}】的购物车消息，itemIds={}", userId, itemIds);
        UserContext.setUser(userId);
        cartClient.removeByItemIds(itemIds);
    }
}
