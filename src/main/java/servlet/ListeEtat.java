/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author pedago
 */
public class ListeEtat extends DAO {
    
    public ListeEtat(DataSource dataSource) {
        super(dataSource);
    }
    
    public ArrayList<String> listetat() throws DAOException, SQLException{
        String sql = "SELECT DISTINCT STATE  FROM CUSTOMER";
        ArrayList<String> liste_etat= new ArrayList<String>() ;
        try (   Connection connection = this.myDataSource.getConnection(); // Ouvrir une connexion
			Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
			ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
		) {
			while (rs.next()) { 
				String result = rs.getString("STATE");
                                liste_etat.add(result);;
                                
			}
                        rs.close();
                        stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}

        
        
        return liste_etat;
    }
    
}
