package controller.gentsitem;

import javafx.collections.ObservableList;
import model.GentsItem;
import model.KidsItem;


public interface ViewGentsitemService {
    boolean removeGentsitem(String gentsietemId);

    GentsItem SearchGentesitem(String gentsietemId);
    ObservableList<GentsItem> getAll();
    boolean updateGentsitem(GentsItem gentsItem);



}
