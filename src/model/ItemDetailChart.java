package model;

public class ItemDetailChart {
    private String itemCode;
    private int totalQty;

    public ItemDetailChart() {
    }

    public ItemDetailChart(String itemCode, int totalQty) {
        this.setItemCode(itemCode);
        this.setTotalQty(totalQty);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }
}
