package com.example.isometric2dmotor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Settings {

    public enum Mode {
        ERASER, ADDER
    }

    public static final int PREF_WIDTH = 350;

    protected static Bloc blocPresentation;

    protected Mode mode;
    protected Button eraserBtn;
    protected Button loadMapBtn;
    protected Downloader downloader;
    protected CheckBox recommandedRatioCheckBox;
    protected Slider ratioSlider;
    protected Label ratioSliderLabel;
    protected CheckBox recommandedLengthsCheckBox;
    protected Slider xLenghtSlider;
    protected Label xLengthSliderLabel;
    protected Slider yLengthSlider;
    protected Label yLengthSliderLabel;
    protected Slider rotationSlider;
    protected Label rotationSliderLabel;
    protected Slider baseHeightSlider;
    protected Slider hatHeightSlider;
    protected Label baseHeightSliderLabel;
    protected Label hatHeightSliderLabel;
    protected Button rotateBtn;
    protected Button adaptBtn;
    protected Pane blocPresentationLayout;
    protected Bloc.Type currentBlocType;
    protected int currentBz;
    protected int currentHz;

    protected Button elementaryBtn;
    protected Button dolphinBtn;
    protected Button sharkOppBtn;
    protected Button sharkAdjBtn;
    protected Button whaleBtn;
    protected Button domeBtn;
    protected Button textureModeBtn;
    protected Button exploreBtn;


    public Settings(Pane root) {

        HBox customizerLayout = new HBox();
        customizerLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox slidersLayout = new VBox();
        slidersLayout.setPadding(new Insets(5, 5, 5, 5));
        VBox hatSliderLayout = new VBox();
        VBox baseSliderLayout = new VBox();
        hatHeightSlider = new Slider(0, 30, 0);
        hatHeightSliderLabel = new Label("hz : " + hatHeightSlider.getValue());
        currentHz = (int)hatHeightSlider.getValue();
        baseHeightSlider = new Slider(0, 300, 0);
        baseHeightSliderLabel = new Label("bz : " + baseHeightSlider.getValue());
        currentBz = (int)baseHeightSlider.getValue();
        hatSliderLayout.getChildren().add(hatHeightSlider);
        hatSliderLayout.getChildren().add(hatHeightSliderLabel);
        baseSliderLayout.getChildren().add(baseHeightSlider);
        baseSliderLayout.getChildren().add(baseHeightSliderLabel);
        slidersLayout.getChildren().add(hatSliderLayout);
        slidersLayout.getChildren().add(baseSliderLayout);

        blocPresentationLayout = new Pane();
        blocPresentation = new Bloc(25, 20, (int)hatHeightSlider.getValue(), (int)baseHeightSlider.getValue(), Bloc.Type.ELEMENTARY, Bloc.Status.PRESENTATION, blocPresentationLayout);
        rotateBtn = new Button("Rotate");
        adaptBtn = new Button("Adapt heights");
        adaptBtn.setDisable(true);
        VBox buttonCustomizerLayout = new VBox(10);
        buttonCustomizerLayout.getChildren().add(rotateBtn);
        buttonCustomizerLayout.getChildren().add(adaptBtn);
        customizerLayout.getChildren().add(blocPresentationLayout);
        customizerLayout.getChildren().add(slidersLayout);
        customizerLayout.getChildren().add(buttonCustomizerLayout);

        currentBlocType = Bloc.Type.ELEMENTARY;

        VBox principalLayout = new VBox();
        principalLayout.setPrefWidth(PREF_WIDTH);

        HBox modeButtonLayout = new HBox();
        modeButtonLayout.setPadding(new Insets(10, 10, 10, 10));
        modeButtonLayout.setSpacing(20);

        recommandedLengthsCheckBox = new CheckBox("Recommanded lengths");
        recommandedLengthsCheckBox.setSelected(true);

        VBox notRecommendedLengthsLayout = new VBox();
        notRecommendedLengthsLayout.setPadding(new Insets(25, 25, 25, 25));
        recommandedRatioCheckBox = new CheckBox("Recommanded ratio");
        recommandedRatioCheckBox.setSelected(true);
        recommandedRatioCheckBox.setDisable(true);
        ratioSlider = new Slider(1.0, 3.0, 52.0/30.0);
        ratioSlider.setDisable(true);
        ratioSliderLabel = new Label("Ratio : " + new DecimalFormat("#.###").format(ratioSlider.getValue()));

        xLenghtSlider = new Slider(30, 80, 52);
        xLenghtSlider.setDisable(true);
        xLengthSliderLabel = new Label("X : " + new DecimalFormat("#.###").format(xLenghtSlider.getValue()));
        yLengthSlider = new Slider(xLenghtSlider.getMin()/ratioSlider.getMax(), xLenghtSlider.getMax()/ratioSlider.getMin(), 30);
        yLengthSlider.setDisable(true);
        yLengthSliderLabel = new Label("Y : " + new DecimalFormat("#.###").format(yLengthSlider.getValue()));
        notRecommendedLengthsLayout.getChildren().add(recommandedRatioCheckBox);
        notRecommendedLengthsLayout.getChildren().add(ratioSlider);
        notRecommendedLengthsLayout.getChildren().add(ratioSliderLabel);
        notRecommendedLengthsLayout.getChildren().add(xLenghtSlider);
        notRecommendedLengthsLayout.getChildren().add(xLengthSliderLabel);
        notRecommendedLengthsLayout.getChildren().add(yLengthSlider);
        notRecommendedLengthsLayout.getChildren().add(yLengthSliderLabel);

        rotationSlider = new Slider(0, 360, 0);
        rotationSliderLabel = new Label("Rotation : " + new DecimalFormat("#.##").format(rotationSlider.getValue()));
        notRecommendedLengthsLayout.getChildren().add(rotationSlider);
        notRecommendedLengthsLayout.getChildren().add(rotationSliderLabel);

        VBox ratioAndLengthsLayout = new VBox();
        ratioAndLengthsLayout.getChildren().add(recommandedLengthsCheckBox);
        ratioAndLengthsLayout.getChildren().add(notRecommendedLengthsLayout);

        mode = Mode.ADDER;
        eraserBtn = new Button("Eraser");


        elementaryBtn = new Button();
        ImageView elementaryImageView = new ImageView(new Image("file:C:/Users/joach/IdeaProjects/Isometric2DMotor/src/main/resources/img/elementary.jpg"));
        elementaryBtn.setGraphic(elementaryImageView);
        elementaryBtn.setFocusTraversable(true);

        dolphinBtn = new Button();
        ImageView dolphinImageView = new ImageView(new Image("file:C:/Users/joach/IdeaProjects/Isometric2DMotor/src/main/resources/img/dolphin.jpg"));
        dolphinBtn.setGraphic(dolphinImageView);

        sharkOppBtn = new Button();
        ImageView sharkOppImageView = new ImageView(new Image("file:C:/Users/joach/IdeaProjects/Isometric2DMotor/src/main/resources/img/shark_opp.jpg"));
        sharkOppBtn.setGraphic(sharkOppImageView);

        sharkAdjBtn = new Button();
        ImageView sharkAdjImageView = new ImageView(new Image("file:C:/Users/joach/IdeaProjects/Isometric2DMotor/src/main/resources/img/shark_adj.jpg"));
        sharkAdjBtn.setGraphic(sharkAdjImageView);

        whaleBtn = new Button();
        ImageView whaleImageView = new ImageView(new Image("file:C:/Users/joach/IdeaProjects/Isometric2DMotor/src/main/resources/img/whale.jpg"));
        whaleBtn.setGraphic(whaleImageView);

        domeBtn = new Button();
        ImageView domeImageView = new ImageView(new Image("file:C:/Users/joach/IdeaProjects/Isometric2DMotor/src/main/resources/img/dome.jpg"));
        domeBtn.setGraphic(domeImageView);


        VBox columnBlocButtonLayout1 = new VBox(5);
        columnBlocButtonLayout1.setPadding(new Insets(0, 10, 0, 10));
        columnBlocButtonLayout1.getChildren().add(elementaryBtn);
        columnBlocButtonLayout1.getChildren().add(dolphinBtn);
        columnBlocButtonLayout1.getChildren().add(sharkOppBtn);

        VBox columnBlocButtonLayout2 = new VBox(5);
        columnBlocButtonLayout2.setPadding(new Insets(0, 10, 0, 10));
        columnBlocButtonLayout2.getChildren().add(sharkAdjBtn);
        columnBlocButtonLayout2.getChildren().add(whaleBtn);
        columnBlocButtonLayout2.getChildren().add(domeBtn);

        HBox rowColumnBlocButtonLayout = new HBox();
        rowColumnBlocButtonLayout.setPadding(new Insets(10, 10, 10, 10));
        rowColumnBlocButtonLayout.getChildren().add(columnBlocButtonLayout1);
        rowColumnBlocButtonLayout.getChildren().add(columnBlocButtonLayout2);

        loadMapBtn = new Button("Load map");
        textureModeBtn = new Button("Texture mode");
        exploreBtn = new Button("Explore");
        principalLayout.getChildren().add(ratioAndLengthsLayout);
        principalLayout.getChildren().add(modeButtonLayout);
        principalLayout.getChildren().add(customizerLayout);
        principalLayout.getChildren().add(rowColumnBlocButtonLayout);
        principalLayout.getChildren().add(loadMapBtn);
        downloader = new Downloader(principalLayout);
        principalLayout.getChildren().add(textureModeBtn);
        principalLayout.getChildren().add(exploreBtn);
        root.getChildren().add(principalLayout);

        /* SLIDERS */
        xLenghtSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xLengthSliderLabel.setText("X : " + new DecimalFormat("#.###").format(newValue));
                yLengthSlider.setValue((double)newValue/ratioSlider.getValue());
            }
        });
        yLengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                yLengthSliderLabel.setText("Y : " + new DecimalFormat("#.###").format(newValue));
                xLenghtSlider.setValue((double)newValue*ratioSlider.getValue());

                Map.changeDimensions(xLenghtSlider.getValue(), yLengthSlider.getValue());
            }
        });
        ratioSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ratioSliderLabel.setText("Ratio : " + new DecimalFormat("#.###").format(newValue));
                yLengthSlider.setValue(xLenghtSlider.getValue()/(double)newValue);

            }
        });
        hatHeightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hatHeightSliderLabel.setText("hz : " + new DecimalFormat("#.###").format(newValue));
                //currentHz = newValue.intValue();
                blocPresentation.setHz(newValue.intValue());
                //Bloc.setHz(newValue.intValue());

                blocPresentationLayout.getChildren().clear();
                blocPresentation.attributeFaces(25, 20);
                blocPresentation.draw(blocPresentationLayout);
            }
        });
        baseHeightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                baseHeightSliderLabel.setText("bz : " + new DecimalFormat("#.###").format(newValue));
                blocPresentation.setBz(newValue.intValue());
                blocPresentationLayout.getChildren().clear();
                blocPresentation.attributeFaces(25, 20);
                blocPresentation.draw(blocPresentationLayout);
            }
        });

        /* CHECK BOXES */
        recommandedLengthsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                xLenghtSlider.setDisable(t1);
                recommandedRatioCheckBox.setDisable(t1);
                if(t1) {
                    recommandedRatioCheckBox.setSelected(true);
                }
                xLenghtSlider.setValue(52.0);
                yLengthSlider.setValue(30.0);
            }
        });
        recommandedRatioCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                ratioSlider.setValue(52.0/30.0);
                ratioSlider.setDisable(t1);
            }
        });

        /* BUTTONS */
        eraserBtn.setOnAction( (event)-> {
            mode = Mode.ERASER;
        });
        rotateBtn.setOnAction( (event-> { //FIX ME : PARENTHESIS
            if(blocPresentation.getType() != null) {
                if(blocPresentation.getType() == Bloc.Type.DOLPHIN_LB) {
                    blocPresentation.setType(Bloc.Type.DOLPHIN_LT);
                }
                else if(blocPresentation.getType() == Bloc.Type.DOLPHIN_LT) {
                    blocPresentation.setType(Bloc.Type.DOLPHIN_RT);
                }
                else if(blocPresentation.getType() == Bloc.Type.DOLPHIN_RT) {
                    blocPresentation.setType(Bloc.Type.DOLPHIN_RB);
                }
                else if(blocPresentation.getType() == Bloc.Type.DOLPHIN_RB) {
                    blocPresentation.setType(Bloc.Type.DOLPHIN_LB);
                }

                else if(blocPresentation.getType() == Bloc.Type.SHARK_OPP_Y) {
                    blocPresentation.setType(Bloc.Type.SHARK_OPP_X);
                }
                else if(blocPresentation.getType() == Bloc.Type.SHARK_OPP_X) {
                    blocPresentation.setType(Bloc.Type.SHARK_OPP_Y);
                }

                else if(blocPresentation.getType() == Bloc.Type.SHARK_ADJ_RIGHT) {
                    blocPresentation.setType(Bloc.Type.SHARK_ADJ_BOTTOM);
                }
                else if(blocPresentation.getType() == Bloc.Type.SHARK_ADJ_BOTTOM) {
                    blocPresentation.setType(Bloc.Type.SHARK_ADJ_LEFT);
                }
                else if(blocPresentation.getType() == Bloc.Type.SHARK_ADJ_LEFT) {
                    blocPresentation.setType(Bloc.Type.SHARK_ADJ_TOP);
                }
                else if(blocPresentation.getType() == Bloc.Type.SHARK_ADJ_TOP) {
                    blocPresentation.setType(Bloc.Type.SHARK_ADJ_RIGHT);
                }

                else if(blocPresentation.getType() == Bloc.Type.WHALE_RB) {
                    blocPresentation.setType(Bloc.Type.WHALE_LB);
                }
                else if(blocPresentation.getType() == Bloc.Type.WHALE_LB) {
                    blocPresentation.setType(Bloc.Type.WHALE_LT);
                }
                else if(blocPresentation.getType() == Bloc.Type.WHALE_LT) {
                    blocPresentation.setType(Bloc.Type.WHALE_RT);
                }
                else if(blocPresentation.getType() == Bloc.Type.WHALE_RT) {
                    blocPresentation.setType(Bloc.Type.WHALE_RB);
                }

                blocPresentationLayout.getChildren().clear();
                blocPresentation.attributeFaces(25, 20);
                blocPresentation.draw(blocPresentationLayout);
            }
        }));
        adaptBtn.setOnAction( (event)-> {
            Map.redraw();
        });
        loadMapBtn.setOnAction( (event)-> {
            Stage stage = new LoadMapWindow();
            stage.show();
        });

        elementaryBtn.setOnAction( (event)-> { /* CLASSE AVEC INDENTIFIANT ET UN SEUL SET ON POUR CHAQUE ? */
            blocPresentation.setType(Bloc.Type.ELEMENTARY);
            blocPresentationLayout.getChildren().clear();
            blocPresentation.attributeFaces(25, 20);
            blocPresentation.draw(blocPresentationLayout);
        });
        dolphinBtn.setOnAction( (event)-> {
            currentBlocType = Bloc.Type.DOLPHIN_LB;
            blocPresentation.setType(Bloc.Type.DOLPHIN_LB);

            blocPresentationLayout.getChildren().clear();
            blocPresentation.attributeFaces(25, 20);
            blocPresentation.draw(blocPresentationLayout);
        });
        sharkOppBtn.setOnAction( (event)-> {
            currentBlocType = Bloc.Type.SHARK_OPP_Y;
            blocPresentation.setType(Bloc.Type.SHARK_OPP_Y);

            blocPresentationLayout.getChildren().clear();
            blocPresentation.attributeFaces(25, 20);
            blocPresentation.draw(blocPresentationLayout);
        });
        sharkAdjBtn.setOnAction( (event)-> {
            currentBlocType = Bloc.Type.SHARK_ADJ_RIGHT;
            blocPresentation.setType(Bloc.Type.SHARK_ADJ_RIGHT);

            blocPresentationLayout.getChildren().clear();
            blocPresentation.attributeFaces(25, 20);
            blocPresentation.draw(blocPresentationLayout);
        });
        whaleBtn.setOnAction( (event)-> {
            currentBlocType = Bloc.Type.WHALE_RB;
            blocPresentation.setType(Bloc.Type.WHALE_RB);

            blocPresentationLayout.getChildren().clear();
            blocPresentation.attributeFaces(25, 20);
            blocPresentation.draw(blocPresentationLayout);
        });
        domeBtn.setOnAction( (event)-> {
            currentBlocType = Bloc.Type.DOME;
            blocPresentation.setType(Bloc.Type.DOME);

            blocPresentationLayout.getChildren().clear();
            blocPresentation.attributeFaces(25, 20);
            blocPresentation.draw(blocPresentationLayout);
        });
        textureModeBtn.setOnAction( (event) -> {

        });
        exploreBtn.setOnAction( (event) -> {
        });
    }

    public static Bloc getBlocPresentation() { return blocPresentation; }
}
