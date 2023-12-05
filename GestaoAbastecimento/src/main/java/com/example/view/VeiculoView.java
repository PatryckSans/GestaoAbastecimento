// package com.example.view;

// import com.example.controller.VeiculoController;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.GridPane;
// import javafx.stage.Stage;

// public class VeiculoView extends Application {

//     private VeiculoController veiculoController = new VeiculoController();

//     public static void main(String[] args) {
//         launch(args);
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         primaryStage.setTitle("Cadastro de Veículo");

//         GridPane grid = new GridPane();
//         grid.setPadding(new Insets(20, 20, 20, 20));
//         grid.setVgap(8);
//         grid.setHgap(10);

//         Label placaLabel = new Label("Placa:");
//         GridPane.setConstraints(placaLabel, 0, 0);

//         Label marcaLabel = new Label("Marca:");
//         GridPane.setConstraints(marcaLabel, 0, 1);

//         Label modeloLabel = new Label("Modelo:");
//         GridPane.setConstraints(modeloLabel, 0, 2);

//         TextField placaInput = new TextField();
//         GridPane.setConstraints(placaInput, 1, 0);

//         TextField marcaInput = new TextField();
//         GridPane.setConstraints(marcaInput, 1, 1);

//         TextField modeloInput = new TextField();
//         GridPane.setConstraints(modeloInput, 1, 2);

//         Button cadastrarButton = new Button("Cadastrar");
//         GridPane.setConstraints(cadastrarButton, 1, 3);
//         cadastrarButton.setOnAction(e -> cadastrarVeiculo(placaInput.getText(), marcaInput.getText(), modeloInput.getText()));

//         grid.getChildren().addAll(placaLabel, placaInput, marcaLabel, marcaInput, modeloLabel, modeloInput, cadastrarButton);

//         Scene scene = new Scene(grid, 300, 200);
//         primaryStage.setScene(scene);

//         primaryStage.show();
//     }

//     private void cadastrarVeiculo(String placa, String marca, String modelo) {
//         veiculoController.cadastrarVeiculo(placa, marca, modelo);
//     }
// }
