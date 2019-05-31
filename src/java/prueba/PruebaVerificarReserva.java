
package prueba;

import pe.kamwha.model.MesaModel;
import pe.kamwha.model.ReservaModel;
import pe.kamwha.service.ReservaService;


public class PruebaVerificarReserva {

    public static void main(String[] args) {
        ReservaModel reserva = new ReservaModel();
        ReservaService service = new ReservaService();
        reserva.setMesa(new MesaModel());
        reserva.getMesa().setMesaNro(1);
        reserva.setReservafecha("2019-05-13");
        
        System.out.println("Numero Bandera: " + service.validarReserva(reserva));
    }
    
}
