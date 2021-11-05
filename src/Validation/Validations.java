package Validation;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Validations {
    public  static  Object validationStep(LinkedHashMap<TextField, Pattern> map, JFXButton button){
        button.setDisable(true);
        for (TextField textFiledKey: map.keySet()
             ) {
            Pattern patternValues = map.get(textFiledKey);
            if(!patternValues.matcher(textFiledKey.getText()).matches()){
                if(!textFiledKey.getText().isEmpty()){
                    Label l1  = (Label) ((Pane) textFiledKey.getParent()).getChildren().get(1);
                    l1.setText("not Success");
                    l1.setTextFill(Color.web("#e74c3c"));
                }
                return  textFiledKey;
            }
            Label l1  = (Label) ((Pane) textFiledKey.getParent()).getChildren().get(1);
            l1.setText("Success");
            l1.setTextFill(Color.web("#10ac84"));
        }
        button.setDisable(false);
        return true;


    }



}
