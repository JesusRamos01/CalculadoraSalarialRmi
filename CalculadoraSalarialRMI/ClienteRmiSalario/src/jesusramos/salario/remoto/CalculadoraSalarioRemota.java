package jesusramos.salario.remoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import jesusramos.salario.modelo.CalculoSalario;

public interface CalculadoraSalarioRemota extends Remote {

    CalculoSalario.Salario calcularSalario(float horasTrabajadas, float valorPorHora)
            throws RemoteException;
}
