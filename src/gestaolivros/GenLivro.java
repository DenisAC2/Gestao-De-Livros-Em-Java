package gestaolivros;
/**
 *
 * @author Denis
 */
 /**
 * @brief Gerenciador e controlador do catálogo de livros do sistema.
 * * Esta classe centraliza as operações de CRUD (Criar, Ler, Atualizar e Excluir)
 * utilizando um array estático de objetos, controlando manualmente os limites de 
 * armazenamento e delegando a lógica de logística para as instâncias de Livro.
 * @author Denis
 */
public class GenLivro {
	/** @brief Contador estático para geração de IDs únicos e auto-incrementais. */
    static public int CODIGO = 0;
    private Livro livros[]; /**< Array que armazena as referências dos livros cadastrados */
    private int tamanho;    /**< Capacidade máxima de armazenamento alocada para o vetor */
    private int id;         /**< Índice atual e contador de livros ativos no sistema */
    
	/**
     * @brief Construtor da classe de gerenciamento.
     * Inicializa o vetor de livros com o tamanho fixo especificado.
     * @param tamanho Capacidade máxima de livros que o sistema suportará.
     */
    public GenLivro(int tamanho){
        this.tamanho = tamanho;
        this.livros = new Livro[this.tamanho];
        this.id = 0;
    }
    
	/**
     * @brief Insere um novo livro no vetor de armazenamento.
     * Verifica se há espaço disponível no array e se o objeto é válido antes de inserir.
     * @param livro Objeto do tipo Livro a ser adicionado ao catálogo.
     * @return true se o livro foi inserido com sucesso, false se o sistema atingiu o limite de capacidade.
     */
    public boolean adicionarLivro(Livro livro) {
        if (this.id < this.tamanho && livro != null) {
            this.livros[this.id] = livro;
            ++this.id;
            return true;
        }
        return false;
    }
    
	/**
     * @brief Atualiza as propriedades de um livro existente.
     * Localiza o livro pelo código e, caso encontre, altera os seus dados usando os setters.
     * @param codigo Código identificador do livro a ser editado.
     * @param novoTitulo Novo título para o livro.
     * @param novaSinopse Nova sinopse para o livro.
     * @param novoAutor Novo autor para o livro.
     * @param novoPreco Novo preço de venda.
     * @param novaCategoria Nova categoria de classificação.
     * @return true se o livro foi encontrado e atualizado, false caso contrário.
     */
    public boolean atualizarLivro(int codigo, String novoTitulo, String novaSinopse, String novoAutor, double novoPreco, String novaCategoria) {
        Livro livro = buscarLivro(codigo);
        
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setSinopse(novaSinopse);
            livro.setAutor(novoAutor);
            livro.setPreco(novoPreco);
            livro.setCategoria(novaCategoria);
            return true;
        }
        return false;
    }
    
	/**
     * @brief Percorre o catálogo e compila os dados de todos os livros ativos.
     * @return String formatada com o relatório de todos os livros, ou um aviso se o sistema estiver vazio.
     */
    public String listarLivros() {
        if (this.id == 0) {
            return "AVISO: Não há nenhum livro cadastrado no sistema no momento.";
        }

        String conteudo = "";
        for (int i = 0; i < this.id; ++i) {
            conteudo += this.livros[i].exibirLivro() + "\n-----------------\n";
        }
        
        return conteudo;
    }
    
	/**
     * @brief Realiza uma busca linear no array utilizando o código identificador.
     * @param codigo Código do livro desejado.
     * @return Livro* Referência para o objeto Livro encontrado, ou null se não existir.
     */
    public Livro buscarLivro(int codigo) {
        for (int i = 0; i < this.id; ++i) {
            if (this.livros[i].getCodigo() == codigo) {
                return this.livros[i];
            }
        }
        return null;
    }
    
	/**
     * @brief Remove um livro do catálogo baseado no seu código.
     * Localiza o item e rearranja o array estático movendo todos os elementos 
     * seguintes uma posição para a esquerda (shift), evitando buracos nulos na memória.
     * @param codigo Código do livro a ser excluído.
     * @return true se a exclusão e o rearranjo foram concluídos, false se o livro não foi encontrado.
     */
    public boolean excluirLivro(int codigo) {
        for (int i = 0; i < this.id; i++) {
            if (this.livros[i].getCodigo() == codigo) {
                if (i < (this.id - 1)) {
					// Algoritmo de Shift para reajustar o array contiguamente
                    for (int j = i; j < (this.id - 1); ++j) {
                        this.livros[j] = this.livros[j + 1];
                    }
                }
                this.id--;
                return true;
            }
        }
        return false;
    }
    
    /**
     * @brief Registra o empréstimo de um livro para um leitor.
     * Localiza o livro e repassa a responsabilidade de criação do empréstimo para a instância.
     * @param codigo Código do livro a ser alugado.
     * @param nomeLeitor Nome do cliente que está realizando o aluguel.
     * @param diasAlugados Prazo de devolução concedido em dias.
     * @return true se o processo de delegação foi concluído com sucesso, false se falhar.
     */
    public boolean registrarEmprestimo(int codigo, String nomeLeitor, int diasAlugados) {
        Livro livroEncontrado = buscarLivro(codigo);
        
        if (livroEncontrado != null) {
            // Repassa o nome do leitor para o livro
            return livroEncontrado.emprestar(nomeLeitor, diasAlugados);
        }
        return false;
    }
    
	/**
     * @brief Processa o retorno de um exemplar ao estoque.
     * @param codigo Código do livro que está sendo devolvido.
     * @return true se o livro existir e possuir empréstimos ativos para processar a devolução.
     */
    public boolean registrarDevolucao(int codigo) {
        Livro livroEncontrado = buscarLivro(codigo);
        if (livroEncontrado != null) {
            return livroEncontrado.devolver();
        }
        return false;
    }
    
	/**
     * @brief Consulta o histórico e a fila de espera de locações de um título específico.
     * @param codigo Código do livro para consulta.
     * @return String contendo o relatório detalhado da fila ou mensagem de aviso.
     */
    public String verFilaDeEmprestimos(int codigo) {
        Livro livroEncontrado = buscarLivro(codigo);
        if (livroEncontrado != null) {
            return livroEncontrado.relatorioEmprestimos();
        }
        return "AVISO: Livro não encontrado no sistema.";
    }
}

