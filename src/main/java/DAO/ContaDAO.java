package DAO;


import Model.ContaBuilder;
import Model.ContaModel;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private Connection connect;

    public ContaDAO() throws SQLException {

    }
    public Connection abrirConexao(){
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        return connectionManager.getConnection();

    }

    public void cadastrar(ContaModel conta) throws SQLException {
        Connection conectar = this.abrirConexao();
        String sql = "INSERT INTO t_conta (nr_saldo, id_email, st_conta, senha, nm_usuario, dt_abertura, dt_nasc) VALUES (0, ?, ?, ?, ?,?, ?)";

        //

        try (PreparedStatement stm = conectar.prepareStatement(sql)) {
            stm.setString(1, conta.getId_email());
            stm.setString(2, "Ativo");
            stm.setString(3, conta.getSenha());
            stm.setString(4, conta.getNm_usuario());
            // Para pegar a data atual
            java.sql.Date dt_abertura = new java.sql.Date(conta.getDt_abertura().getTime());

            // Convertendo para java.sql.Date
            stm.setDate(5, dt_abertura);

            // Para a data de nascimento
            Date dt_nasc = new java.sql.Date(conta.getDt_nasc().getTime());
            stm.setDate(6, dt_nasc);

            stm.executeUpdate();

            conectar.close();

        }
    }
    public ContaModel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_conta WHERE cd_conta = ?";
        ContaModel conta = null;

        Connection conectar = this.abrirConexao();
             PreparedStatement stm = conectar.prepareStatement(sql);

            stm.setInt(1, id);
            try (ResultSet resultado = stm.executeQuery()) {
                if (resultado.next()) {
                    ContaBuilder contaBuilder = new ContaBuilder();
                    conta = new ContaBuilder()
                            .CdConta(resultado.getInt("cd_conta"))
                            .NrSaldo(resultado.getDouble("nr_saldo"))
                            .IdEmail(resultado.getString("id_email"))
                            .DtAbertura(resultado.getDate("dt_abertura"))
                            .StConta(resultado.getString("st_conta"))
                            .Senha(resultado.getString("senha"))
                            .NmUsuario(resultado.getString("nm_usuario"))
                            .DtNasc(resultado.getDate("dt_nasc"))
                            .build();
                }
            }

        return conta;
    }


    public List<ContaModel> getAll() throws SQLException {
        Connection conectar = this.abrirConexao();
        PreparedStatement stm = conectar.prepareStatement("SELECT * FROM t_conta");
        ResultSet resultado = stm.executeQuery();
        List<ContaModel> lista = new ArrayList<>();
        while (resultado.next()){
            Integer cd_conta = resultado.getInt("cd_conta");
            Double nr_saldo = resultado.getDouble("nr_saldo");
            String id_email = resultado.getString("id_email");
            Date dt_abertura = resultado.getDate("dt_abertura");
            String st_conta = resultado.getString("st_conta");
            String senha = resultado.getString("senha");
            String nm_usuario = resultado.getString("nm_usuario");
            Date dt_nasc = resultado.getDate("dt_nasc");

            ContaModel nv_conta = new ContaBuilder()
                    .CdConta(cd_conta)
                    .NrSaldo(nr_saldo)
                    .IdEmail(id_email)
                    .DtAbertura(dt_abertura)
                    .StConta(st_conta)
                    .Senha(senha)
                    .NmUsuario(nm_usuario)
                    .DtNasc(dt_nasc)
                    .build();

            lista.add(nv_conta);
        }

        return lista;
    }

    public void exluirPorId(int id) throws SQLException {
        String sql = "delete FROM T_conta WHERE cd_conta = ?";
        Connection conectar = this.abrirConexao();
        PreparedStatement stm = conectar.prepareStatement(sql);

        stm.setInt(1, id);
        try{
            stm.executeQuery();
        }catch(SQLException e){
            e.getMessage();
        }

    }
    public void alterar(ContaModel conta) throws SQLException {
        Connection conectar = this.abrirConexao();
        String sql = "update T_conta set nr_saldo = ?, id_email = ?, st_conta = ?, senha = ?, nm_usuario = ?, dt_abertura = ?, dt_nasc = ? where cd_conta = ?";

        //

        try (PreparedStatement stm = conectar.prepareStatement(sql)) {

            stm.setDouble(1, conta.getNr_saldo());
            stm.setString(2, conta.getId_email());
            stm.setString(3, conta.getSt_conta());
            stm.setString(4, conta.getSenha());
            stm.setString(5, conta.getNm_usuario());
            // Para pegar a data atual
            java.sql.Date dt_abertura = new java.sql.Date(conta.getDt_abertura().getTime());
            // Convertendo para java.sql.Date
            stm.setDate(6, dt_abertura);
            // Para a data de nascimento
            Date dt_nasc = new java.sql.Date(conta.getDt_nasc().getTime());
            stm.setDate(7, dt_nasc);
            stm.setInt(8, conta.getCd_conta());

            stm.executeQuery();

            conectar.close();

        }
    }

}
