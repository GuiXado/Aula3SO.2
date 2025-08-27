package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	// ex1 identificar o SO
    private String os() {
        return System.getProperty("os.name");
    }

    // ex2 Lista de processos ativos
    public void listaProcessos() {
        String comando;

        if (os().contains("Windows")) {
            comando = "TASKLIST /FO TABLE";
            //System.out.println(os());
        } else if (os().contains("Linux")) {
            comando = "ps -ef";
        } else {
            System.out.println("Sistema Operacional não encontrado");
            return;
        }

        try {
            Process p = Runtime.getRuntime().exec(comando);
            InputStream fluxo = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leitor);

            String linha = buffer.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = buffer.readLine();
            }

            buffer.close();
            leitor.close();
            fluxo.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // ex3 Mata processo por PID
    public void mataPid(String pid) {
        String comando;

        if (os().contains("Windows")) {
            comando = "TASKKILL /PID " + pid;
        } else if (os().contains("Linux")) {
            comando = "kill -9 " + pid;
        } else {
            System.out.println("Sistema Operacional não encontrado");
            return;
        }

        callProcess(comando);
    }

    // ex4 Mata processo por nome
    public void mataNome(String nome) {
        String comando;

        if (os().contains("Windows")) {
            comando = "TASKKILL /IM " + nome;
        } else if (os().contains("Linux")) {
            comando = "pkill -f " + nome;
        } else {
            System.out.println("Sistema Operacional não encontrado");
            return;
        }

        callProcess(comando);
    }

    // método auxiliar que chama o processo e trata erro 740 do Windows
    private void callProcess(String proc) {
        String[] procArr = proc.split(" ");
        try {
            Runtime.getRuntime().exec(procArr);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.err.println(msg);
            if (msg.contains("740")) {
                try {
                    String[] procArr2 = ("cmd /c " + proc).split(" ");
                    Runtime.getRuntime().exec(procArr2);
                } catch (Exception e1) {
                    System.err.println(e1.getMessage());
                }
            }
        }
    }
}
