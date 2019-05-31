package pe.kamwha.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.kamwha.dao.espec.ReservaDaoEspec;
import pe.kamwha.db.AccesoDB;
import pe.kamwha.model.ClienteModel;
import pe.kamwha.model.MesaModel;
import pe.kamwha.model.ReservaModel;

public class ReservaDaoImpl implements ReservaDaoEspec {

    @Override
    public List<ReservaModel> listar(String bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(ReservaModel bean) {
        AccesoDB cn = AccesoDB.saberEstado();
        PreparedStatement pst;

        try {
            pst = cn.getCn().prepareCall("{ Call InsertarReserva(?,?,?,?,?)}");

            pst.setString(1, bean.getReservaID());
            pst.setString(2, bean.getCliente().getClienteID());
            pst.setInt(3, bean.getMesa().getMesaNro());
            pst.setString(4, bean.getReservafecha());
            pst.setString(5, bean.getReservaStatus());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en Ingresar Reserva: " + ex);
        } finally {
            cn.cerrarConexion();
        }
    }

    @Override
    public void actualizar(ReservaModel bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(ReservaModel bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String generarCodigo() {
        AccesoDB cn = AccesoDB.saberEstado();
        String codigo = "";

        PreparedStatement pst;
        ResultSet rs = null;

        try {
            pst = cn.getCn().prepareCall("{ Call GenerarReservaID() }");

            rs = pst.executeQuery();

            while (rs.next()) {
                codigo = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error en Generar ID RESERVA: " + ex);
        } finally {
            cn.cerrarConexion();
        }

        return codigo;
    }

    public int validarExistenciaReserva(ReservaModel bean) {
        AccesoDB cn = AccesoDB.saberEstado();
        List<ReservaModel> lista = new ArrayList<ReservaModel>();
        int rows = 0;
        PreparedStatement pst;
        ResultSet rs = null;

        try {
            pst = cn.getCn().prepareCall("{ Call verificarExistenciaReserva(?,?) }");

            pst.setInt(1, bean.getMesa().getMesaNro());
            pst.setString(2, bean.getReservafecha());

            rs = pst.executeQuery();

            while (rs.next()) {
                rows = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en Generar ID RESERVA: " + ex);
        } finally {
            cn.cerrarConexion();
        }

        return rows;
        
    }

    @Override
    public ReservaModel mapRow(ResultSet rs) throws SQLException {
        ReservaModel bean = new ReservaModel();

        bean.setReservaID(rs.getString(1));
        bean.setCliente(new ClienteModel());
        bean.getCliente().setClienteID(rs.getString(2));
        bean.setMesa(new MesaModel());
        bean.getMesa().setMesaNro(rs.getInt(3));
        bean.setReservafecha(rs.getString(4));
        bean.setReservaStatus(rs.getString(5));

        return bean;
    }

}
