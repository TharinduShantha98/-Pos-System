package views.TM;

public class OrderManageCartTM {
    private String itemCode;
    private int orderQty;
    private double itemDiscount;
    private double totalCost;

    public OrderManageCartTM() {
    }

    public OrderManageCartTM(String itemCode, int orderQty, double itemDiscount, double totalCost) {
        this.setItemCode(itemCode);
        this.setOrderQty(orderQty);
        this.setItemDiscount(itemDiscount);
        this.setTotalCost(totalCost);
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
