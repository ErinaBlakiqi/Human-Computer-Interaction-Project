package model.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProfileOrderDto {
    private final StringProperty date;
    private final StringProperty item;
    private final SimpleIntegerProperty price;


    public ProfileOrderDto(String date, String item, int price) {
        this.date = new SimpleStringProperty(date);
        this.item = new SimpleStringProperty(item);
        this.price = new SimpleIntegerProperty(price);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getItem() {
        return item.get();
    }

    public StringProperty itemProperty() {
        return item;
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }
}
