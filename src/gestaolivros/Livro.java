package gestaolivros;

import java.time.LocalDate;
 /**
 * @brief Representa a entidade Livro no sistema de gestão.
 * Armazena as informações de catálogo, preço, estoque e controla
 * o histórico de exemplares alugados.
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
	
	/**
     * @brief Construtor principal da classe Livro.
     * O código do livro é gerado automaticamente baseado na constante global da classe GenLivro.
     */
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
    
	/**
     * @brief Formata os dados completos do livro para exibição.
     * @return String formatada contendo todas as propriedades do catálogo e estado do estoque.
     */
    public String exibirLivro() {
        return "Código: " + this.codigo +
               "\nTítulo: " + this.titulo +
               "\nAutor: " + this.autor +
               "\nPreço: " + this.preco +
               "\nCategoria: " + this.categoria +
               "\nEstoque Disponível: " + this.quantidade +
               "\nExemplares Emprestados: " + this.qtdEmprestados;
    }
    
	/**
     * @brief Gera um relatório detalhado de todos os exemplares atualmente alugados.
     * @return String com a lista de leitores e datas, ou um aviso se não houver empréstimos.
     */
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
    
	/**
     * @brief Realiza o empréstimo de um exemplar do livro para um leitor.
     * Decrementa a quantidade em estoque e adiciona um novo registro na fila.
     * * @param nomeLeitor Nome da pessoa que está alugando o livro.
     * @param dias Quantidade de dias concedidos para a devolução.
     * @return true se o empréstimo for bem-sucedido (com estoque e dias válidos), false caso contrário.
     */
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
    
	/**
     * @brief Processa a devolução do exemplar alugado há mais tempo.
     * O algoritmo varre a fila de empréstimos buscando a data de devolução mais antiga (isBefore)
     * e remove este registro da fila, reajustando os índices do array e incrementando o estoque.
     * * @return true se a devolução for processada, false se não houver exemplares emprestados.
     */
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
	
	/**
     * @brief Atualiza o preço do livro.
     * Possui validação interna que ignora valores negativos.
     * @param preco O novo valor de venda.
     */
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
	
	/**
     * @brief Atualiza a quantidade de exemplares no estoque.
     * Possui validação interna que ignora valores negativos.
     * @param quantidade O novo número de itens em estoque.
     */
    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }
    
    
}
