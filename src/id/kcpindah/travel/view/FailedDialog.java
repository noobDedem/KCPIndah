package id.kcpindah.travel.view;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class FailedDialog extends JFXDialogLayout {
	public FailedDialog() {
        this.setHeading(new Text("FAILED"));
        this.setBody(new Text("One of the field is EMPTY."));
    }
	
	public void showDialog(StackPane stackPane) {
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(this);
        dialog.show(stackPane);
    }
}
