import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class frmEliminaPalabra extends JDialog implements KeyListener,ActionListener {
    private Sopaletras2 sopa;
    private JMenuItem mnuRemove;
    private JList lista;
    private Color colorf;
    private boolean boolpinta;

    public frmEliminaPalabra(Sopaletras2 xsopa, Color xcolor, boolean bPinta){
        super();
        sopa = xsopa;
        colorf = xcolor;
        boolpinta = bPinta;
        Ini();
        this.setSize(320,240);
        this.setModal(true);
        this.setTitle("Eliminar palabra agregada");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void Ini(){
        JPanel paneMain = new JPanel(new BorderLayout());
        JMenuBar menu = new JMenuBar();
        JMenu mnuE = new JMenu("Edición");
        mnuRemove = new JMenuItem("Eliminar");
        mnuRemove.addActionListener(this);
        mnuE.add(mnuRemove);
        menu.add(mnuE);
        lista = new JList();
        lista.setListData(sopa.getVectorPalabas());
        lista.addKeyListener(this);

        paneMain.add(menu, BorderLayout.NORTH);
        paneMain.add(lista, BorderLayout.CENTER);
        this.add(paneMain);
    }
    private void RemovePalabra(){
        try{
            int index = lista.getSelectedIndex();
            if(boolpinta){
                sopa.PintaPalabra(index, colorf);
                //PalabrasIntro pa = sopa.getPalabra(index);
                sopa.RemovePalabra(index);
                /*int i;
                for(i = 0; i < pa.getSizePalabra(); i++){
                    sopa.PintaPosicion(pa.getPosX(i), i, colorf);
                }*/
                sopa.PintaAllPalabra(Color.YELLOW);
            }else
                sopa.RemovePalabra(index);
            lista.setListData(sopa.getVectorPalabas());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Error al seleccionar palabra", "SopaLetras", 1);
        }
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 27)
            this.dispose();
        else if(e.getKeyCode() == 127)
            RemovePalabra();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        RemovePalabra();
    }
}
