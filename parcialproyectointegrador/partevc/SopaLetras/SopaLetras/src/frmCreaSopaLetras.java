import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class frmCreaSopaLetras extends JDialog implements ActionListener,KeyListener{
    private JButton JBAceptar;
    private JTextField txtFilas,txtColumnas,txtLetras;

    public frmCreaSopaLetras(){
        super();
        IniciaGUI();
        this.setTitle("SopaLetras");
        this.setSize(320,240);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void IniciaGUI(){
        JPanel panePrincipal = new JPanel(new GridLayout(4,1));
        JLabel label0 = new JLabel("  Crear Sopa de letras: ");
        JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label1 = new JLabel("Filas: ");
        txtFilas = new JTextField("20",4);
        txtFilas.setHorizontalAlignment(JTextField.CENTER);
        txtFilas.addKeyListener(this);
        pane1.add(label1);
        pane1.add(txtFilas);
        JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label2 = new JLabel("Columnas: ");
        txtColumnas = new JTextField("20",4);
        txtColumnas.setHorizontalAlignment(JTextField.CENTER);
        txtColumnas.addKeyListener(this);
        pane2.add(label2);
        pane2.add(txtColumnas);
        JPanel pane21 = new JPanel(new GridLayout(1,2));
        pane21.add(pane1);
        pane21.add(pane2);
        JPanel pane3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label3 = new JLabel("Letras: ");
        txtLetras = new JTextField("ABCDEFGHIJKLMNOPQRSTUVWXYZ",22);
        txtLetras.addKeyListener(this);
        pane3.add(label3);
        pane3.add(txtLetras);

        JPanel pane4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JBAceptar = new JButton("Aceptar");
        JBAceptar.addActionListener(this);
        pane4.add(JBAceptar);
        panePrincipal.add(label0);
        panePrincipal.add(pane21);
        panePrincipal.add(pane3);
        panePrincipal.add(pane4);
        this.add(panePrincipal);
    }
    private int verifyFilas(){
        try{
            int a = Integer.parseInt(txtFilas.getText());
            int b = Integer.parseInt(txtColumnas.getText());
            int i = 0;
            if(a < 2 || a > 50)
                i = 2;
            if (b < 2 || b > 50)
                i = 2;
            return i;
        }catch(Exception e){
            return 1;
        }
    }

    private void crearSopaLetras(){
        int num = verifyFilas();
        if(num == 0){
            if(txtLetras.getText().length()>0){
                Sopaletras2 sopa = new Sopaletras2(Integer.parseInt(txtFilas.getText()),Integer.parseInt(txtColumnas.getText()),txtLetras.getText());
                frmSopaLetras frm = new frmSopaLetras(sopa);
                frm.setVisible(true);
                frm.setLocationRelativeTo(this);
                this.dispose();
            }else
                JOptionPane.showMessageDialog(rootPane, "Al menos debe haber un carácter", "SopaLetras", 1);
        }else if(num == 1)
            JOptionPane.showMessageDialog(rootPane, "Datos no válidos", "SopaLetras", 1);
        else
            JOptionPane.showMessageDialog(rootPane, "Número máximo para filas y columnas es 50 y mínimo 2", "SopaLetras", 1);
    }
    public void actionPerformed(ActionEvent e) {
        crearSopaLetras();
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == '\n')
            crearSopaLetras();
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
