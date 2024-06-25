import org.postgresql.util.PGmoney;

import java.math.BigDecimal;
import java.sql.*;

public class Database {
    private static String url = "jdbc:postgresql://localhost:5432/locadora";
    private static String user = "postgres";
    private static String password = "postgres";
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to PostgreSQL database");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void getEnderecos() {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from endereco order by cod_end");
            ResultSet rs = ps.executeQuery();
            System.out.println("Código\t\tLogradouro\t\tTipo\t\tComplemento\t\tCidade\t\tUF\t\tCEP\t\tNúmero\t\tBairro");
            while (rs.next()) {
                System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
                        rs.getInt("cod_end"),
                        rs.getString("logradouro"),
                        rs.getString("tipo_log"),
                        rs.getString("complemento"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("numero"),
                        rs.getString("bairro"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCategorias() {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from categoria order by cod_cat");
            ResultSet rs = ps.executeQuery();
            System.out.println("Código\t\tNome\t\tValor");
            while (rs.next()) {
                System.out.printf("%d\t\t%s\t\t%s\n",
                        rs.getInt("cod_cat"),
                        rs.getString("nome"),
                        rs.getString("valor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getGeneros() {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from genero order by cod_gen");
            ResultSet rs = ps.executeQuery();
            System.out.println("Código\t\tNome");
            while (rs.next()) {
                System.out.printf("%d\t\t%s\n",
                        rs.getInt("cod_gen"),
                        rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getFilmes() {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from filmes order by cod_filme");
            ResultSet rs = ps.executeQuery();
            System.out.println("Código\t\tTítulo Original\t\tTítulo\t\tQuantidade\t\tCódigo Categoria\t\tCódigo Gênero");
            while (rs.next()) {
                System.out.printf("%d\t\t%s\t\t%s\t\t%d\t\t%d\t\t%d\n",
                        rs.getInt("cod_filme"),
                        rs.getString("titulo_original"),
                        rs.getString("titulo"),
                        rs.getInt("quantidade"),
                        rs.getInt("fk_cod_cat"),
                        rs.getInt("fk_cod_gen"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertEndereco(String logradouro, String tipoLog, String complemento, String cidade, String uf, String cep, String numero, String bairro) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into endereco (logradouro,tipo_log,complemento,cidade,uf,cep,numero,bairro) values (?,?,?,?,?,?,?,?)");
            ps.setString(1, logradouro);
            ps.setString(2, tipoLog);
            ps.setString(3, complemento);
            ps.setString(4, cidade);
            ps.setString(5, uf);
            ps.setString(6, cep);
            ps.setString(7, numero);
            ps.setString(8, bairro);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertCategoria(String nome, BigDecimal valor) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into categoria (nome,valor) values (?,?)");
            ps.setString(1, nome);
            ps.setBigDecimal(2, valor);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertGenero(String nome) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into genero (nome) values (?)");
            ps.setString(1, nome);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertFilme(String tituloOriginal, String titulo, int quantidade, int fkCodCat, int fkCodGen) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into filmes (titulo_original,titulo,quantidade,fk_cod_cat,fk_cod_gen) values (?,?,?,?,?)");
            ps.setString(1, tituloOriginal);
            ps.setString(2, titulo);
            ps.setInt(3, quantidade);
            ps.setInt(4, fkCodCat);
            ps.setInt(5, fkCodGen);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        try {
            if (con != null || !con.isClosed()) {
                con.close();
                System.out.println("Disconnected from PostgreSQL database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}