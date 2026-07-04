package com.fantasi.fantasia.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaRequestDTO {
    private Long idCliente;
    private String metodoPago;
    private List<DetalleVentaRequestDTO> detalles;
}