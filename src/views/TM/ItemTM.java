package views.TM;

public class ItemTM {
    private String itemCode;
    private String description;
    private String packSize;
    private String buyIngPrice;
    private String SaleUnitPrice;
    private String discount;
    private String qtyOnHand;

    public ItemTM() {
    }

    public ItemTM(String itemCode, String description, String packSize, String buyIngPrice, String saleUnitPrice, String discount, String qtyOnHand) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPackSize(packSize);
        this.setBuyIngPrice(buyIngPrice);
        setSaleUnitPrice(saleUnitPrice);
        this.setDiscount(discount);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public String getBuyIngPrice() {
        return buyIngPrice;
    }

    public void setBuyIngPrice(String buyIngPrice) {
        this.buyIngPrice = buyIngPrice;
    }

    public String getSaleUnitPrice() {
        return SaleUnitPrice;
    }

    public void setSaleUnitPrice(String saleUnitPrice) {
        SaleUnitPrice = saleUnitPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
