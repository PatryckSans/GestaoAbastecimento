package com.example.view;

import com.example.repository.AbastecimentoRepository;
import com.example.repository.PostoRepository;
import com.example.repository.VeiculoRepository;
import com.example.model.Abastecimento;
import com.example.model.Posto;
import com.example.model.Veiculo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class CadastroView extends Application {

    private VeiculoRepository veiculoRepository = new VeiculoRepository();
    private PostoRepository postoRepository = new PostoRepository();
    private AbastecimentoRepository abastecimentoRepository = new AbastecimentoRepository();

    private TextArea resultadoTextArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Veículos, Postos e Abastecimentos");

        TabPane tabPane = new TabPane();

        Tab veiculoTab = new Tab("Veículo");
        veiculoTab.setContent(createVeiculoTabContent());
        tabPane.getTabs().add(veiculoTab);

        Tab postoTab = new Tab("Posto");
        postoTab.setContent(createPostoTabContent());
        tabPane.getTabs().add(postoTab);

        Tab abastecimentoTab = new Tab("Abastecimento");
        abastecimentoTab.setContent(createAbastecimentoTabContent());
        tabPane.getTabs().add(abastecimentoTab);

        Tab relatoriosTab = new Tab("Relatórios");
        relatoriosTab.setContent(createRelatoriosTabContent());
        tabPane.getTabs().add(relatoriosTab);

        VBox layout = new VBox(resultadoTextArea, tabPane);
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createVeiculoTabContent() {
        GridPane gridPane = createCommonGridPane();

        Label placaLabel = new Label("Placa:");
        TextField placaTextField = new TextField();

        Label marcaLabel = new Label("Marca:");
        TextField marcaTextField = new TextField();

        Label modeloLabel = new Label("Modelo:");
        TextField modeloTextField = new TextField();

        gridPane.addRow(1, placaLabel, placaTextField);
        gridPane.addRow(2, marcaLabel, marcaTextField);
        gridPane.addRow(3, modeloLabel, modeloTextField);

        Button cadastrarVeiculoButton = new Button("Cadastrar Veículo");
        cadastrarVeiculoButton.setOnAction(event -> {
            Veiculo veiculo = new Veiculo(placaTextField.getText(), marcaTextField.getText(), modeloTextField.getText());
            veiculoRepository.cadastrarVeiculo(veiculo);
            System.out.println("Veículo cadastrado com sucesso!");
            clearFields(placaTextField, marcaTextField, modeloTextField);
        });

        gridPane.add(cadastrarVeiculoButton, 1, 4);

        return gridPane;
    }

    private void clearFields(TextField placaTextField, TextField marcaTextField, TextField modeloTextField) {
    }

    private GridPane createPostoTabContent() {
        GridPane gridPane = createCommonGridPane();

        Label nomeLabel = new Label("Nome:");
        TextField nomeTextField = new TextField();

        Label localizacaoLabel = new Label("Localização:");
        TextField localizacaoTextField = new TextField();

        gridPane.addRow(1, nomeLabel, nomeTextField);
        gridPane.addRow(2, localizacaoLabel, localizacaoTextField);

        Button cadastrarPostoButton = new Button("Cadastrar Posto");
        cadastrarPostoButton.setOnAction(event -> {
            Posto posto = new Posto(nomeTextField.getText(), localizacaoTextField.getText());
            postoRepository.cadastrarPosto(posto);
            System.out.println("Posto cadastrado com sucesso!");
            clearFields(nomeTextField, localizacaoTextField);
        });

        gridPane.add(cadastrarPostoButton, 1, 3);

        return gridPane;
    }

    private void clearFields(TextField nomeTextField, TextField localizacaoTextField) {
    }

    private GridPane createAbastecimentoTabContent() {
        GridPane gridPane = createCommonGridPane();

        Label veiculoLabel = new Label("Veículo (Placa):");
        TextField veiculoTextField = new TextField();

        Label postoLabel = new Label("Posto (Nome):");
        TextField postoTextField = new TextField();

        Label dataLabel = new Label("Data:");
        DatePicker dataDatePicker = new DatePicker(LocalDate.now());

        Label litrosLabel = new Label("Quantidade de Litros:");
        TextField litrosTextField = new TextField();

        Label kmLabel = new Label("Quilometros Odômetro:");
        TextField kmTextField = new TextField();

        Label precoLabel = new Label("Preço:");
        TextField precoTextField = new TextField();

        Label combustivelLabel = new Label("Tipo de Combustível:");
        TextField combustivelTextField = new TextField();

        gridPane.addRow(1, veiculoLabel, veiculoTextField);
        gridPane.addRow(2, postoLabel, postoTextField);
        gridPane.addRow(3, dataLabel, dataDatePicker);
        gridPane.addRow(4, litrosLabel, litrosTextField);
        gridPane.addRow(5, kmLabel, kmTextField);
        gridPane.addRow(6, precoLabel, precoTextField);
        gridPane.addRow(7, combustivelLabel, combustivelTextField);

        Button cadastrarAbastecimentoButton = new Button("Cadastrar Abastecimento");
        cadastrarAbastecimentoButton.setOnAction(event -> {
            try {
                Veiculo veiculo = veiculoRepository.buscarVeiculoPorPlaca(veiculoTextField.getText());
                Posto posto = postoRepository.buscarPostoPorNome(postoTextField.getText());

                if (veiculo == null) {
                    System.out.println("Veículo não encontrado!");
                    return;
                }

                if (posto == null) {
                    System.out.println("Posto não encontrado!");
                    return;
                }

                Date data = new Date(dataDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
                double litros = Double.parseDouble(litrosTextField.getText());
                int km = Integer.parseInt(kmTextField.getText());
                double preco = Double.parseDouble(precoTextField.getText());

                Abastecimento abastecimento = new Abastecimento(veiculo, posto, data, litros, km, preco, combustivelTextField.getText());
                abastecimentoRepository.cadastrarAbastecimento(abastecimento);

                System.out.println("Abastecimento cadastrado com sucesso!");
                clearFields(veiculoTextField, postoTextField, litrosTextField, kmTextField, precoTextField, combustivelTextField);
            } catch (Exception e) {
                System.out.println("Erro ao cadastrar abastecimento: " + e.getMessage());
            }
        });

        gridPane.add(cadastrarAbastecimentoButton, 1, 8);

        return gridPane;
    }


    private void clearFields(TextField veiculoTextField, TextField postoTextField, TextField litrosTextField,
            TextField kmTextField, TextField precoTextField, TextField combustivelTextField) {
    }

    private GridPane createRelatoriosTabContent() {
        GridPane gridPane = createCommonGridPane();

        Button listarVeiculosButton = new Button("Listar Veículos");
        listarVeiculosButton.setOnAction(event -> {
            List<Veiculo> veiculos = veiculoRepository.listarVeiculos();
            updateResultadoTextArea("Lista de Veículos:\n" + veiculos.toString());
        });

        Button listarPostosButton = new Button("Listar Postos");
        listarPostosButton.setOnAction(event -> {
            List<Posto> postos = postoRepository.listarPostos();
            updateResultadoTextArea("Lista de Postos:\n" + postos.toString());
        });

        Label placaLabel = new Label("Placa do Veículo:");
        TextField placaTextField = new TextField();

        Button listarAbastecimentosButton = new Button("Listar Abastecimentos por Placa");
        listarAbastecimentosButton.setOnAction(event -> {
            String placa = placaTextField.getText();
            List<Abastecimento> abastecimentos = abastecimentoRepository.listarAbastecimentosPorPlaca(placa);
            updateResultadoTextArea("Lista de Abastecimentos para a placa " + placa + ":\n" + abastecimentos.toString());
        });

        Label mediaKmPorLitroLabel = new Label("Média de Km por Litro (Placa):");
        TextField mediaKmPorLitroTextField = new TextField();

        Button calcularMediaKmPorLitroButton = new Button("Calcular Média Km por Litro");
        calcularMediaKmPorLitroButton.setOnAction(event -> {
            String placa = mediaKmPorLitroTextField.getText();
            double mediaKmPorLitro = abastecimentoRepository.calcularMediaKmPorLitroPorPlaca(placa);
            updateResultadoTextArea("\nMédia de Km por Litro para a placa " + placa + ": " + mediaKmPorLitro);
        });

        Label mediaPrecoLabel = new Label("Média de Preço (Placa):");
        TextField mediaPrecoTextField = new TextField();

        Button calcularMediaPrecoButton = new Button("Calcular Média de Preço");
        calcularMediaPrecoButton.setOnAction(event -> {
            String placa = mediaPrecoTextField.getText();
            double mediaPreco = abastecimentoRepository.calcularMediaPrecoPorPlaca(placa);
            updateResultadoTextArea("Média de Preço para a placa " + placa + ": " + mediaPreco);
        });

        gridPane.addRow(1, listarVeiculosButton, listarPostosButton);
        gridPane.addRow(2, placaLabel, placaTextField);
        gridPane.addRow(3, listarAbastecimentosButton);
        gridPane.addRow(4, mediaKmPorLitroLabel, mediaKmPorLitroTextField);
        gridPane.addRow(5, calcularMediaKmPorLitroButton);
        gridPane.addRow(6, mediaPrecoLabel, mediaPrecoTextField);
        gridPane.addRow(7, calcularMediaPrecoButton);

        return gridPane;
    }

    private GridPane createCommonGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        return gridPane;
    }

    private void updateResultadoTextArea(String resultado) {
        resultadoTextArea.appendText(resultado + "\n");
    }
}