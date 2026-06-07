package gestaolivros;

import java.time.LocalDate;
/**
 *
 * @author Denis
 */
public class Livro {
    private int codigo;
    private String titulo;
    private String sinopse;
    private String autor;
    private double preco;
    private String categoria;
    private int quantidade;
    private Emprestimo[] filaEmprestimos = new Emprestimo[50];
    private int qtdEmprestados = 0;

    public Livro(String titulo, String sinopse, String autor, double preco, String categoria, int quantidade) {
        this.setCodigo(GenLivro.CODIGO); 
        GenLivro.CODIGO++;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.autor = autor;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }
    
    public String exibirLivro() {
        return "Código: " + this.codigo +
               "\nTítulo: " + this.titulo +
               "\nAutor: " + this.autor +
               "\nPreço: " + this.preco +
               "\nCategoria: " + this.categoria +
               "\nEstoque Disponível: " + this.quantidade +
               "\nExemplares Emprestados: " + this.qtdEmprestados;
    }
    
    public String relatorioEmprestimos() {
        if (this.qtdEmprestados == 0) {
            return "Nenhum exemplar do livro '" + this.titulo + "' está emprestado no momento.";
        }
        
        String relatorio = "Fila de empréstimos do livro '" + this.titulo + "':\n\n";
        for (int i = 0; i < this.qtdEmprestados; i++) {
            relatorio += "Exemplar " + (i + 1) + " -> " + this.filaEmprestimos[i].exibir() + "\n";
        }
        return relatorio;
    }
    
    public boolean emprestar(String nomeLeitor, int dias) { // Parâmetro adicionado
        if (this.quantidade > 0 && dias > 0) {
            this.quantidade--;
            
            // Passa o nome do leitor para o novo empréstimo
            this.filaEmprestimos[this.qtdEmprestados] = new Emprestimo(nomeLeitor, dias);
            this.qtdEmprestados++; 
            return true;
        }
        return false;
    }
    
    public boolean devolver() {
        if (this.qtdEmprestados > 0) {
            this.quantidade++;
            
            int indiceMenorData = 0;
            LocalDate menorData = this.filaEmprestimos[0].getDataDevolucao();
            
            for (int i = 1; i < this.qtdEmprestados; i++) {
                if (this.filaEmprestimos[i].getDataDevolucao().isBefore(menorData)) {
                    menorData = this.filaEmprestimos[i].getDataDevolucao();
                    indiceMenorData = i;
                }
            }
            
            for (int i = indiceMenorData; i < (this.qtdEmprestados - 1); i++) {
                this.filaEmprestimos[i] = this.filaEmprestimos[i + 1];
            }
            
            this.qtdEmprestados--;
            this.filaEmprestimos[this.qtdEmprestados] = null;
            
            return true;
        }
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if(preco >= 0){
            this.preco = preco;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }
    
    
}
