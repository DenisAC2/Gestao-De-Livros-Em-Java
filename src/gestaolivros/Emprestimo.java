package gestaolivros;

import java.time.LocalDate;
/**
 *
 * @author Denis
 */
public class Emprestimo {
    private String nomeLeitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(String nomeLeitor, int dias) {
        this.nomeLeitor = nomeLeitor;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(dias);
    }

    public String exibir() {
        return "Leitor: " + this.nomeLeitor + 
               " | Empréstimo: " + this.dataEmprestimo + 
               " | Devolução: " + this.dataDevolucao;
    }
    
    public LocalDate getDataDevolucao() {
        return this.dataDevolucao;
    }
}
