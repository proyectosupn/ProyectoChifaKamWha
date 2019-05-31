
package prueba;

import pe.kamwha.model.ClienteModel;
import pe.kamwha.model.MesaModel;
import pe.kamwha.model.ReservaModel;
import pe.kamwha.service.ReservaService;
public class PruebaReserva {

    
    public static void main(String[] args) {
        ReservaService reservaService = new ReservaService();
        
        String codigoReserva = reservaService.generarCodigo();
        String codigoCliente = "CI0001";
        int codigoMesa = 1;
        String fecha = "2019-05-07";
        String status = "RESERVADO";
        
        MesaModel beanMesa = new MesaModel();
        beanMesa.setMesaNro(codigoMesa);
        ClienteModel beanCliente = new ClienteModel();
        beanCliente.setClienteID(codigoCliente);
        ReservaModel beanReserva = new ReservaModel();
        beanReserva.setReservaID(codigoReserva);
        beanReserva.setCliente(beanCliente);
        beanReserva.setMesa(beanMesa);
        beanReserva.setReservafecha(fecha);
        beanReserva.setReservaStatus(status);
        
        int rows = reservaService.validarReserva(beanReserva);
            
            if(rows == 0){
                reservaService.insertar(beanReserva);
                System.out.println("Se inserto la reserva");
            }else{
                System.out.println("Ya hay reserva para esa mesa ese dia y hora");
            }

    }
    
}
