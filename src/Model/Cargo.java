package Model;

public class Cargo {
    private int idcargo;
    private String nomeCargo;

    public Cargo(int idcargo, String nomeCargo) {
        this.idcargo = idcargo;
        this.nomeCargo = nomeCargo;
    }

    public int getIdCargo() {
        return idcargo;
    }

    public void setIdCargo(int idCargo) {
        this.idcargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
    @Override
    public String toString() {
        return "Cargo{" +
                "idCargo=" + idcargo +
                ", nomeCargo='" + nomeCargo + '\'' +
                '}';
    }
}
