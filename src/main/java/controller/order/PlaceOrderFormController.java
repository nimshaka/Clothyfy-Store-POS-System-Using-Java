package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.gentsitem.AddGentsitemController;
import controller.kidsitem.AddKidsitemController;
import controller.ladiesitem.AddLadiesitemController;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    public Label lblNetTotal;
    public Label lblOrderId;
    public JFXComboBox <String> cmbLitemId;
    public JFXComboBox <String> cmbGitemId;
    public JFXComboBox <String> cmbKitemId;
    public JFXTextField txtStock;
    public JFXTextField txtPrice;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public TableColumn colTotal;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colItemId;

    public TableView tblAddToCart;
    public Label lblTime;
    public Label lblDate;
    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOrderId.setText(generateOrderID());
        loadDateAndTime();
        loadLadiesitemIds();
        loadGentsitemIds();
        loadKidsitemIds();

        cmbLitemId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                SearchLadiesitem((String) newVal);
            }
        });

       cmbGitemId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                searchGentsitem((String) newVal);
            }
        });

       cmbKitemId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                searchKidssitem((String) newVal);
            }
        });
    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow =f.format(date);

        lblDate.setText("Date :" +dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            LocalTime now = LocalTime.now();
            lblTime.setText("Time : " +now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    private void loadLadiesitemIds(){
        cmbLitemId.setItems(AddLadiesitemController.getInstance().getladiesitmeId());
    }
    private void loadGentsitemIds(){
        cmbGitemId.setItems(AddGentsitemController.getInstance().getgentsitmeId());
    }
    private void loadKidsitemIds(){
        cmbKitemId.setItems(AddKidsitemController.getInstance().getkidssitmeId());
    }

    private void SearchLadiesitem(String ladiesitemId){
        LadiesItem ladiesItem = AddLadiesitemController.getInstance().SearchLadiesitem(ladiesitemId);
        txtName.setText(ladiesItem.getName());
        txtPrice.setText(String.valueOf(ladiesItem.getPrice()));
        txtStock.setText(String.valueOf(ladiesItem.getQty()));
    }

    private void searchGentsitem(String gentsitemId){
        GentsItem gentsItem = AddGentsitemController.getInstance().searchGentsitem(gentsitemId);
        txtName.setText(gentsItem.getName());
        txtPrice.setText(String.valueOf(gentsItem.getPrice()));
        txtStock.setText(String.valueOf(gentsItem.getQty()));

    }

    private void searchKidssitem(String kidsitemId){
       KidsItem kidsItem = AddKidsitemController.getInstance().SearchKidsitem(kidsitemId);
        txtName.setText(kidsItem.getName());
        txtPrice.setText(String.valueOf(kidsItem.getPrice()));
        txtStock.setText(String.valueOf(kidsItem.getQty()));

    }

    public void btnPlaceOrderOnaction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String total = (lblNetTotal.getText());
        LocalDate orderDate = LocalDate.now();
        String itemId = String.valueOf(cmbLitemId.getValue());

        List<OrderDetail> orderDetails = new ArrayList<>();

        cartTMS.forEach(obj->{
            orderDetails.add(new OrderDetail(orderId,obj.getTotal(),obj.getItemId(),obj.getQty()));
        });

        Order order = new Order(orderId,total,itemId,orderDate, orderDetails);


        try {
            new PlaceOrderController().placeOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(order);
    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemId =  cmbLitemId.getValue();
        String itemId2 =  cmbGitemId.getValue();
        String itemId3 =  cmbKitemId.getValue();
        String name = txtName.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtPrice.getText());
        Double total = unitPrice*qty;

        if (Integer.parseInt(txtStock.getText())<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
        }else{
            cartTMS.add(new CartTM(itemId,itemId2,itemId3,name,qty,unitPrice,total));
            calcNetTotal();
        }

        tblAddToCart.setItems(cartTMS);

        cmbLitemId.setValue(null);
        cmbGitemId.setValue(null);
        cmbKitemId.setValue(null);
        txtName.setText(null);
        txtPrice.setText(null);
        txtStock.setText(null);
        txtQty.setText(null);

    }

    private void calcNetTotal(){
        Double total=0.0;

        for (CartTM cartTM: cartTMS){
            total+=cartTM.getTotal();
        }

        lblNetTotal.setText(total.toString()+"/=");

    }

    public void btnHistoryOnAction(ActionEvent actionEvent) {
    }

    private String generateOrderID() {
        int orderCounter = getNextEmployeeCounter();


        return String.format("P%03d",  orderCounter);
    }


    private int getNextEmployeeCounter() {
        int  orderCounter = 0;

        try {

            String SQL = "SELECT COUNT(*) FROM Orders";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            if (resultSet.next()) {
                orderCounter = resultSet.getInt(1);
            }

            orderCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  orderCounter;
    }


    public void setorderId(String orderId) {
       lblOrderId.setText(orderId);
    }



}
