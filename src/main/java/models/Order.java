package models;

public class Order {
    Integer id;
    Integer petId;
    Integer quantity;
    String shipDate;
    String status;
    boolean complete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Order {" +
                "id = " + id +
                ", petId = " + petId +
                ", quantity = " + quantity +
                ", shipDate = " + shipDate +
                ", status = '" + status + '\'' +
                ", complete = " + complete +
                '}';
    }
}
