package ru.crestwavetech.crestwave.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class SendPayment {
    private String token;
    private String cardNumber;
    private String requestId;
    private double amount;
    private String currency;
    @XmlElement(name = "account", namespace = "wsapi:Utils")
    private Account[] accounts;
    private int page;
    @XmlElement(name = "field")
    private Field[] fields;
}