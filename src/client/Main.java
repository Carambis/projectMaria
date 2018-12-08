package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main extends JFrame {
    public void createAndShowGUI() {
        JPanel groupLayoutPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(groupLayoutPanel);
        groupLayout.setAutoCreateGaps(true);
        groupLayoutPanel.setLayout(groupLayout);

        GroupLayout.Group hg1 = groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        GroupLayout.Group hg2 = groupLayout.createParallelGroup();

        GroupLayout.Group vg1 = groupLayout.createParallelGroup();
        GroupLayout.Group vg2 = groupLayout.createParallelGroup();

        // Horizontal group
        GroupLayout.SequentialGroup hseq1 = groupLayout.createSequentialGroup();
        hseq1.addGroup(hg1);
        hseq1.addGroup(hg2);

        // Vertical group
        GroupLayout.SequentialGroup vseq1 = groupLayout.createSequentialGroup();
        vseq1.addGroup(vg1);
        vseq1.addGroup(vg2);

        groupLayout.setHorizontalGroup(hseq1);
        groupLayout.setVerticalGroup(vseq1);

        JPanel contentPane = panel1;
        contentPane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        contentPane.add(groupLayoutPanel, new com.intellij.uiDesigner.core.GridConstraints());

        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        jMenu3 = new JMenu();
        jMenu4 = new JMenu();

        jMenu1.setText("Цены на товары/услуги");
        jMenu1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenu2.setText("Затраты");
        jMenu2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenu3.setText("Расходы");
        jMenu3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenu4.setText("Пользователи");
        jMenu4.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jMenu4MousePressed(evt);
            }
        });

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);

        if (GlobalVariable.getUserRole() == 1) {
            jMenuBar1.add(jMenu4);
        }

        JFrame frame = new JFrame("Курсовой проект: \"Подсистема учета и ведения семейного бюджета\" Довгун М.А.");
        frame.setJMenuBar(jMenuBar1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(contentPane);
        frame.pack();
        frame.setSize(new Dimension(800, 300));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        //frame.setBackground(Color.LIGHT_GRAY);

    }



    private void jMenu4MousePressed(MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        try {
            User user = new User();
            user.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jMenu3MousePressed(MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        try {
            Outcome outcome = new Outcome();
            outcome.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu2MousePressed(MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        try {
            Costs costs = new Costs();
            costs.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenu1MousePressed(MouseEvent evt) {
        try {
            Price price = new Price();
            price.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IllegalAccessException, UnsupportedLookAndFeelException, InstantiationException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Auth().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenu jMenu4;
    private JMenu jMenu5;
    private JMenuBar jMenuBar1;
    private JTextField newPassword;
    private JPanel panel1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setEnabled(true);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    // End of variables declaration//GEN-END:variables

}
