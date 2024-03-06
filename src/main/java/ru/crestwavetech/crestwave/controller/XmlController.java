package ru.crestwavetech.crestwave.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.crestwavetech.crestwave.service.ConvertService;


@Controller
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class XmlController {
    private final ConvertService service;

    @GetMapping("/web/interface")
    public String showInterface() {
        return "interface";
    }

    @PostMapping("/web/submit")
    @ResponseStatus(HttpStatus.OK)
    public String processXml(@RequestParam("xml") String xml, HttpServletRequest request, Model model) {
        log.info("Получен пакет XML по {} ", request.getRequestURI());
        String result = service.convertXml(xml);
        model.addAttribute("result", result);
        return "result";
    }
}