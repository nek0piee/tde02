package Jogo;

import java.util.Scanner;

// pilha que usamos no último TDE
public class Pilha {  
  private Node Topo;

  public Pilha() {
    this.Topo = null;
  }

  public int getInformacao(){
    return Topo.getInformacao();
  }

  // inserir elementos
  public void inserir(int informacao) {
    // declarando um novo node
    Node no = new Node();

    no.setInformacao(informacao);
    if (Topo == null) { // se o topo for nulo define ele como novo nó
      Topo = no;
    } else {
      no.setProximo(Topo);
      Topo = no;
    }
  }

  // remoção do último elemento
  public Integer remove() {
    Node topo = Topo; // variável topo que aponta para o nó no topo
    Integer informacaoRemovida = topo.getInformacao();
    
    Topo = topo.getProximo();
    topo = null;

    return informacaoRemovida; // return a info removida
  }

  // imprimir elementos
  public void imprime() {
    Node atual = Topo;

    if(atual == null){
      System.out.println("Vazia");
    }
      
    else{
      while (atual != null) {
      System.out.print(atual.getInformacao() + " / ");
      atual = atual.getProximo();
      }

      System.out.println(" ");
    }
  }

    // criação do método para ordenação crescente
  public boolean isSortedAscending(int stackSize){
    int countingElements = 1;  // criação da variável como 1

    // 
    if(Topo == null || Topo.getProximo() == null){ 
      return false; // verifica se a pilha ta vazia/unico elemento, assim já estaria ordenada
    }

    Node atual = Topo;

    while(atual.getProximo() != null){  // 
      
      // 
      if(atual.getInformacao() > atual.getProximo().getInformacao()){
        return false; // compara o elemento atual com o próximo elemento
      }
      atual = atual.getProximo(); // atual para o próximo nó
      countingElements++;
    }
    System.out.println("Contagem de elementos: " + countingElements);

    // 
    return countingElements == stackSize; // return 
}


  // criação do método para ordenação decrescente
  public boolean isSortedDescending(int stackSize){
    int countingElements = 1;  // 

    // 
    if(Topo == null || Topo.getProximo() == null){ 
      return false;
    }

    Node atual = Topo;

    while(atual.getProximo() != null){  // 
      
      // 
      if(atual.getInformacao() < atual.getProximo().getInformacao()){
        return false;
      }
      atual = atual.getProximo();
      countingElements++;
    }
  
    return countingElements == stackSize;
}

  // método main e a "mini interface" do código para o usuário
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Pilha pilha = new Pilha();

    System.out.println("Digite os itens da lista (digite '0' para parar):");
    int item;
    while (true) {
      item = scanner.nextInt();
      if (item == 0) {
        break;
      }
      pilha.inserir(item);
    }
    scanner.close();

    System.out.println("Lista Encadeada:");
    pilha.imprime();
    pilha.remove();
    System.out.println("Pilha após remover o primeiro elemento:");
    pilha.imprime();

  }
}
