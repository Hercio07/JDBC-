package controller;
import dao.DiscoCompactoDAO;
import java.util.ArrayList;
import Model.DiscoCompacto;
import java.sql.SQLException;

public class DiscoCompactoController {

    private DiscoCompactoDAO dao;

    public DiscoCompactoController() {
        this.dao = new DiscoCompactoDAO();
    }

    public void adicionarDisco(DiscoCompacto disco) {
        dao.criar(disco);
    }

    public ArrayList<DiscoCompacto> listarDiscos() {
        return dao.listarDiscos();
    }

    public void atualizarDisco(DiscoCompacto disco) throws SQLException {
        dao.AtualizarDisco(disco);
    }

    public void removerDisco(int codigo) {
        dao.delete(codigo);
    }
}