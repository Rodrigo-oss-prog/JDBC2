import Data.CandidatoDAO;
import Data.CargoDAO;
import Data.PartidoDAO;
import Model.Candidato;
import Model.Cargo;
import Model.Partido;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Partidos!");
        Cargo cargo1 = new Cargo(1, "Vereador");
        Cargo cargo2 = new Cargo(2, "Presidente");
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.save(cargo1);
        cargoDAO.save(cargo2);



        Partido partido1 = new Partido(2235, "Partido do Java", "PJ");
        Partido partido2 = new Partido(2236, "Partido do Python", "PP");
        PartidoDAO partidoDAO = new PartidoDAO();
        partidoDAO.save(partido1);
        partidoDAO.save(partido2);

        Candidato candidato1 = new Candidato(22356, "Jonas", cargo1, partido1);
        Candidato candidato2 = new Candidato(22357, "Maria", cargo2, partido2);
        CandidatoDAO candidatoDAO = new CandidatoDAO();
        candidatoDAO.save(candidato1);
        candidatoDAO.save(candidato2);

        List<Candidato> candidatos = candidatoDAO.findAll();
        for(Candidato candidato : candidatos)
            System.out.println(candidato);



        List<Partido> partidos = partidoDAO.findAll();
        for(Partido partido : partidos)
            System.out.println(partido);


        List<Cargo> cargos = cargoDAO.findAll();
        for(Cargo cargo : cargos)
            System.out.println(cargo);




    }
}