package com.example.hormigacharlie_practicaDto.controller;

import com.example.hormigacharlie_practicaDto.dto.ConfiguracionDTO;
import com.example.hormigacharlie_practicaDto.dto.EstadoDTO;
import com.example.hormigacharlie_practicaDto.dto.SimulacionDTO;
import com.example.hormigacharlie_practicaDto.service.CharlieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CharlieController {

    private final CharlieService charlieService;

    public CharlieController(CharlieService charlieService) {
        this.charlieService = charlieService;
    }

    @GetMapping("/")
    public String mostrarFormulario(Model model) {

        //Creamos un DTO vacío y lo mandamos a Thymeleaf
        //Así nuestro HTML podrá trabajar con:
        //th:object="${configuracion}"
        model.addAttribute("configuracion", new ConfiguracionDTO());

        return "configuracion";
    }

    @PostMapping("/simular")
    public String iniciarSimulacion(ConfiguracionDTO configuracion, Model model) {

        //Enviamos la configuración al Service.
        SimulacionDTO estado = charlieService.simular(configuracion);

        //Aqui decimos: Guarda este objeto EstadoDTO en el Model con el nombre "estado".
        //Enviamos el resultado al HTML.
        model.addAttribute("estado", estado);

        return "resultado";
    }

}
