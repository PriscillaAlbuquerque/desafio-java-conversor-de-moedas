import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaConversao consulta = new ConsultaConversao();
        String apiKey = "9437b0b9d8aacb4e05607c98";
        ConsultaConversao.Conversao conversao = null;



        try{
            conversao = consulta.buscaConversao(apiKey);

        } catch (IOException e) {
            System.out.println(" Erro ao buscar conversao: " + e.getMessage());
            return;
        }

        String menu = """
                ** Seja bem vindo ao Conversor de Moedas. **
                ** Selecione a opção desejada:
                1 - Dólar =>> Peso argentino
                2 - Peso argentino =>> Dólar
                3 - Dólar =>> Real Brasileiro
                4 - Real brasileiro =>> Dólar
                5 - Dólar =>> Peso Colombiano
                6 - Peso colombiano =>> Dólar
                7 - Sair
                Escolha uma opção válida.
                """;

        while (true) {
            System.out.println(menu);
            int opcao = scanner.nextInt();
            if (opcao == 7) {
                System.out.println("Saindo do conversor. Até mais");
                break;
            }

            switch (opcao) {
                case 1:
                    exibirTaxa(conversao.conversion_rates, "USD", "ARS");
                    break;
                case 2:
                    exibirTaxa(conversao.conversion_rates, "ARS", "USD");
                    break;
                case 3:
                    exibirTaxa(conversao.conversion_rates, "USD", "BRL");
                    break;
                case 4:
                    exibirTaxa(conversao.conversion_rates, "BRL", "USD");
                    break;
                case 5:
                    exibirTaxa(conversao.conversion_rates, "USD", "COP");
                    break;
                case 6:
                    exibirTaxa(conversao.conversion_rates, "COP", "USD");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirTaxa(Map<String, Double> taxas, String moedaOrigem, String moedaDestino) {
        if (taxas.containsKey(moedaOrigem) && taxas.containsKey(moedaDestino)) {
            double taxa = taxas.get(moedaDestino) / taxas.get(moedaOrigem);
            System.out.printf("A taxa de conversão de %s para %s é: %.4f%n", moedaOrigem, moedaDestino, taxa);
        } else {
            System.out.println("Uma das moedas especificadas não foi encontrada.");
        }
            }
        }




