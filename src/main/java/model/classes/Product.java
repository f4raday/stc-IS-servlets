package model.classes;

public class Product {
    private long id;
    private String description;
    private int availableAmount;

    public Product(long id, String description, int availableAmount) {
        this.id = id;
        this.description = description;
        this.availableAmount = availableAmount;
    }

    public Product(String description, int availableAmount) {
        this(0, description, availableAmount);
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    /**
     * Изменяет количество доступного товара
     * @param additionalAmount Может быть отрицательным
     */
    public void addAmount(int additionalAmount)  {
        availableAmount += additionalAmount;
        if(availableAmount<0)
            availableAmount = 0;
    }
}
