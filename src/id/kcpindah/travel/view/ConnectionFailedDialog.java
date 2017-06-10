package id.kcpindah.travel.view;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ConnectionFailedDialog extends JFXDialogLayout {
	public ConnectionFailedDialog() {
        this.setHeading(new Text("FAILED"));
        this.setBody(new Text("Connection to MySQL Database cannot be established."));
    }
	
	public void showDialog(StackPane stackPane) {
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(this);
        dialog.show(stackPane);
    }
}
