package controller.kidsitem;

import javafx.collections.ObservableList;
import model.GentsItem;
import model.KidsItem;

public interface ViewKidsitemService {
    boolean removeKidsitem(String kidsietemId);

    KidsItem SearchKidsitem(String kidsietemId);
    ObservableList<KidsItem> getAll();
    boolean updateKidsitem(KidsItem kidsItem);

}
