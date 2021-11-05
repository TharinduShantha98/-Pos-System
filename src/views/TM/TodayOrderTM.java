package views.TM;

public class TodayOrderTM {
    private String itemCode;
    private double totalDiscount;
    private double TotalQty;
    private double TotalProfit;

    public TodayOrderTM() {
    }

    public TodayOrderTM(String itemCode, double totalDiscount, double totalQty, double totalProfit) {
        this.setItemCode(itemCode);
        this.setTotalDiscount(totalDiscount);
        setTotalQty(totalQty);
        setTotalProfit(totalProfit);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalQty() {
        return TotalQty;
    }

    public void setTotalQty(double totalQty) {
        TotalQty = totalQty;
    }

    public double getTotalProfit() {
        return TotalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        TotalProfit = totalProfit;
    }
}
