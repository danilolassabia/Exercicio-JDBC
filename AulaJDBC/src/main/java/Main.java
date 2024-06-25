import java.math.BigDecimal;

public class Main{
    public static void main(String[] args) {
        Database.getConnection();
        //System.out.println(Database.insertEndereco("Rua Itapura","Apartamento","Apto 82","São Paulo","SP","01878745","158","Tatuapé"));
        //System.out.println(Database.insertCategoria("Lançamento", BigDecimal.valueOf(25.5)));
        //System.out.println(Database.insertGenero("Animação"));
        //System.out.println(Database.insertFilme("Avengers: Endgame","Vingadores: Ultimato",20,1,1));
        //Database.getEnderecos();
        //Database.getCategorias();
        //Database.getGeneros();
        //Database.getFilmes();
        Database.disconnect();
    }
}
