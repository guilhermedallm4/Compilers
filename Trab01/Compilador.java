import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Compilador {

    public static void main(String[] args) {
        ArvoreSintatica arv = null;

        try {
            AnaliseLexica al = new AnaliseLexica(args[0]);
            Parser as = new Parser(al);
            arv = as.parseProg();
            Interpretador interpretador = new Interpretador();
            int inter = interpretador.geraCodigo(arv);
            System.out.println(inter);
            CodeGen backend = new CodeGen();
            String codigo = backend.geraCodigo(arv);
			salvarCodigoEmArquivo("codigoGerado.txt", codigo);

        } catch (Exception e) {
            System.out.println("Erro de compilação:\n" + e);
        }
    }


	private static void salvarCodigoEmArquivo(String nomeArquivo, String codigo) {
        try (FileWriter fw = new FileWriter(nomeArquivo);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(codigo);

            System.out.println("Código salvo com sucesso no arquivo: " + nomeArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar o código no arquivo:\n" + e);
        }
    }
}