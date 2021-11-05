package views.TM;

public class MovableTM {

    private String itemCode;
    private int  orderCount;
    private int MovableQty;

    public MovableTM() {
    }

    public MovableTM(String itemCode, int orderCount, int movableQty) {
        this.setItemCode(itemCode);
        this.setOrderCount(orderCount);
        setMovableQty(movableQty);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getMovableQty() {
        return MovableQty;
    }

    public void setMovableQty(int movableQty) {
        MovableQty = movableQty;
    }
}
