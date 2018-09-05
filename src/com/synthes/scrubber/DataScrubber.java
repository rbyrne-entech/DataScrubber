package com.synthes.scrubber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import Config.Configuration;
import Config.ObjectFactory;

public class DataScrubber {

	private String configFile;
	private DBConnection dbConnectionSrc,dbConnectionDest;
	Configuration config;
	//table, arraylist of fields
	//private Map<String,ArrayList<String>> relationMap;
	
	//C:\Users\rbyrne\Documents\Type3DataAnon\exampleConfig.xml
	public DataScrubber(String configFile) {
		this.configFile = configFile;
		this.dbConnectionSrc = new DBConnection();
		this.dbConnectionDest = new DBConnection();

		
	}
	
	/**
	 * 
	 */
	public void scrub() {
		List<Configuration.Table> tables = this.config.getTable();
		for(int i=0;i< tables.size();i++) {
			List<String> columns =tables.get(i).getColumn();
			//Map<String,String> colAndValues = new Map<String,String>();
			ResultSet rsSet = this.dbConnectionDest.read(columns, tables.get(i).getTablename());
			//make x if string 9 if num
			System.out.println("Scrubbing Table: "+ tables.get(i).getTablename());
			List<String> values = new ArrayList<String>();
				try {
					while( rsSet.next()) {
						for(int j =0; j<columns.size();j++) {
							values.add(rsSet.getString(columns.get(j)));
							//modify values here in future
						}
						//this.cleanValues(values, rsSet);
						this.dbConnectionDest.updateAll(rsSet,tables.get(i).getTablename(),columns, values);
					}
					System.out.println("Finished Scrubbing data");
				} catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	
	/**
	 * Can do anything to data here possibly another function to do a particular thing random or encrypt
	 * @param values-list of values that need to change
	 * @param rsSet- list of types of values that need to change
	 * possibly just make a map and pass that
	 */
	/*private void cleanValues(List<String> values, ResultSet rsSet) {
		try {
			ResultSetMetaData rsSetMeta = rsSet.getMetaData();		
			
			for(int i = 0; i < values.size()-1; i++) {
				
					if(rsSetMeta.getColumnTypeName(i+1).toLowerCase().contains("char")){
						String temp = "xxxxx";
						values.set(i, temp);
					} else {
						values.set(i, "999");
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}*/
	
	public void migrate() {
		this.dbConnectionSrc.migrate( this.dbConnectionDest.getConnection(), this.dbConnectionDest.getDbName());
		
	}
	
	public void connect() {
		this.dbConnectionSrc.connect();
		this.dbConnectionDest.connect();
		
	}
	
	public void disconnect() {
		this.dbConnectionSrc.disconnect();
		this.dbConnectionDest.disconnect();
		System.out.println("Disconnected from dbs");
	}
	
		
	public void parseConfig()  {
		try {

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("C:\\Users\\rbyrne\\dataAnon\\DataAnon\\dataAnon.xsd")); 
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setSchema(schema);
			//.setEventHandler
			config = (Configuration) jaxbUnmarshaller.unmarshal(new File(this.configFile));
			System.out.println("config is parsed");
			this.dbConnectionSrc.setCreds(config.getUsers().getDbNamesrc(),config.getUsers().getUsersrc(),config.getUsers().getPasswrdsrc(),
						config.getUsers().getPortsrc(),config.getUsers().getUrlsrc());
			this.dbConnectionDest.setCreds(config.getUsers().getDbNamedest(),config.getUsers().getUserdest(),config.getUsers().getPasswrddest(),
						config.getUsers().getPortdest(),config.getUsers().getUrldest());

		} catch (JAXBException e) {
			e.printStackTrace();
			System.exit(1);
		  } catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
