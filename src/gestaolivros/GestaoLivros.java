package gestaolivros;

import javax.swing.JOptionPane;

 /**
 * @brief Ponto de entrada (Main) e interface de usuário do sistema de gestão.
 * Esta classe é responsável por renderizar a interface gráfica via caixas de diálogo
 * do JOptionPane, capturar as entradas do usuário, realizar o parse de dados básicos
 * e rotear as ações para o controlador GenLivro.
 * @author Denis
 */
public class GestaoLivros {
	
	/**
     * @brief Método principal que inicia o ciclo de vida do aplicativo.
     * Instancia o gerenciador de livros com uma capacidade fixa e executa o menu interativo
     * baseado em uma estrutura switch-case dentro de um loop condicional de controle.
     * @param args Argumentos de linha de comando padrão do Java (não utilizados).
     */
    public static void main(String[] args) {
        GenLivro genLivro = new GenLivro(10);

        Livro livro;
        int quantidade, busca, dias;
        String titulo, sinopse, autor, categoria;
        double preco;
        boolean rodando = true;

        while (rodando) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                                                     Sistema de Gestão de Livros:
                                                                     1 - Inserir livro
                                                                     2 - Listar livros
                                                                     3 - Buscar livro
                                                                     4 - Excluir livro
                                                                     5 - Atualizar livro
                                                                     6 - Registrar Empréstimo
                                                                     7 - Registrar Devolução
                                                                     8 - Ver fila de empréstimos de um livro
                                                                     0 - Sair
                                                                     Selecione uma opção:"""));
            switch (opcao) {
                case 1:
                    titulo = JOptionPane.showInputDialog("Digite o título do livro:");
                    sinopse = JOptionPane.showInputDialog("Digite a sinopse:");
                    autor = JOptionPane.showInputDialog("Digite o autor do livro:");
                    preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do livro:"));
                    categoria = JOptionPane.showInputDialog("Digite a categoria do livro:");
                    quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade inicial em estoque:"));
                    
                    livro = new Livro(titulo, sinopse, autor, preco, categoria, quantidade);
                    
                    boolean adicionou = genLivro.adicionarLivro(livro);
                    
                    if (adicionou) {
                        JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "ERRO: O limite de livros do sistema foi atingido!");
                    }
                    break;
                    
                case 2:
                    JOptionPane.showMessageDialog(null, genLivro.listarLivros());
                    break;
                    
                case 3:
                    busca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro para busca:"));
                    
                    livro = genLivro.buscarLivro(busca);
                    if (livro == null) {
                        JOptionPane.showMessageDialog(null, "Livro não encontrado!");
                    } else {
                        JOptionPane.showMessageDialog(null, livro.exibirLivro());
                    }
                    break;
                    
                case 4:
                    busca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro para excluir:"));
                    
                    boolean excluiu = genLivro.excluirLivro(busca);
                    if (excluiu) {
                        JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "AVISO: Nenhum livro encontrado com o código " + busca);
                    }
                    break;
                    
                case 5:
                    busca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro que deseja editar:"));
                    livro = genLivro.buscarLivro(busca);
                    
                    if (livro != null) {
                        JOptionPane.showMessageDialog(null, "Editando o livro:\n" + livro.getTitulo());
                        
                        titulo = JOptionPane.showInputDialog("Digite o novo título:", livro.getTitulo());
                        sinopse = JOptionPane.showInputDialog("Digite a nova sinopse:", livro.getSinopse());
                        autor = JOptionPane.showInputDialog("Digite o novo autor:", livro.getAutor());
                        preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo preço:", livro.getPreco()));
                        categoria = JOptionPane.showInputDialog("Digite a nova categoria:", livro.getCategoria());
                        
                        genLivro.atualizarLivro(busca, titulo, sinopse, autor, preco, categoria);
                        JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "ERRO: Livro não encontrado no sistema.");
                    }
                    break;
                    
                case 6:
                    busca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro a ser alugado:"));
                    String nomeLeitor = JOptionPane.showInputDialog("Digite o nome do leitor:");
                    dias = Integer.parseInt(JOptionPane.showInputDialog("Por quantos dias será o empréstimo?"));
                    
                    boolean sucessoEmprestimo = genLivro.registrarEmprestimo(busca, nomeLeitor, dias);
                    if (sucessoEmprestimo) {
                        JOptionPane.showMessageDialog(null, "Empréstimo registrado para " + nomeLeitor + "!");
                    } else {
                        JOptionPane.showMessageDialog(null, "FALHA: Livro não encontrado ou sem estoque.");
                    }
                    break;
                    
                case 7:
                    busca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro para devolução:"));
                    
                    boolean sucessoDevolucao = genLivro.registrarDevolucao(busca);
                    if (sucessoDevolucao) {
                        livro = genLivro.buscarLivro(busca);
                        JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso! O livro mais antigo foi devolvido.\n\n" + livro.exibirLivro());
                    } else {
                        JOptionPane.showMessageDialog(null, "FALHA: O livro não foi encontrado ou não há exemplares emprestados.");
                    }
                    break;
                    
                case 8:
                    busca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro para ver os exemplares alugados:"));
                    String relatorio = genLivro.verFilaDeEmprestimos(busca);
                    JOptionPane.showMessageDialog(null, relatorio);
                    break;
                    
                case 0:
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                    rodando = false;
                    break;
            }
        }
    }  
    
}
