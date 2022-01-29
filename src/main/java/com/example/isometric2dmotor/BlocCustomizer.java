package com.example.isometric2dmotor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlocCustomizer {

    protected Rectangle area;
    protected Bloc bloc;

    protected Slider baseHeightSlider;
    protected Slider hatHeightSlider;
    protected Label baseHeightSliderLabel;
    protected Label hatHeightSliderLabel;

    public BlocCustomizer(Pane root) {

        HBox principalLayout = new HBox();
        principalLayout.setPadding(new Insets(10, 10, 10, 10));

        VBox slidersLayout = new VBox();
        slidersLayout.setPadding(new Insets(5, 5, 5, 5));
        VBox hatSliderLayout = new VBox();
        VBox baseSliderLayout = new VBox();

        hatHeightSlider = new Slider(0, 30, 0);
        hatHeightSliderLabel = new Label("" + hatHeightSlider.getValue());
        baseHeightSlider = new Slider(0, 30, 0);
        baseHeightSliderLabel = new Label("" + baseHeightSlider.getValue());

        hatSliderLayout.getChildren().add(hatHeightSlider);
        hatSliderLayout.getChildren().add(hatHeightSliderLabel);

        baseSliderLayout.getChildren().add(baseHeightSlider);
        baseSliderLayout.getChildren().add(baseHeightSliderLabel);

        slidersLayout.getChildren().add(hatSliderLayout);
        slidersLayout.getChildren().add(baseSliderLayout);

        area = new Rectangle(0, 0, 100, 80);
        area.setFill(Color.WHITE);
        principalLayout.getChildren().add(area);
        principalLayout.getChildren().add(slidersLayout);

        root.getChildren().add(principalLayout);

        hatHeightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hatHeightSliderLabel.setText("hz : " + newValue);
            }
        });
        baseHeightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                baseHeightSliderLabel.setText("bz : " + newValue);
            }
        });
    }

}
