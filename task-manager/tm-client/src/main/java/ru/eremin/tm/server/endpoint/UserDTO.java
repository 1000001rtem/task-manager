package ru.eremin.tm.server.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userDTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="userDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="hashPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="role" type="{http://endpoint.server.tm.eremin.ru/}role" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDTO", propOrder = {
        "id",
        "createDate",
        "login",
        "hashPassword",
        "role"
})
public class UserDTO {

    protected String id;
    protected String createDate;
    protected String login;
    protected String hashPassword;
    @XmlSchemaType(name = "string")
    protected Role role;

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the createDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCreateDate(String value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the login property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the hashPassword property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * Sets the value of the hashPassword property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHashPassword(String value) {
        this.hashPassword = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return possible object is
     * {@link Role }
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value allowed object is
     *              {@link Role }
     */
    public void setRole(Role value) {
        this.role = value;
    }

}
