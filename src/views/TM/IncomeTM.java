package views.TM;

public class IncomeTM {
    private String itemCode;
    private int saleItemQty;
    private double itemProfit;


    public IncomeTM() {
    }

    public IncomeTM(String itemCode, int saleItemQty, double itemProfit) {
        this.setItemCode(itemCode);
        this.setSaleItemQty(saleItemQty);
        this.setItemProfit(itemProfit);
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getSaleItemQty() {
        return saleItemQty;
    }

    public void setSaleItemQty(int saleItemQty) {
        this.saleItemQty = saleItemQty;
    }

    public double getItemProfit() {
        return itemProfit;
    }

    public void setItemProfit(double itemProfit) {
        this.itemProfit = itemProfit;
    }
}
