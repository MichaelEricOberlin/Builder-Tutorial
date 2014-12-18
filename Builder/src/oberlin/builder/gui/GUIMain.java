package oberlin.builder.gui;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

import oberlin.algebra.builder.AlgebraicBuilder;
import oberlin.builder.Builder;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.EOT;

public class GUIMain extends Application {
	
	private AST program;
	private Builder builder = new AlgebraicBuilder();
	private List<HBox> hBoxStack = new ArrayList<>();
	private int boxIndex = 0;
	private Map<AST, Node> nodeMap = new HashMap<>();
	
	private VBox vbox = new VBox();
	private StackPane stackPane = new StackPane();
	private Pane freehand = new Pane();
	
	private final String hboxTopStyle = "-fx-alignment: center;" +
			"-fx-border-radius: 1.0;" +
			"-fx-border-color: darkblue;";
	private final String hboxBottomStyle = "-fx-alignment: center;" +
			"-fx-border-radius: 1.0;" +
			"-fx-border-color: blue;";
	
	public static void main(String...args) throws IOException {
		try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));) {
			/*out.write("Enter code: ");
			out.flush();
			String input = in.readLine();*/
			
			//DEBUG:
			String input = "1+2";
			
			launch(new String[]{input});
		}
//		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		program = getAST(getParameters().getRaw().get(0));
		
//		Pane vbox = new Pane();
//		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(6.0);
		System.out.println("Spacing: " + vbox.getSpacing());
		
		stackPane.getChildren().add(vbox);
		stackPane.getChildren().add(freehand);
		
		final Scene scene = new Scene(stackPane, 640, 480);
		
		stage.setScene(scene);
		stage.show();
		stage.centerOnScreen();
		
		displayAll(vbox, program);
		
		//DEBUG
		
		System.out.println("Pane width: " + vbox.getWidth());
		
		
	}
	
	
	//PRIVATE UTILITY CLASSES
	private AST getAST(String programArgument) {
		return builder.getParseTree(programArgument);
	}
	
	private void displayAll(Pane pane, AST root) {
		BorderPane borderPane = new BorderPane();
//		HBox hbox = getNextHBox();
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle(hboxTopStyle);
		display(hbox, root);
		borderPane.setCenter(hbox);
		
		boxIndex++;
		HBox hboxKids = new HBox();
		hboxKids.setAlignment(Pos.CENTER);
//		hboxKids.setStyle(hboxTopStyle);
		for(AST branch : root.getContainedNodes()) {
			displayAll(hboxKids, branch);
		}
		hboxKids.setStyle(hboxBottomStyle);
		borderPane.setBottom(hboxKids);
		pane.getChildren().add(borderPane);
//		vbox.getChildren().add(hboxKids);
		boxIndex--;
	}
	
	private void display(Pane pane, AST tree) {
		//don't render EOTs. (Should be a way around instanceof here.)
		if(tree instanceof EOT) return;
		
		Node node = new Node(tree);
		pane.getChildren().add(node);
		nodeMap.put(tree, node);
	}
	
	private HBox getNextHBox() {
		HBox hbox;
		if(hBoxStack.size() <= boxIndex) {
			hbox = new HBox();
			hBoxStack.add(hbox);
			vbox.getChildren().add(hbox);
		} else {
			hbox = hBoxStack.get(boxIndex);
		}
		return hbox;
	}
	
	
	//INTRINSIC CLASSES
	class Node extends BorderPane {
		private Label label;
		
		final String style = "-fx-background-color: lightblue;" +
				"-fx-border-color: black;" +
			    "-fx-border-radius: 5;" +
			    "-fx-padding: 3 6 6 6;";
		final String labelStyle = "-fx-alignment: center;" +
			    "-fx-text-alignment: center;" +
				"-fx-content-display: center;";
		
		private List<Node> children = new ArrayList<>();
		
		public Node(AST ast) {
			label = new Label(ast.getClass().getSimpleName() + " " + ast.hashCode());
			
			label.setStyle(labelStyle);
			
			this.setCenter(label);
			
			this.setStyle(style);
		}
	}

}
