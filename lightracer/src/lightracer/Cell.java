package lightracer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
/*
 * Authors: Alex Reiff
 */
	
public class Cell extends JPanel {

    public Cell() {}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}