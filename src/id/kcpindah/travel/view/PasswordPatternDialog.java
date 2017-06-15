package id.kcpindah.travel.view;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PasswordPatternDialog extends JFXDialogLayout {
	public PasswordPatternDialog() {
        this.setHeading(new Text("FAILED"));
        this.setBody(new Text("Password must contain number and at least 4 character long"));
    }
	
	public void showDialog(StackPane stackPane) {
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(this);
        dialog.show(stackPane);
    }
}

