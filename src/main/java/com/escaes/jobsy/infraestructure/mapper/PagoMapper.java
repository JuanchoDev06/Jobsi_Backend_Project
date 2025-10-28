package com.escaes.jobsy.infraestructure.mapper;

import com.escaes.jobsy.application.dto.pago.PagoResponse;
import com.escaes.jobsy.domain.model.TipoPago;
import com.escaes.jobsy.infraestructure.entity.TipoPagoEntity;

public class PagoMapper {

    public static TipoPago toDomain(TipoPagoEntity pagoEntity) {
        if (pagoEntity == null) {
            return null;
        }
        return new TipoPago(
                pagoEntity.getId(),
                pagoEntity.getNombre()
        );
    }
    public static TipoPagoEntity toEntity(TipoPago pago) {
        if (pago == null) {
            return null;
        }
        return new TipoPagoEntity(
                pago.id(),
                pago.nombrePago()
        );
    }
    public static PagoResponse toResponse(TipoPago pago) {
        if (pago == null) {
            return null;
        }
        return new PagoResponse(
                pago.nombrePago()
        );
    }
}