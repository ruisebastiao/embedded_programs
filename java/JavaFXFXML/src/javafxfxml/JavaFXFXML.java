/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxfxml;


import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
 

/**
 *
 * @author rui
 */
public class JavaFXFXML extends Application {
      double anchorX, anchorY, anchorAngle;
 
    private PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        perspectiveCamera.setTranslateZ(-1000);
        scene.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root=FXMLLoader.load(getClass().getResource("mainFXML.fxml"));	
        Scene scene = new Scene(root);	
        stage.setTitle("Teste Title");	   
        stage.setScene(scene);	
        //stage.show();
        
        //primaryStage.setTitle("SphereAndBox");
 
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.ORANGE);
        redMaterial.setDiffuseColor(Color.RED);
 
        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.LIGHTBLUE);
 
        final Box red = new Box(400, 400, 400);
        red.setMaterial(redMaterial);
 
        final Sphere blue = new Sphere(200);
        blue.setMaterial(blueMaterial);
 
        blue.setTranslateX(250);
        blue.setTranslateY(250);
        blue.setTranslateZ(50);
        red.setTranslateX(250);
        red.setTranslateY(250);
        red.setTranslateZ(450);
 
        final Group parent = new Group(red, blue);
        parent.setTranslateZ(500);
        parent.setRotationAxis(Rotate.Y_AXIS);
 
 
        final Group root3D = new Group(parent);
 
       // final Scene scene = new Scene(root, 800, 480, true);
 
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngle = parent.getRotate();
            }
        });
 
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                parent.setRotate(anchorAngle + anchorX -  event.getSceneX());
            }
        });
 
        PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
        pointLight.setTranslateX(15);
        pointLight.setTranslateY(-10);
        pointLight.setTranslateZ(-100);
 
        root3D.getChildren().add(pointLight);
 
        RotateTransition rt = new RotateTransition(Duration.millis(2000), root);
        rt.setAxis(Rotate.Y_AXIS);
        rt.setByAngle(180);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAutoReverse(true);
 
        rt.play();
     
        addCamera(scene);
       // primaryStage.setScene(scene);
        stage.show();
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
