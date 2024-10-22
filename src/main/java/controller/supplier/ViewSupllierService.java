package controller.supplier;

import javafx.collections.ObservableList;
import model.LadiesItem;
import model.Supplier;

public interface ViewSupllierService {
    boolean removeSupllier(String supplierId);

    Supplier SearchSupllier(String supplierId);
    ObservableList<Supplier> getAll();
    boolean updateSupllier(Supplier supplier);
}
