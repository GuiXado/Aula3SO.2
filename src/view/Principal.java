package view;

import javax.swing.JOptionPane;
import controller.KillController;

public class Principal {

	public static void main(String[] args) {
		KillController kill = new KillController();
        int opc = 0;

        while (opc != 9) {
            opc = Integer.parseInt(JOptionPane.showInputDialog(
                "MENU\n"
                + "1 - Listar Processos\n"
                + "2 - Matar por PID\n"
                + "3 - Matar por Nome\n"
                + "9 - Finalizar"
            ));

            switch (opc) {
                case 1:
                    kill.listaProcessos();
                    break;
                case 2:
                    String pid = JOptionPane.showInputDialog("Digite o PID do processo:");
                    kill.mataPid(pid);
                    break;
                case 3:
                    String nome = JOptionPane.showInputDialog("Digite o nome do processo:");
                    kill.mataNome(nome);
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Fim!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
	}
}