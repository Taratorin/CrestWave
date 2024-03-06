package ru.crestwavetech.crestwave.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.crestwavetech.crestwave.exception.ProcessException;
import ru.crestwavetech.crestwave.jaxb.Envelope;
import ru.crestwavetech.crestwave.util.Packager;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Socket;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConvertServiceImpl implements ConvertService {
    @Value("${tcp.dest.addr}")
    private String address;
    @Value("${tcp.dest.port}")
    private int port;

    @Override
    public String convertXml(String xml) {
        Envelope envelope = getEnvelope(xml);
        String json = getJson(envelope);
        byte[] dataToSend = Packager.getPackage(json);
        sendInSocket(dataToSend);
        return json;
    }

    private void sendInSocket(byte[] dataToSend) {
        try (Socket socket = new Socket(address, port)) {
            try (OutputStream out = socket.getOutputStream()) {
                StringBuilder hexData = new StringBuilder();
                for (byte b : dataToSend) {
                    hexData.append(String.format("%02X ", b));
                }
                log.info("Data to send in socket in HEX: " + hexData);
                out.write(dataToSend);
                log.info("Data successfully send in socket");
            }
        } catch (IOException e) {
            throw new ProcessException("Error while sending data in socket");
        }
    }

    private String getJson(Envelope envelope) {
        String json;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writeValueAsString(envelope);
            log.info("Object Envelope successfully serialized in JSON: " + json);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ProcessException("Serialization error while converting in JSON");
        }
        return json;
    }

    private Envelope getEnvelope(String xml) {
        Envelope envelope;
        StringReader reader = new StringReader(xml);
        try {
            JAXBContext context = JAXBContext.newInstance(Envelope.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            envelope = (Envelope) unmarshaller.unmarshal(reader);
            log.info("The parsing of the XML and the creation of an object of the Envelope were completed successfully");
        } catch (JAXBException e) {
            log.error(e.getMessage());
            throw new ProcessException("Couldn't convert incoming XML package");
        }
        return envelope;
    }
}