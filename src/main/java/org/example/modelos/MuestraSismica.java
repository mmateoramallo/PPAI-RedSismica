package org.example.modelos;

import java.util.ArrayList;

public class MuestraSismica {

    private ArrayList<DetalleMuestraSismica> detalleMuestraSismica;

    private String fechaHoraMuestra;

    public ArrayList<DetalleMuestraSismica> getDetalleMuestraSismica() {
        return detalleMuestraSismica;
    }

    public void setDetalleMuestraSismica(ArrayList<DetalleMuestraSismica> detalleMuestraSismica) {
        this.detalleMuestraSismica = detalleMuestraSismica;
    }

    public String getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }

    public void setFechaHoraMuestra(String fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public MuestraSismica(ArrayList<DetalleMuestraSismica> detalleMuestraSismica, String fechaHoraMuestra) {
        this.detalleMuestraSismica = detalleMuestraSismica;
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public MuestraSismica() {
    }

    @Override
    public String toString() {
        return "MuestraSismica{" +
                "detalleMuestraSismica=" + detalleMuestraSismica +
                ", fechaHoraMuestra='" + fechaHoraMuestra + '\'' +
                '}';
    }
}
