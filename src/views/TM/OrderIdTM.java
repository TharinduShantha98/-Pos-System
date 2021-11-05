package views.TM;

public class OrderIdTM {
    private String orderId;

    public OrderIdTM() {
    }

    public OrderIdTM(String orderId) {
        this.setOrderId(orderId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
