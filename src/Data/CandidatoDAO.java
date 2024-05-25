package Data;

import Model.Candidato;
import Model.Cargo;
import Model.Partido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO implements  DAO<Candidato>{

    @Override
    public void save(Candidato c) {
        System.out.println("Salvando Candidato");
        String sql = "INSERT INTO candidato (numero, nome,cargo, partido) VALUES (?,?,?,?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, c.getNumero());
            stmt.setString(2, c.getNome());
            stmt.setInt(3, c.getCargo().getIdCargo());
            stmt.setInt(4, c.getPartido().getNumero());
            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Candidato c) {
        System.out.println("Atualizando Candidato");
        String sql = "UPDATE candidato SET  nome =?, partido =?, cargo =? WHERE numero =?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){

            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getPartido().getNumero());
            stmt.setInt(3, c.getCargo().getIdCargo());
            stmt.setInt(4, c.getNumero());

            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Candidato c) {
        System.out.println("Deletando Candidato");
        String sql = "DELETE FROM candidato WHERE numero =?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){

            stmt.setInt(1, c.getNumero());

            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Candidato findById(int id) {
        System.out.println("Buscando Candidato");
        String sql = "SELECT * FROM candidato WHERE numero =?";
        Candidato candidato = null;
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                Partido partido = new PartidoDAO().findById(rs.getInt("partido"));
                Cargo cargo = new CargoDAO().findById(rs.getInt("cargo"));

                 candidato = new Candidato(rs.getInt("numero"), rs.getString("nome"),
                         cargo, partido);



            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return candidato;
    }

    @Override
    public List<Candidato> findAll() {
        System.out.println("Buscando Candidato");
        String sql = "SELECT * FROM candidato";
        List<Candidato> candidatos = new ArrayList<>();
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cargo cargo = new CargoDAO().findById(rs.getInt("cargo"));
                Partido partido = new PartidoDAO().findById(rs.getInt("partido"));

                Candidato candidato = new Candidato(rs.getInt("numero"),
                        rs.getString("nome"), cargo,
                        partido);

                candidatos.add(candidato);



            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return candidatos;

    }
}
