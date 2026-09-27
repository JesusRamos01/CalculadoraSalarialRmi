package jesusramos.salario.lipermi.net;

import jesusramos.salario.lipermi.lib.DatosSalario;
import jesusramos.salario.lipermi.lib.IRemotaCalculoSalario;

public class CalculoRmiSalarioImplem implements IRemotaCalculoSalario {

    @Override
    public DatosSalario calcularSalario(DatosSalario datos) {
        if (datos.getHorasTrabajadas() <= 0 || datos.getValorPorHora() <= 0) {
            datos.setMensaje("ERROR: Las horas trabajadas y el valor por hora deben ser mayores que 0");
            return datos;
        }
        datos.setResultado(datos.getHorasTrabajadas() * datos.getValorPorHora());
        datos.setMensaje("El salario total a pagar es: " + datos.getResultado());
        return datos;
    }
}
