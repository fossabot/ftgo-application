package net.chrisrichardson.ftgo.orderservice.web;

/**
 * 这里通过
 *
 * @see net.chrisrichardson.ftgo.orderservice.api.web.CreateOrderRequest.LineItem
 * 做了一个转换
 */
public class MenuItemIdAndQuantity {

    private String menuItemId;
    private int quantity;

    public String getMenuItemId() {
        return menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public MenuItemIdAndQuantity(String menuItemId, int quantity) {

        this.menuItemId = menuItemId;
        this.quantity = quantity;

    }
}
