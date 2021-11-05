package model;

public class ItemDTO {
        private String  itemCode;
        private String Description;
        private String PackSize;
        private double buyingPrice;
        private double unitPrice;
        private String ItemDiscount;
        private int qtyOnHand;

    public ItemDTO() {
    }




    public ItemDTO(String itemCode, String description, String packSize, double buyingPrice, double unitPrice, String itemDiscount, int qtyOnHand) {
        this.setItemCode(itemCode);
        setDescription(description);
        setPackSize(packSize);
        this.setBuyingPrice(buyingPrice);
        this.setUnitPrice(unitPrice);
        setItemDiscount(itemDiscount);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return PackSize;
    }

    public void setPackSize(String packSize) {
        PackSize = packSize;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getItemDiscount() {
        return ItemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        ItemDiscount = itemDiscount;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
