
package prueba;

import java.util.ArrayList;
import java.util.List;
import pe.kamwha.model.MenuModel;
import pe.kamwha.service.MenuService;



public class Probando {

    
    public static void main(String[] args) {
        List<MenuModel> lista = new ArrayList<MenuModel>();
        
        MenuService entrada = new MenuService();
        
        lista = entrada.listarEntrada("Bebida");
        
        for(MenuModel list:lista){
            System.out.println("Nombre: " + list.getMenuNombre() );
        }
        
    }
    
}
