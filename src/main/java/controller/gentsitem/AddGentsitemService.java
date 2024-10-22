package controller.gentsitem;

import javafx.collections.ObservableList;
import model.GentsItem;
import model.LadiesItem;


public interface AddGentsitemService {
    boolean addGentssitem(GentsItem gentsItem);
    ObservableList<GentsItem> getAll();


}
