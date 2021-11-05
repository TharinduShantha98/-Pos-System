package entity;

public class OrderDetail {
    private  String orderId;
    private String itemCode;
    private int orderQty;
    private double itemDiscount;
    private double cost;
    private double itemProfit;


    public OrderDetail(String orderId, String itemCode, int orderQty, double itemDiscount, double cost, double itemProfit) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.itemDiscount = itemDiscount;
        this.cost = cost;
        this.itemProfit = itemProfit;
    }

    public OrderDetail() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getItemProfit() {
        return itemProfit;
    }

    public void setItemProfit(double itemProfit) {
        this.itemProfit = itemProfit;
    }
}
