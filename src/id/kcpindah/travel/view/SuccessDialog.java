package id.kcpindah.travel.view;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by KCPIndah on 6/1/2017.
 */
public class SuccessDialog extends JFXDialogLayout {
    public SuccessDialog() {
        this.setHeading(new Text("SUCCESS"));
        this.setBody(new Text("User Account has been registered."));
    }

    public void showDialog(StackPane stackPane) {
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(this);
        dialog.show(stackPane);
    }
}
