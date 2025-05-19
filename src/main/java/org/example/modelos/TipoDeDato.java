package org.example.modelos;

public class TipoDeDato {
    private String denominacion;

    private String nombreUnidadMedida;

    private Integer valorUmbral;

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Integer getValorUmbral() {
        return valorUmbral;
    }

    public void setValorUmbral(Integer valorUmbral) {
        this.valorUmbral = valorUmbral;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public TipoDeDato(String denominacion, Integer valorUmbral, String nombreUnidadMedida) {
        this.denominacion = denominacion;
        this.valorUmbral = valorUmbral;
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public TipoDeDato() {
    }

    @Override
    public String toString() {
        return "TipoDeDato{" +
                "denominacion='" + denominacion + '\'' +
                ", nombreUnidadMedida='" + nombreUnidadMedida + '\'' +
                ", valorUmbral=" + valorUmbral +
                '}';
    }
}
