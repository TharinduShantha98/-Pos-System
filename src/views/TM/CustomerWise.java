package views.TM;

public class CustomerWise {
    private String cusId;
    private double itemProfit;
    private int itemQty;

    public CustomerWise() {
    }

    public CustomerWise(String cusId, double itemProfit, int itemQty) {
        this.setCusId(cusId);
        this.setItemProfit(itemProfit);
        this.setItemQty(itemQty);
    }


    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public double getItemProfit() {
        return itemProfit;
    }

    public void setItemProfit(double itemProfit) {
        this.itemProfit = itemProfit;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
}
