# Sistema de Gestão de Livros 📚

Um sistema de gerenciamento de biblioteca desenvolvido em **Java**, focado na aplicação prática de **Programação Orientada a Objetos (POO)** e gestão de memória em tempo de execução. O sistema interage com o usuário de forma intuitiva através de caixas de diálogo do `JOptionPane`.

## 🚀 Funcionalidades

- **CRUD Completo:** Adicionar, listar, buscar, atualizar e excluir livros do catálogo.
- **Controle de Estoque:** Gerenciamento automático da quantidade de exemplares disponíveis no momento das transações.
- **Sistema de Empréstimos Avançado:**
  - Registro de aluguéis atrelados ao nome do leitor e ao prazo de entrega (em dias).
  - Geração de relatório da fila de exemplares alugados para um livro específico.
  - Devolução inteligente: o algoritmo identifica e processa a devolução do exemplar que possui a data de entrega mais antiga.

## 🛠️ Tecnologias e Conceitos Utilizados

- **Java (JDK):** Linguagem base do projeto.
- **POO:** Forte uso de encapsulamento, atributos estáticos, arrays de objetos e manipulação de datas com `LocalDate`.
- **Java Swing (`JOptionPane`):** Utilizado para a interface de entrada e saída de dados.
