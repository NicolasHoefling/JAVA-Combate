package nicolashoefling.combate;

import java.util.Scanner;

public class Combate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coletando dados para o Campeão A
        System.out.println("Digite os dados do Primeiro campeão");
        System.out.print("Nome do Campeão A: ");
        String nomeA = scanner.nextLine();
        System.out.print("Vida do Campeão A: ");
        double vidaA = scanner.nextDouble();
        System.out.print("Ataque do Campeão A: ");
        double ataqueA = scanner.nextDouble();
        System.out.print("Armadura do Campeão A: ");
        double armaduraA = scanner.nextDouble();
        Campeao campeaoA = new Campeao(nomeA, vidaA, ataqueA, armaduraA);

        // Coletando dados para o Campeão B
        scanner.nextLine(); // Limpar o buffer
        System.out.println("\nDigite os dados do Segundo campeão");
        System.out.print("Nome do Campeão B: ");
        String nomeB = scanner.nextLine();
        System.out.print("Vida do Campeão B: ");
        double vidaB = scanner.nextDouble();
        System.out.print("Ataque do Campeão B: ");
        double ataqueB = scanner.nextDouble();
        System.out.print("Armadura do Campeão B: ");
        double armaduraB = scanner.nextDouble();
        Campeao campeaoB = new Campeao(nomeB, vidaB, ataqueB, armaduraB);

        // Coletando o número de rounds
        System.out.print("\nQuantos rounds você deseja executar? (máximo 5): ");
        int numeroDeRounds = scanner.nextInt();
        numeroDeRounds = Math.min(numeroDeRounds, 5); // Limita a 5 rounds

        iniciarCombate(campeaoA, campeaoB, numeroDeRounds);
    }

    public static void iniciarCombate(Campeao campeaoA, Campeao campeaoB, int numeroDeRounds) {
        for (int round = 1; round <= numeroDeRounds; round++) {
            // Campeão A ataca Campeão B
            campeaoB.receberDano(campeaoA.getAtaque());

            // Verifica se o Campeão B foi derrotado
            if (!campeaoB.estaVivo()) {
                System.out.println("\n--- Round " + round + " ---");
                System.out.println(campeaoB.getNome() + " ficou com 0 de HP - Morreu.");
                System.out.println(campeaoA.getNome() + " ficou com " + campeaoA.getVida() + " de HP");
                break;
            }

            // Campeão B ataca Campeão A
            campeaoA.receberDano(campeaoB.getAtaque());

            // Verifica se o Campeão A foi derrotado
            if (!campeaoA.estaVivo()) {
                System.out.println("\n--- Round " + round + " ---");
                System.out.println(campeaoA.getNome() + " ficou com 0 de HP - Morreu.");
                System.out.println(campeaoB.getNome() + " ficou com " + campeaoB.getVida() + " de HP");
                break;
            }

            // Mostra o status de cada campeão após o round
            System.out.println("\n--- Round " + round + " ---");
            System.out.println(campeaoA.status());
            System.out.println(campeaoB.status());
        }

        System.out.println("\n### FIM DO COMBATE ###");
    }

    public static class Campeao {
        private String nome;
        private double vida;
        private double ataque;
        private double armadura;

        public Campeao(String nome, double vida, double ataque, double armadura) {
            this.nome = nome;
            this.vida = vida;
            this.ataque = ataque;
            this.armadura = armadura;
        }

        public boolean estaVivo() {
            return vida > 0;
        }

        public void receberDano(double dano) {
            double danoRecebido = Math.max(dano - armadura, 0); // Calcula o dano, considerando a armadura
            vida -= danoRecebido;
            vida = Math.max(vida, 0); // Garante que a vida não fique negativa
        }

        public String status() {
            if (!estaVivo()) {
                return nome + " ficou com 0 de HP - Morreu";
            } else {
                return nome + " ficou com " + vida + " de HP";
            }
        }

        public double getAtaque() {
            return ataque;
        }

        public double getVida() {
            return vida;
        }

        public String getNome() {
            return nome;
        }
    }
}
