package controller.kidsitem;

import javafx.collections.ObservableList;
import model.KidsItem;
import model.LadiesItem;

public interface AddKidsitemService {
    boolean addKidsitem(KidsItem kidsItem);

    ObservableList<KidsItem> getAll();
}
