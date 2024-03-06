package ru.crestwavetech.crestwave.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.crestwavetech.crestwave.model.ConverterResult;
import ru.crestwavetech.crestwave.service.ConvertService;


@Controller
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final ConvertService service;

    @GetMapping("web/interface")
    public String showInterface() {
        return "interface";
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.OK)
    public ConverterResult processXml(@RequestBody String xml, HttpServletRequest request) {
        log.info("Получен пакет XML по {} ", request.getRequestURI());
        return service.convertXml(xml);
    }
}