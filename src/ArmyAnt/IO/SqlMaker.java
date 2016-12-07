package ArmyAnt.IO;
import java.sql.*;

public class SqlMaker {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean connect(String address, String user, String password, String defaultDB){
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + address + "/" + defaultDB + "?useUnicode=true", user, password);
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void disconnect() throws SQLException{
		con.close();
	}
	
	public boolean createTable(String name, String[] column, String[] type){
		String sqlStr = "CREATE TABLE "+name+" ( ";
		try {
			if(column == null || column.length <= 0){
				return executeSql(sqlStr + " )");
			}
			if(column.length != type.length)
				return false;
			sqlStr += column[0] + " " + type[0];
			for(int i=1; i<column.length; ++i){
				sqlStr += ", " + column[i] + " " + type[i];
			}
			sqlStr += " )";
			return !executeSql(sqlStr);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
			// e.printStackTrace();
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertInto(String table, String[] column, java.util.List<String /*data*/>data) throws SQLException{
		String sqlStr = "INSERT INTO " + table + "(";
		for(int i=0; i<column.length;i++){
			sqlStr += " " + column[i] + (i==column.length-1?" ":",");
		}
		sqlStr += ") VALUES (";
		java.util.Iterator<String> values = data.iterator();
		while(values.hasNext()){
			sqlStr += " \"" + values.next().replace('"', '“') + (values.hasNext()?"\",":"\" )");
		}
		return !executeSql(sqlStr);
	}
	
	public boolean executeSql(String sqlCode) throws SQLException{
		PreparedStatement state = con.prepareStatement(sqlCode);
		return state.execute();
	}
	
	private Connection con = null;
}
