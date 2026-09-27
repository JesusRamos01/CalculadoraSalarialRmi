package jesusramos.salario.lipermi.lib;

import java.io.Serializable;

/**
 * Datos que viajan entre el cliente y el servidor mediante LipeRMI.
 */
public class DatosSalario implements Serializable {

    private static final long serialVersionUID = 1L;
    private float horasTrabajadas;
    private float valorPorHora;
    private float resultado;
    private String mensaje;

    public DatosSalario() {
    }

    public DatosSalario(float horasTrabajadas, float valorPorHora) {
        this.horasTrabajadas = horasTrabajadas;
        this.valorPorHora = valorPorHora;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(float v) {
        horasTrabajadas = v;
    }

    public float getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(float v) {
        valorPorHora = v;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float v) {
        resultado = v;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String v) {
        mensaje = v;
    }
}
