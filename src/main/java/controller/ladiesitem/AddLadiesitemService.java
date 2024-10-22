package controller.ladiesitem;

import javafx.collections.ObservableList;
import model.Employee;
import model.LadiesItem;

public interface AddLadiesitemService {
    boolean addLadiesitem(LadiesItem ladiesItem);
    ObservableList<LadiesItem> getAll();
}
