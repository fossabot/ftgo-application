package net.chrisrichardson.ftgo.orderservice.api.web;

import java.util.List;

/**
 * 餐馆Id
 * 消费者Id
 * 点餐具体信息：
 * -- 1. 食品Id
 * -- 2. 食品数量
 */
public class CreateOrderRequest {

    private long restaurantId;
    private long consumerId;
    private List<LineItem> lineItems;

    public CreateOrderRequest(long consumerId, long restaurantId, List<LineItem> lineItems) {
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
        this.lineItems = lineItems;

    }

    private CreateOrderRequest() {
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(long consumerId) {
        this.consumerId = consumerId;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    /**
     * menuItemId：食品Id
     * quantity：食品数量
     */
    public static class LineItem {

        private String menuItemId;
        private int quantity;

        private LineItem() {
        }

        public LineItem(String menuItemId, int quantity) {
            this.menuItemId = menuItemId;

            this.quantity = quantity;
        }

        public String getMenuItemId() {
            return menuItemId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setMenuItemId(String menuItemId) {
            this.menuItemId = menuItemId;

        }
    }
}
