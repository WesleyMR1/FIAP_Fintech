package Model;

import Impl.OracleTransferenciaDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transferencias {
    private int id_transferencia;
    private int id_cartao;
    private int cd_conta;
    private String tipo_transferencia;
    private Date data_transferencia;
    private Double valor_transferencia;
    private String nome_transferencia;

    public Transferencias(int id_transferencia, String tipo_transferencia, Date data_transferencia, Double valor_transferencia, String nome_transferencia, int id_cartao, int cd_conta) {
        this.id_transferencia = id_transferencia;
        this.id_cartao = id_cartao;
        this.cd_conta = cd_conta;
        this.tipo_transferencia = tipo_transferencia;
        this.data_transferencia = data_transferencia;
        this.valor_transferencia = valor_transferencia;
        this.nome_transferencia = nome_transferencia;
    }

    public Transferencias() {
    }

    public Transferencias(TransferenciaBuilder transferenciaBuilder) {
        this.id_transferencia = transferenciaBuilder.id_transferencia;
        this.id_cartao = transferenciaBuilder.id_cartao;
        this.tipo_transferencia = transferenciaBuilder.tipo_transferencia;
        this.data_transferencia = transferenciaBuilder.data_transferencia;
        this.valor_transferencia = transferenciaBuilder.valor_transferencia;
        this.nome_transferencia = transferenciaBuilder.nome_transferencia;
        this.cd_conta = transferenciaBuilder.cd_conta;

    }

    @Override
    public String toString() {
        return "Transferencia { " +
                "Id_transferência: " + id_transferencia +
                ", Id_cartao: " + id_cartao +
                ", Tipo: " + tipo_transferencia +
                ", Data: " + data_transferencia +
                ", Valor: " + valor_transferencia +
                ", Nome: " + nome_transferencia +
                " }";
    }

    public int getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(int id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public int getCd_conta() {
        return cd_conta;
    }

    public void setCd_conta(int cd_conta) {
        this.cd_conta = cd_conta;
    }

    public String getTipo_transferencia() {
        return tipo_transferencia;
    }

    public void setTipo_transferencia(String tipo_transferencia) {
        this.tipo_transferencia = tipo_transferencia;
    }

    public Date getData_transferencia() {
        return data_transferencia;
    }

    public void setData_transferencia(Date data_transferencia) {
        this.data_transferencia = data_transferencia;
    }

    public Double getValor_transferencia() {
        return valor_transferencia;
    }

    public void setValor_transferencia(Double valor_transferencia) {
        this.valor_transferencia = valor_transferencia;
    }

    public String getNome_transferencia() {
        return nome_transferencia;
    }

    public void setNome_transferencia(String nome_transferencia) {
        this.nome_transferencia = nome_transferencia;
    }

    public void adicionarTransferencia() throws SQLException {
        OracleTransferenciaDAO oracleTransferenciaDAO = new OracleTransferenciaDAO();
        oracleTransferenciaDAO.cadastrarTrans(this);
    }

    public List<Transferencias> getLista(int id) throws SQLException {
        OracleTransferenciaDAO oracleTransferenciaDAO = new OracleTransferenciaDAO();
        return new ArrayList<Transferencias>(oracleTransferenciaDAO.buscar(id));
    }

    public double faturaMesAnterior(int cd_conta, int id_cartao) {
        OracleTransferenciaDAO oracleTransferenciaDAO = new OracleTransferenciaDAO();
        return oracleTransferenciaDAO.faturaMesAnterior(cd_conta, id_cartao);

    }
}
