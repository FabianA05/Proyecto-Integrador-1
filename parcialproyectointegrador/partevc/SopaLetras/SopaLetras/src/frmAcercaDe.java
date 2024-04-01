import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class frmAcercaDe extends JDialog implements ActionListener{
    private JButton cmdSalir;

    public frmAcercaDe(){
        super();
        IniComponentes();
        this.setTitle("Acerca de SopaLetras");
        this.setSize(437,219);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
    }

    private void IniComponentes(){

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel pane1 = new JPanel(new GridLayout(5,1));
        JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel paneIco = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lblTitulo = new JLabel("SopaLetras",JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial",Font.BOLD,18));

        JLabel lblDescripcion = new JLabel("Programa para crear una sopa de letras");
        JLabel lbl1 = new JLabel("(22 de Marzo del 2024)");
        JLabel lbl2 = new JLabel("Santiago Villegas");
        cmdSalir = new JButton("Salir");
        cmdSalir.addActionListener(this);

        pane2.add(cmdSalir);
        pane1.add(lblDescripcion);
        pane1.add(lbl1);
        pane1.add(lbl2);
        pane1.add(pane2);

        panelPrincipal.add(lblTitulo,BorderLayout.NORTH);
        panelPrincipal.add(paneIco,BorderLayout.WEST);
        panelPrincipal.add(pane1,BorderLayout.EAST);

        this.add(panelPrincipal);
        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
