package jesusramos.salario.modelo;

import java.io.Serializable;


public class CalculoSalario implements Serializable {

    private static final long serialVersionUID = 1L;

    private float horasTrabajadas;
    private float valorPorHora;

    public static class Salario implements Serializable {

        private static final long serialVersionUID = 1L;
        public float resultado;
        public String mensaje;
    }

    public CalculoSalario() {
    }

    public CalculoSalario(float horasTrabajadas, float valorPorHora) {
        this.horasTrabajadas = horasTrabajadas;
        this.valorPorHora = valorPorHora;
    }

    public Salario getSalario() {
        Salario salario = new Salario();
        if (horasTrabajadas <= 0 || valorPorHora <= 0) {
            salario.mensaje = "ERROR: Las horas trabajadas y el valor por hora deben ser mayores que 0";
        } else {
            salario.resultado = horasTrabajadas * valorPorHora;
            salario.mensaje = "El salario total a pagar es: " + salario.resultado;
        }
        return salario;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(float horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public float getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(float valorPorHora) {
        this.valorPorHora = valorPorHora;
    }
}
