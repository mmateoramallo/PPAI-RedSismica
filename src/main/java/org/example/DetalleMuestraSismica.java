package org.example;

import org.example.modelos.TipoDeDato;

public class DetalleMuestraSismica {

    private TipoDeDato tipoDeDato;

    private Integer valor;

    public TipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }

    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public DetalleMuestraSismica() {
    }

    public DetalleMuestraSismica(TipoDeDato tipoDeDato, Integer valor) {
        this.tipoDeDato = tipoDeDato;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "DetalleMuestraSismica{" +
                "tipoDeDato=" + tipoDeDato +
                ", valor=" + valor +
                '}';
    }
}
