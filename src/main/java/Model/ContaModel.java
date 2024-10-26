package Model;

import DAO.ContaDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContaModel {

    private int cd_conta;
    private double nr_saldo;
    private String id_email;
    private Date dt_abertura;
    private String st_conta;
    private String senha;
    private String nm_usuario;
    private Date dt_nasc;

    public int getCd_conta() {
        return cd_conta;
    }

    public void setCd_conta(int cd_conta) {
        this.cd_conta = cd_conta;
    }

    public double getNr_saldo() {
        return nr_saldo;
    }

    public void setNr_saldo(int nr_saldo) {
        this.nr_saldo = nr_saldo;
    }

    public String getId_email() {
        return id_email;
    }

    public void setId_email(String id_email) {
        this.id_email = id_email;
    }

    public Date getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(Date dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public String getSt_conta() {
        return st_conta;
    }

    public void setSt_conta(String st_conta) {
        this.st_conta = st_conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public Date getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public List<ContaModel> getAll() throws SQLException {
        ContaDAO contaDAO = new ContaDAO();

        return new ArrayList<ContaModel>(contaDAO.getAll());
    }

    public ContaModel(int cd_conta, double nr_saldo, String id_email) {
        this.cd_conta = cd_conta;
        this.nr_saldo = nr_saldo;
        this.id_email = id_email;
    }

    public ContaModel(ContaBuilder conta) {
        this.cd_conta = conta.cd_conta;
        this.nr_saldo = conta.nr_saldo;
        this.id_email = conta.id_email;
        this.dt_abertura = conta.dt_abertura;
        this.st_conta = conta.st_conta;
        this.senha = conta.senha;
        this.nm_usuario = conta.nm_usuario;
        this.dt_nasc = conta.dt_nasc;
    }

    public ContaModel() {
    }

    public void cadastrarConta() throws SQLException {
        try{
            ContaDAO contaDao = new ContaDAO();
            contaDao.cadastrar(this);
        }catch(Exception e){
            e.getMessage();
        }
    }

    public ContaModel buscaPorId(int id) throws SQLException {
        ContaDAO contaDao = new ContaDAO();
        return contaDao.buscarPorId(id);

    }
    public void excluirPorId(int id) throws SQLException {
        ContaDAO contaDao = new ContaDAO();
        contaDao.exluirPorId(id);
    }
    public void alterar() throws SQLException {
        ContaDAO contaDao = new ContaDAO();
        contaDao.alterar(this);
    }

}

