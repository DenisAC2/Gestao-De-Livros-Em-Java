package gestaolivros;
/**
 *
 * @author Denis
 */
public class GenLivro {
    static public int CODIGO = 0;
    private Livro livros[];
    private int tamanho;
    private int id;
    
    public GenLivro(int tamanho){
        this.tamanho = tamanho;
        this.livros = new Livro[this.tamanho];
        this.id = 0;
    }
    
    public boolean adicionarLivro(Livro livro) {
        if (this.id < this.tamanho && livro != null) {
            this.livros[this.id] = livro;
            ++this.id;
            return true;
        }
        return false;
    }
    
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
    
    public Livro buscarLivro(int codigo) {
        for (int i = 0; i < this.id; ++i) {
            if (this.livros[i].getCodigo() == codigo) {
                return this.livros[i];
            }
        }
        return null;
    }
    
    public boolean excluirLivro(int codigo) {
        for (int i = 0; i < this.id; i++) {
            if (this.livros[i].getCodigo() == codigo) {
                if (i < (this.id - 1)) {
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
    
    
    
    public boolean registrarEmprestimo(int codigo, String nomeLeitor, int diasAlugados) {
        Livro livroEncontrado = buscarLivro(codigo);
        
        if (livroEncontrado != null) {
            // Repassa o nome do leitor para o livro
            return livroEncontrado.emprestar(nomeLeitor, diasAlugados);
        }
        return false;
    }
    
    public boolean registrarDevolucao(int codigo) {
        Livro livroEncontrado = buscarLivro(codigo);
        if (livroEncontrado != null) {
            return livroEncontrado.devolver();
        }
        return false;
    }
    
    public String verFilaDeEmprestimos(int codigo) {
        Livro livroEncontrado = buscarLivro(codigo);
        if (livroEncontrado != null) {
            return livroEncontrado.relatorioEmprestimos();
        }
        return "AVISO: Livro não encontrado no sistema.";
    }
}

