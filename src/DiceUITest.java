import javax.swing.*;
import java.awt.*;

public class DiceUITest {

    private static DiceUI ui;


    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            ui = new DiceUI();
            JFrame ex = ui;
            ex.setVisible(true);
            ui.DiceBoard.DrawGraphics(2,100,123,5,6);
        });






    }
}
