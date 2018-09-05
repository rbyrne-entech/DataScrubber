package com.synthes.scrubber;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DBConnection {

	String dbName, userName,password,port,url;
	Connection conn;
	
	String querySelect = "SELECT * FROM ";
	
	public DBConnection() {
		
	}	
	
	/**
	 * 
	 * @param dbName
	 * @param username
	 * @param password
	 * @param port
	 * @param url
	 */
	public void setCreds(String dbName, String username, String password, String port, String url) {
		this.dbName=dbName;
		this.userName=username;
		
		if(this.password!=null) {
			this.password=password;
		}else {this.password="";}
		
		this.port=port;
		this.url=url;
	}
	
	/**
	 * 
	 */
	public void connect() {
		this.conn = null;
	    Properties connectionProps = new Properties();
	    //need to add db names
	    connectionProps.put("dbname", this.dbName);
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
	        try {
	    	    System.out.println("Connecting...");

				this.conn = DriverManager.getConnection(
				           "jdbc:" + "mariadb" + "://" +
				           this.url +
				           ":" + this.port + "/",
							connectionProps);
				
				System.out.println("Connected to "+this.url+ " "+ this.dbName);
			} catch (SQLException e) {
				if(this.url!=null) {
					System.out.println("Failed to connected to "+this.url + " "+ this.dbName);
				}
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * 
	 */
	public void disconnect() {
		
		try {
			if(conn!=null&&!this.conn.isClosed()) {
				this.conn.close();
				this.conn=null;
			}
		} catch (SQLException e) {
			if(this.url!=null) {
				System.out.println("Error clossing connection for "+ this.url);
			}
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @param connDest
	 * @param destDB
	 */
	public void migrate(Connection connDest,String destDB) {
		try {
			System.out.println("Schema is: " + this.conn.getMetaData().toString());
			DatabaseMetaData dbMeta = this.conn.getMetaData();
			ResultSet rs = dbMeta.getTables(this.dbName,null,"%",null);
			
			//loops through tables
			while(rs.next()) {
				//rs.getString(3) is table names 
				//create table 
			System.out.println("Migrating Table: "+rs.getString(3));
			Statement selectstmnt = conn.createStatement();
			ResultSet rsSelect = selectstmnt.executeQuery(querySelect+this.dbName+"."+rs.getString(3));
			
			ResultSetMetaData rsColumns = rsSelect.getMetaData();
			int colCount = rsColumns.getColumnCount();
			
			String colInfo = columnInfoBuilder( rsColumns);
			ResultSet rsPrimaryKeys = dbMeta.getPrimaryKeys(null, null, rs.getString(3));
			String primaryKey = null;//getPrimaryKeyFromTable(rsPrimaryKeys);
			
			
			createTable(connDest, destDB, rs.getString(3),colInfo,primaryKey);
			//loops thorugh rows
			while(rsSelect.next()) {
				//change number- goes to different columns
					//insert
					this.insertStatement(destDB,rs.getString(3),rsColumns,rsSelect,colCount, connDest);
					/*for(int i =1; i<colCount;i++) {
						System.out.println("this one "+rsSelect.getString(i)+ " col "+colCount);
					}*/
				}
			System.out.println("Finished Migrating Table: "+ rs.getString(3));

			}
			System.out.println("Finished Migration ");
			
		} catch (SQLException e) {
			System.out.println("Migration Error..." );
			e.printStackTrace();
		}
	}
	
	/*private String getPrimaryKeyFromTable(ResultSet rsPrimaryKey) {
		int n =4;
		try {
			rsPrimaryKey.next();
			System.out.println("Primary key is " + rsPrimaryKey.getString(n));
			return rsPrimaryKey.getString(n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	

	/**
	 * 
	 * 
	 * 
	 * @param columnNames
	 * @param tableName
	 * @return
	 */
	public ResultSet read(List<String> columnNames, String tableName) {
		ResultSet rsSet=null;
		try {
			Statement stmnt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String columns = this.stringBuilder(columnNames);

			rsSet = stmnt.executeQuery("Select " + columns + " from "+ this.dbName+"." +tableName);
			System.out.println("Getting Table Data: " +tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsSet;
	}
	//result set, tablename, columns, values

	/**
	 * 
	 * 
	 * @param rsSet
	 * @param tableName
	 * @param columns
	 * @param values
	 */
	public void updateAll(ResultSet rsSet, String tableName, List<String> columns, List<String> values) {
		String updateString = "";
		try {
				ResultSetMetaData rsSetMeta = rsSet.getMetaData();
		
				for(int i = 0; i <columns.size(); i++) {
					if(rsSetMeta.getColumnTypeName(i+1).toLowerCase().contains("char")){
						String temp = "'"+ "xxxxxxx" +"'";
						values.set(i, temp);
						
					}else {
						values.set(i, "999");
					}
					updateString += columns.get(i) + " = " +values.get(i);
					
					if(i+1!=columns.size()) {
						updateString += ", ";
					}
				}
			
				
					Statement stmnt = this.conn.createStatement();
					stmnt.executeQuery("UPDATE "+ this.dbName+"."+tableName +" SET "+ updateString);
				
				System.out.println("Updated Table: " + tableName);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	
	
	public void select() {
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param createCon
	 * @param dbName
	 * @param tableName
	 * @param colInfo
	 * @param primaryKey
	 */
	public void createTable(Connection con, String dbName, String tableName,String colInfo,String primaryKey) {
		//String primaryKeyString = ",PRIMARY KEY ("+primaryKey+")";
		 //+primaryKeyString
		String createSql = "CREATE TABLE "+ dbName+"."+tableName+"( "+colInfo + ");";
		Statement createStmnt;
		try {
			createStmnt = con.createStatement();
			createStmnt.executeUpdate(createSql);
			System.out.println("Created Table: " + dbName + "." +tableName);
		} catch (SQLException e) {
			
			System.out.println("Failed to create table "+tableName);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param dbName-DataBase name
	 * @param tName-Table name
	 * @param colInfo-Information about columns names and type
	 * @param rSet - values 
	 * @param colCount
	 * @param conn
	 */
	public void insertStatement(String dbName,String tName, ResultSetMetaData colInfo, ResultSet rSet,int colCount,Connection conn) {
		String queryBegin ="Insert Into "+dbName+"."+tName;
		String values = "";
		String columns ="";

		try {

			for(int i =1; i<=colCount;i++) {
				
				if(colInfo.getColumnTypeName(i).toLowerCase().contains("char")){
					values+="'";
				}
				columns +=colInfo.getColumnName(i);
				values +=rSet.getString(i);
				if(colInfo.getColumnTypeName(i).toLowerCase().contains("char")){
					values+="'";
				}
				if(i+1<=colCount) {
					columns+=",";
					values+=",";
				}
				//System.out.println("Type is " +colInfo.getColumnTypeName(i));
				
			}
			Statement stmnt = conn.createStatement();
			//TODO add column names to ensure order
			stmnt.executeQuery(queryBegin+ " values ("+values+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Takes ResultSetMeta data and returns strings seperated by commas 
	 * @param rsMeta
	 * @return
	 * @throws SQLException
	 */
	public String columnInfoBuilder( ResultSetMetaData rsMeta) throws SQLException {
		String columnInfo = "";
		for(int i=1;i<=rsMeta.getColumnCount();i++) {
			columnInfo += rsMeta.getColumnName(i) + " " + rsMeta.getColumnTypeName(i)+"("+rsMeta.getColumnDisplaySize(i)+")";
			if(i<rsMeta.getColumnCount()) {
				columnInfo+=",";
			}
		}
		
		return columnInfo;
	}
	
	
	
	/**
	 * Returns a string of items in a list seperated by commas
	 * @param list 
	 * @return 
	 */
	private String stringBuilder(List<String> list) {
		String ret ="";
		for(int i =0; i<list.size();i++) {
			ret += list.get(i);
			if((i+1)!=list.size()) {
				ret+=" ,";
			}
		}
		return ret;
	}
	
	
	
	/**
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		return this.conn;
	}
	
	
	
	/**
	 * 
	 * @return connection dbname
	 */
	public String getDbName() {
		return this.dbName;
	}
	
}
