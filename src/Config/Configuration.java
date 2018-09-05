//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.31 at 04:13:44 PM EDT 
//


package Config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="users">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="usersrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="passwrdsrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="portsrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="urlsrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="userdest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="passwrddest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="portdest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="urldest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="table" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tablename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="column" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "users",
    "table"
})
@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElement(required = true)
    protected Configuration.Users users;
    @XmlElement(required = true)
    protected List<Configuration.Table> table;

    /**
     * Gets the value of the users property.
     * 
     * @return
     *     possible object is
     *     {@link Configuration.Users }
     *     
     */
    public Configuration.Users getUsers() {
        return users;
    }

    /**
     * Sets the value of the users property.
     * 
     * @param value
     *     allowed object is
     *     {@link Configuration.Users }
     *     
     */
    public void setUsers(Configuration.Users value) {
        this.users = value;
    }

    /**
     * Gets the value of the table property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the table property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Configuration.Table }
     * 
     * 
     */
    public List<Configuration.Table> getTable() {
        if (table == null) {
            table = new ArrayList<Configuration.Table>();
        }
        return this.table;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="tablename" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="column" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tablename",
        "column"
    })
    public static class Table {

        @XmlElement(required = true)
        protected String tablename;
        @XmlElement(required = true)
        protected List<String> column;

        /**
         * Gets the value of the tablename property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTablename() {
            return tablename;
        }

        /**
         * Sets the value of the tablename property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTablename(String value) {
            this.tablename = value;
        }

        /**
         * Gets the value of the column property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the column property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getColumn() {
            if (column == null) {
                column = new ArrayList<String>();
            }
            return this.column;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="usersrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="passwrdsrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="portsrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="urlsrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="userdest" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="passwrddest" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="portdest" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="urldest" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
    	"dbnamesrc",
        "usersrc",
        "passwrdsrc",
        "portsrc",
        "urlsrc",
        "dbnamedest",
        "userdest",
        "passwrddest",
        "portdest",
        "urldest"
    })
    public static class Users {

    	@XmlElement(required = true)
        protected String dbnamesrc;
        @XmlElement(required = true)
        protected String usersrc;
        @XmlElement(required = true)
        protected String passwrdsrc;
        @XmlElement(required = true)
        protected String portsrc;
        @XmlElement(required = true)
        protected String urlsrc;
        @XmlElement(required = true)
        protected String dbnamedest;
        @XmlElement(required = true)
        protected String userdest;
        @XmlElement(required = true)
        protected String passwrddest;
        @XmlElement(required = true)
        protected String portdest;
        @XmlElement(required = true)
        protected String urldest;

        /**
         * Gets the value of the usersrc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsersrc() {
            return usersrc;
        }

        /**
         * Sets the value of the usersrc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsersrc(String value) {
            this.usersrc = value;
        }

        /**
         * Gets the value of the passwrdsrc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPasswrdsrc() {
            return passwrdsrc;
        }

        /**
         * Sets the value of the passwrdsrc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPasswrdsrc(String value) {
            this.passwrdsrc = value;
        }

        /**
         * Gets the value of the portsrc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortsrc() {
            return portsrc;
        }

        /**
         * Sets the value of the portsrc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortsrc(String value) {
            this.portsrc = value;
        }

        /**
         * Gets the value of the urlsrc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUrlsrc() {
            return urlsrc;
        }

        /**
         * Sets the value of the urlsrc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUrlsrc(String value) {
            this.urlsrc = value;
        }

        /**
         * Gets the value of the userdest property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUserdest() {
            return userdest;
        }

        /**
         * Sets the value of the userdest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUserdest(String value) {
            this.userdest = value;
        }

        /**
         * Gets the value of the passwrddest property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPasswrddest() {
            return passwrddest;
        }

        /**
         * Sets the value of the passwrddest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPasswrddest(String value) {
            this.passwrddest = value;
        }

        /**
         * Gets the value of the portdest property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPortdest() {
            return portdest;
        }

        /**
         * Sets the value of the portdest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPortdest(String value) {
            this.portdest = value;
        }

        /**
         * Gets the value of the urldest property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUrldest() {
            return urldest;
        }

        /**
         * Sets the value of the urldest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUrldest(String value) {
            this.urldest = value;
        }
        
        public String getDbNamesrc() {
            return dbnamesrc;
        }

        /**
         * Sets the value of the urldest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDbNamesrc(String value) {
            this.dbnamesrc = value;
        }
        public String getDbNamedest() {
            return dbnamedest;
        }

        /**
         * Sets the value of the urldest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDbNamedest(String value) {
            this.dbnamedest = value;
        }

    }

}
