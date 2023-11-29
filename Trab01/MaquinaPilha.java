import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MaquinaPilha {

    private ArrayList<Integer> interpreter;
    private ArrayList<String> command;
    private int stack;
    private int registerOne;
    private int registerTwo;

    public MaquinaPilha(String nomeArquivo) {
        interpreter = new ArrayList<>();
        command = new ArrayList<>();
        stack = 0;
        registerOne = 0;
        registerTwo = 0;
        try {
            List<String> linhas = Files.readAllLines(Paths.get(nomeArquivo));
            for (String linha : linhas) {
                String[] partes = linha.split(" ");
                String comando = partes[0];
                String valor = (partes.length > 1) ? partes[1] : null;

                if (valor != null) {
                    interpreter.add(Integer.parseInt(valor));
                } else {
                    command.add(comando);
                }
            }
            System.out.printf("STACK %d", stack);
            System.out.println();
            for (int i = 0; i < command.size(); i++) {
                executarComando(command.get(i));
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo ou executar os comandos:\n" + e);
        }
    }

    private void executarComando(String comando) {
        int tam = interpreter.size();

        switch (comando) {
            case "SOMA":
                if (tam == 1) {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("SOMA %d %d", registerOne, stack);
                    System.out.println();
                    stack = registerOne + stack;
                } else {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    registerTwo = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("SOMA %d %d", registerTwo, registerOne);
                    System.out.println();
                    stack = registerTwo + registerOne + stack;
                }
                System.out.printf("STACK %d", stack);
                System.out.println();
                break;

            case "MULT":
                if (tam == 1) {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("MULT %d %d", registerOne, stack);
                    System.out.println();
                    stack = registerOne * stack;
                } else {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    registerTwo = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("MULT %d %d", registerTwo, registerOne);
                    System.out.println();
                    stack = (registerTwo * registerOne) + stack;
                }
                System.out.printf("STACK %d", stack);
                System.out.println();
                break;

            case "SUB":
                if (tam == 1) {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("SUB %d %d", registerOne, stack);
                    System.out.println();
                    stack = registerOne - stack;
                } else {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    registerTwo = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("SUB %d %d", registerTwo, registerOne);
                    System.out.println();
                    stack = (registerTwo - registerOne) + stack;
                }
                System.out.printf("STACK %d", stack);
                System.out.println();
                break;

            case "DIV":
                if (tam == 1) {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("DIV %d %d", registerOne, stack);
                    System.out.println();
                    stack = registerOne / stack;
                } else {
                    registerOne = interpreter.remove(interpreter.size() - 1);
                    registerTwo = interpreter.remove(interpreter.size() - 1);
                    System.out.printf("DIV %d %d", registerTwo, registerOne);
                    System.out.println();
                    stack = (registerTwo / registerOne) + stack;
                }
                break;

            case "PRINT":
                System.out.printf("STACK %d", stack);
                System.out.println();
                break;

            default:
                break;
        }
    }
}
