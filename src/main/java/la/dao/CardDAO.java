package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.CardBean;
import la.bean.CustomerBean;

public class CardDAO {
	
	 private String url = "jdbc:postgresql:sample";
	    private String user = "student";
	    private String pass = "himitu";

	    public CardDAO() throws DAOException {
	        try {
	            
				Class.forName("org.postgresql.Driver");
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new DAOException("JDBCドライバの登録の失敗しました。");
	        }
	    }

	    public int saveOrder(CustomerBean customer, CardBean card)
				                                    throws DAOException {
	        
	        int customerNumber = 0;
	        String sql = "SELECT nextval('customer_code_seq')";

	        try (
	             Connection con = DriverManager.getConnection(url, user, pass);
				
				 PreparedStatement st = con.prepareStatement(sql);
				
				 ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
				    customerNumber = rs.getInt(1);
				}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
	        }

	       
	        sql = "INSERT INTO card VALUES(?, ?, ?, ?, ?, ?)";
			
	        try (
				 Connection con = DriverManager.getConnection(url, user, pass);
				
				 PreparedStatement st = con.prepareStatement(sql);) {
				
				st.setInt(1, customerNumber);
				st.setString(2, card.getName());
				st.setString(3, card.getNum());
				st.setString(4, card.getMonth());
				st.setString(5, card.getYear());
				st.setString(6, card.getPass());
				
				st.executeUpdate();
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
	        }

	       
	        int orderNumber = 0;
	        sql = "SELECT nextval('ordered_code_seq')";
			
	        try (
				 Connection con = DriverManager.getConnection(url, user, pass);
				 
				 PreparedStatement st = con.prepareStatement(sql);
				
				 ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
				    orderNumber = rs.getInt(1);
				}
				return orderNumber;
				
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
	        }
	    }
}