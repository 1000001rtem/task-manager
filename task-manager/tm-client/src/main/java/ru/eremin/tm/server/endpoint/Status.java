
package ru.eremin.tm.server.endpoint;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for status.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="status"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ЗАПЛАНИРОВАНО"/&gt;
 *     &lt;enumeration value="В ПРОЦЕССЕ"/&gt;
 *     &lt;enumeration value="ГОТОВО"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "status")
@XmlEnum
public enum Status {

    ЗАПЛАНИРОВАНО("\u0417\u0410\u041f\u041b\u0410\u041d\u0418\u0420\u041e\u0412\u0410\u041d\u041e"),
    @XmlEnumValue("\u0412 \u041f\u0420\u041e\u0426\u0415\u0421\u0421\u0415")
    В_ПРОЦЕССЕ("\u0412 \u041f\u0420\u041e\u0426\u0415\u0421\u0421\u0415"),
    ГОТОВО("\u0413\u041e\u0422\u041e\u0412\u041e");
    private final String value;

    Status(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Status fromValue(String v) {
        for (Status c : Status.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
