package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListener;
import model.Netflix;

import java.util.ArrayList;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(javafx.scene.input.MouseEvent mouseEvent) {
        myListener.onClickListener(netflix);
    }

    private Netflix netflix;
    private MyListener myListener;


    public void setData(Netflix netflix, MyListener myListener) {
        this.netflix = netflix;
        this.myListener = myListener;
        nameLabel.setText(netflix.getTitle());
        Image image;

//        if(getClass().getResourceAsStream(netflix.getImageUrl()) == null){
//            image = new Image("/img/naruto.png");
//        }
//        else{
//            image = new Image(getClass().getResourceAsStream(netflix.getImageUrl()));
//        }

        if(netflix.getImageUrl() == null){
            int random = (int)(Math.random() * MarketController.imagesUrl.size());
            System.out.println(random);
            image = new Image(MarketController.imagesUrl.get(random));
        }
        else{
            image = new Image(netflix.getImageUrl());
        }


        //image = new Image(getClass().getResourceAsStream(netflix.getImageUrl()));
        //image = new Image( "/img/winx.png");
        //System.out.println(getClass().getResourceAsStream(netflix.getImageUrl()));
        //image = new Image("/img/naruto.png");

        //image = new Image("/img/naruto.png");
        img.setImage(image);

    }
}
