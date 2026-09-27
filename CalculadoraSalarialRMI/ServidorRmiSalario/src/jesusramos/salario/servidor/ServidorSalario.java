package jesusramos.salario.servidor;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import jesusramos.salario.modelo.CalculoSalario;
import jesusramos.salario.remoto.CalculadoraSalarioRemota;
import jesusramos.salario.vistas.VentanaPrincipal;

public class ServidorSalario extends Thread {

    private volatile boolean estado;
    private int puerto = 9007;
    private Registry registro;
    private CalculadoraSalarioRemota servicioRemoto;
    private VentanaPrincipal ventana;

    public ServidorSalario(Integer puerto, VentanaPrincipal v) {
        if (puerto != null && puerto != 0) {
            this.puerto = puerto;
        }
        ventana = v;
    }

    @Override
    public void run() {
        try {
            registro = LocateRegistry.createRegistry(puerto);
            servicioRemoto = new ServicioSalario(ventana);
            registro.rebind("CalculadoraSalario", servicioRemoto);
            estado = true;
            ventana.getBtnIniciar().setText("DETENER");
            ventana.getTxtEstado().setText("ONLINE");
            ventana.getTxtEstado().setForeground(Color.GREEN);
            ventana.getBtnIniciar().setForeground(Color.RED);
            escribirLog("Servidor RMI disponible en el puerto " + puerto);
        } catch (RemoteException ex) {
            escribirLog("ERROR al iniciar el registro RMI en el puerto " + puerto + ": " + ex.getMessage());
            ventana.getBtnIniciar().setText("INICIAR");
            ventana.getTxtEstado().setText("OFF LINE");
        }
    }

    public void detenerServicio() {
        if (!estado) {
            return;
        }
        estado = false;
        try {
            registro.unbind("CalculadoraSalario");
            UnicastRemoteObject.unexportObject(servicioRemoto, true);
            UnicastRemoteObject.unexportObject(registro, true);
            escribirLog("Servidor RMI detenido");
        } catch (Exception ex) {
            escribirLog("ERROR al detener el servidor RMI: " + ex.getMessage());
        }
        ventana.getBtnIniciar().setText("INICIAR");
        ventana.getBtnIniciar().setForeground(Color.GREEN);
        ventana.getTxtEstado().setText("OFF LINE");
        ventana.getTxtEstado().setForeground(Color.RED);
    }

    public String log() {
        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date()) + " - ";
    }

    private void escribirLog(String mensaje) {
        System.out.println(log() + mensaje);
        ventana.getCajaLog().append(log() + mensaje + "\n");
    }

    private static class ServicioSalario extends UnicastRemoteObject implements CalculadoraSalarioRemota {

        private final VentanaPrincipal ventana;

        ServicioSalario(VentanaPrincipal ventana) throws RemoteException {
            this.ventana = ventana;
        }

        @Override
        public CalculoSalario.Salario calcularSalario(float horasTrabajadas, float valorPorHora)
                throws RemoteException {
            CalculoSalario datosSalario = new CalculoSalario(horasTrabajadas, valorPorHora);
            CalculoSalario.Salario salario = datosSalario.getSalario();
            String log = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date())
                    + " - RMI -> HORAS: " + horasTrabajadas + ", VALOR POR HORA: " + valorPorHora
                    + ", SALARIO: " + salario.resultado + ", MENSAJE: " + salario.mensaje;
            System.out.println(log);
            ventana.getCajaLog().append(log + "\n");
            return salario;
        }
    }
}
