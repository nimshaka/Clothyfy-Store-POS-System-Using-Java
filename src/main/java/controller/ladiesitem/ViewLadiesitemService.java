package controller.ladiesitem;

import javafx.collections.ObservableList;
import model.Employee;
import model.GentsItem;
import model.LadiesItem;

public interface ViewLadiesitemService {
    boolean removeLadiesitem(String ladiesietemId);

    LadiesItem SearchLadiesitem(String ladiesietemId);
    ObservableList<LadiesItem> getAll();
    boolean updateLadiesitem(LadiesItem ladiesItem);

}
