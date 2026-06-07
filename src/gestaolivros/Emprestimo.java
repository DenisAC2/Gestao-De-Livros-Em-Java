package gestaolivros;

import java.time.LocalDate;

 /**
 * @brief Representa a transação de empréstimo de um exemplar.
 * Encapsula os dados do locatário e gerencia o controle temporal
 * da data de retirada e do prazo limite para a devolução do livro.
 * @author Denis
 */
public class Emprestimo {
    private String nomeLeitor;        /**< Nome completo do leitor que realizou o aluguel */
    private LocalDate dataEmprestimo; /**< Data em que o empréstimo foi registrado (gerada automaticamente) */
    private LocalDate dataDevolucao;  /**< Data limite calculada para a devolução do exemplar */
	
	/**
     * @brief Construtor da classe Emprestimo.
     * Registra a data atual do sistema como momento da retirada e calcula automaticamente
     * a data de devolução somando o prazo estipulado em dias.
     * @param nomeLeitor Nome da pessoa que está alugando o livro.
     * @param dias Quantidade de dias concedidos para o prazo de leitura.
     */
    public Emprestimo(String nomeLeitor, int dias) {
        this.nomeLeitor = nomeLeitor;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(dias);
    }
	
	/**
     * @brief Formata os dados resumidos do empréstimo para exibição em relatórios.
     * @return String formatada contendo o nome do leitor, data de retirada e data de entrega.
     */
    public String exibir() {
        return "Leitor: " + this.nomeLeitor + 
               " | Empréstimo: " + this.dataEmprestimo + 
               " | Devolução: " + this.dataDevolucao;
    }
    
	/**
     * @brief Recupera a data limite para a devolução do livro.
     * Método crucial utilizado pelo algoritmo de ordenação inteligente de devoluções.
     * @return LocalDate Objeto contendo a data máxima de devolução.
     */
    public LocalDate getDataDevolucao() {
        return this.dataDevolucao;
    }
}
