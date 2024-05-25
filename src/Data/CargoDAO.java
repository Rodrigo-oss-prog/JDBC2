package Data;

import Model.Cargo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO implements DAO<Cargo> {



    @Override
    public void save(Cargo c) {
        System.out.println("Salvando Cargo");
        String sql = "INSERT INTO cargo(idcargo, cargo ) values(?,?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            stmt.setInt(1, c.getIdCargo());
            stmt.setString(2, c.getNomeCargo());
            stmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }




    }

    @Override
    public void update(Cargo c) {
        System.out.println("Atualizando Cargo");
        String sql = "UPDATE cargo SET cargo =? WHERE idcargo =?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            stmt.setString(1, c.getNomeCargo());
            stmt.setInt(2, c.getIdCargo());
            stmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void delete(Cargo c) {
        System.out.println("Deletando Cargo");
        String sql = "DELETE FROM cargo WHERE idcargo =?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            stmt.setInt(1, c.getIdCargo());
            stmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Cargo findById(int id) {
        String sql = "SELECT * FROM cargo WHERE idcargo = ?";
        Cargo c = null;
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                c = new Cargo(rs.getInt("idcargo"), rs.getString("cargo"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Cargo> findAll() {

        String sql = "SELECT * FROM cargo";
        List<Cargo> cargos = new ArrayList<>();
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cargo c = new Cargo(rs.getInt("idcargo"), rs.getString("cargo"));
                cargos.add(c);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cargos;
    }
}
