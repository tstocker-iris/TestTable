import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import gui.DepartmentDetails;
import model.DepartmentModel;
import model.DepartmentTableModel;

public class TestTable {

    private JPanel mainPanel;
    private JTable table1;

    public TestTable() {
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    super.mouseClicked(e);
                    int row = table1.rowAtPoint(e.getPoint());
                    if (row >= 0 ) {
                        DepartmentTableModel model = (DepartmentTableModel) table1.getModel();

                        DepartmentModel department = model.getDepartment(row);
                        JFrame detailsFrame = new JFrame("Détail département " + department.getName());
                        DepartmentDetails panel = new DepartmentDetails(department);
                        panel.getBtnSave().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                panel.save();
                                JOptionPane.showMessageDialog(null, "Département sauvegardé avec succès");
                                detailsFrame.setVisible(false);
                                detailsFrame.dispose();
                            }
                        });

                        detailsFrame.setContentPane(panel.getPanel());

                        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        detailsFrame.pack();

                        detailsFrame.setVisible(true);
                    }
                }
            }
        });
    }

    public JTable getTable1() { return table1; }

    public static TableModel getTableMode()
    {
        TableModel model = null;
        try {
            // chargement de la classe par son nom
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;
            Driver pilote = (Driver)c.newInstance() ;
            // enregistrement du pilote auprès du DriverManager
            DriverManager.registerDriver(pilote);
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3390" ;  // port MySQL par défaut
            // Nom de la base ;
            String nomBase =  "employee" ;  // dépend du contexte
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
            // jdbc:mysql://localhost:3390/employee
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "example" ;  // dépend du contexte
            // Connexion
            Connection con = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;

            // Envoi d’un requête générique
            String sql =  "select department_id, department_name from departments" ;
            Statement smt = con.createStatement() ;
            ResultSet res = smt.executeQuery(sql) ;

            List<DepartmentModel> data = new ArrayList<DepartmentModel>();

            while (res.next()) {
                data.add(new DepartmentModel(res.getInt("department_id"), res.getString("department_name")));
            }

            model = new DepartmentTableModel(data);

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }
        return model;
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Test Table");
        TestTable panel = new TestTable();

        panel.getTable1().setModel(getTableMode());

        frame.setContentPane(panel.mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setVisible(true);
    }
}
