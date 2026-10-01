package com.example.hormigacharlie_practicaDto.dto;

import java.util.List;

public class SimulacionDTO {

    private List<EstadoDTO> estados;  // Significa: Este DTO tiene una lista de objetos EstadoDTO."

    //Por ejemplo:
    //
    //SimulacionDTO
    //     │
    //     └── estados
    //          │
    //          ├── EstadoDTO 0
    //          ├── EstadoDTO 1
    //          ├── EstadoDTO 2
    //          ├── EstadoDTO 3
    //          └── EstadoDTO 4

    public List<EstadoDTO> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoDTO> estados) {
        this.estados = estados;
    }
}