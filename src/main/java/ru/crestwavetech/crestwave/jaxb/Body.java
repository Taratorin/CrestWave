package ru.crestwavetech.crestwave.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class EnvelopeBody {
    @XmlElement(name = "sendPayment", namespace = "wsapi:Payment")
    private SendPayment sendPayment;
}
