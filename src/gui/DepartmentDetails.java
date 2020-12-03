package gui;

import com.mysql.cj.jdbc.Driver;
import model.DepartmentModel;
import model.DepartmentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDetails {
    private JTextField txtIdDepartment;
    private JTextField txtDepartmentName;
    private JButton btnSave;
    private JPanel mainPanel;
    private DepartmentModel department;

    public JPanel getPanel() { return mainPanel; }
    public JButton getBtnSave() { return btnSave; }

    public DepartmentDetails(DepartmentModel pModel)
    {
        department = pModel;
        init();
    }

    private void init()
    {
        txtIdDepartment.setText(String.valueOf(department.getIdDepartment()));
        txtDepartmentName.setText(department.getName());
    }

    public void save()
    {
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
            String sql =  "UPDATE departments " +
                    "SET department_name = '" + txtDepartmentName.getText() +"' "+
                    "WHERE department_id = " + department.getIdDepartment() ;
            System.out.println(sql);
            Statement smt = con.createStatement() ;
            if (smt.executeUpdate(sql) > 0) {
                department.setName(txtDepartmentName.getText());
            }
        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }
    }
}
