/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valuta;
/**
 *
 * @author MIROSLAV
 */
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class baza {
      
 static public Connection povezi_bazu() {
 try {
     Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/baza_valute");
        return connection;
    } catch (SQLException sqlException) {
        return null; 
    }
    
 }
    
    
 static public float uzmi_iz_baze(String Drzava, Connection connection) {
    
     
     try {
         float kurs=0;
         PreparedStatement st = connection
                 .prepareStatement("SELECT kurs FROM kursevi WHERE ime=?");
         
         st.setString(1, Drzava);
         ResultSet rs = st.executeQuery();
         
        while (rs.next()) {
        kurs = rs.getFloat("kurs");
        
        }
        return kurs;
     }  catch (SQLException ex) {
        return 0;
     }
     
     
 }

 
 static public int upisi_u_bazu(String Drzava, Float kurs, Connection connection) {
    
     
     try {
 
         PreparedStatement st = connection
                 .prepareStatement("INSERT INTO kursevi(ime,kurs) VALUES(?,?)");
         
         st.setString(1, Drzava);
         st.setFloat(2, kurs);
         st.execute();
         
        
        
        return 1;
     }  catch (SQLException ex) {
        
         return 0; 
     }
 }
     
static public String[] popuniComboBox(){
     try {
         int i=0;
         String[] data =  new String[50];
         Connection connection = povezi_bazu();
         
         PreparedStatement st = connection
                 .prepareStatement("SELECT ime FROM kursevi");
         ResultSet rs = st.executeQuery();
         
         while (rs.next()) {
             data[i]=rs.getString("ime") ;
             i++;
             
         }
         return data;
     } catch (SQLException ex) {
         Logger.getLogger(baza.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }


}
 
 
 
    
    
    

 
}
