package model.filter;

import javafx.collections.ObservableList;

public abstract class Filter<T> {
    public abstract ObservableList<T> filter(ObservableList<T> items, String searchText);
}
