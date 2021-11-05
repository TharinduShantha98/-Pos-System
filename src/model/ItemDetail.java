package model;

public class ItemDetail {
    private String itemCode;
    private double unitPrice;
    private int qty;
    private double discount;
    private double total;

    public ItemDetail() {
    }

    public ItemDetail(String itemCode, double unitPrice, int qty, double discount, double total) {
        this.setItemCode(itemCode);
        this.setUnitPrice(unitPrice);
        this.setQty(qty);
        this.setDiscount(discount);
        this.setTotal(total);
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
