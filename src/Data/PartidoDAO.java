package Data;

import Model.Partido;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO implements DAO<Partido> {

    @Override
    public void save(Partido p) {
        String sql = "INSERT INTO partido values (?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, p.getNumero());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getSigla());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Partido p) {
        String sql = "UPDATE partido SET  nome =?, sigla =? WHERE numero =?";
        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){

            stmt.setString(1, p.getNome());
            stmt.setString(2,p.getSigla());
            stmt.setInt(3, p.getNumero());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(Partido p) {
        String sql = "DELETE FROM partido WHERE numero =?";
        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, p.getNumero());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Partido findById(int id) {
        String sql = "SELECT * FROM partido WHERE numero =?";
        Partido p = null;
        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                p = new Partido(rs.getInt("numero"),rs.getString("nome"), rs.getString("sigla") );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public List<Partido> findAll() {
        String sql = "SELECT * FROM partido";
        List<Partido> partidos = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Partido p  = new Partido(rs.getInt("numero"),rs.getString("nome"),
                        rs.getString("sigla") );
                partidos.add(p);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return partidos;
    }
}
