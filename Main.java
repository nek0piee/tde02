// Lucas Bertoncello, Laura da Rocha, Antonio Arruda
// BCC 4B 

import java.util.Scanner;
import java.awt.SystemColor;
import java.util.Random;
import Jogo.Pilha;

class Main {

  private static Pilha stack1;
  private static Pilha stack2;
  private static Pilha stack3;
  private static int stackSize;
  private static int orderChoice;
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Escolha o tamanho das pilhas: ");
    stackSize = scanner.nextInt();
      
    stack1 = fillWithRandomNumbers();
    stack2 = new Pilha();
    stack3 = new Pilha();
   
    printStacks(stack1,stack2,stack3);
    printMenu();

    Integer countMovedPeaces = 0;
    
    while (true) {

      int escolha = scanner.nextInt();
      
      switch (escolha) {
        case 0:
          System.out.println("Saiu do jogo");
          System.exit(0);
          break;
        case 1:
          System.out.println("Escolha a ordem para verificar");
          System.out.println("1 - Crescente");
          System.out.println("2 - Decrescente");
          orderChoice = scanner.nextInt();
          if (verifyOrderedStacks(stack1, stack2, stack3) == false){
            // movimenta os elementos
            moveDisk(countMovedPeaces); 
          }
          
          break;
        case 2:
          System.out.println("entrou no case 2"); 
          automaticResolution();
          System.out.println("Nova ordenação das pilhas: ");
          printStacks(stack1,stack2,stack3);
          System.exit(0);
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
        }
      }
    }

  public static void moveDisk(Integer countMovedPeaces){

    if (verifyOrderedStacks(stack1, stack2, stack3) == false){
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Digite o número da pilha de origem (1, 2 ou 3): ");
      int origin = scanner.nextInt();
      Pilha originStack = getStackByNumber(origin);
      
      System.out.print("Digite o número da pilha de destino (1, 2 ou 3): ");
      int destiny = scanner.nextInt();
      Pilha destinyStack = getStackByNumber(destiny);
      
      try{
        // retorna um node
        int movedObject = originStack.remove();
        System.out.println(movedObject);
        destinyStack.inserir(movedObject);
        countMovedPeaces++;
        printStacks(stack1,stack2,stack3);
        System.out.println("Peças movidas:" + countMovedPeaces);
        moveDisk(countMovedPeaces);
      }
      catch (NullPointerException e) {
        System.out.println("Escolha outra pilha de origem, pois essa está vazia :) /n");
        moveDisk(countMovedPeaces);
      }
      
    } else{
      System.out.println("O jogo foi vencido com " + countMovedPeaces +  " movimentos." );
    }
    
  }
  
  // inicia com o preenchimento da pilha 1 com números aleatórios na faixa de 1 a 100
  public static Pilha fillWithRandomNumbers(){
    Random random = new Random();
    Pilha stack = new Pilha();

    for(int i=0; i<stackSize; i++){
      int aleatoryNumber = random.nextInt(101);
      stack.inserir(aleatoryNumber);
    }
    return stack;
  }

  public static void printStacks(Pilha p1,Pilha p2,Pilha p3){
    System.out.println("----------------------");
    System.out.println("Pilha 1: ");
    p1.imprime();

    System.out.println("----------------------");
    System.out.println("Pilha 2: ");
    p2.imprime();

    System.out.println("----------------------");
    System.out.println("Pilha 3: ");
    p3.imprime();
  }

  public static void printMenu(){
    System.out.println("----------------------");
    System.out.println("Escolha uma opção:");
    System.out.println("0 - Sair do jogo");
    System.out.println("1 - Movimentar");
    System.out.println("2 - Solução automática");
  }
  
  public static Pilha getStackByNumber(int number) {
    switch(number) {
      case 1:
        return stack1;
      case 2:
        return stack2;
      case 3:
        return stack3;
      default:
        return null; 
    }
  }

  // verifica se está na ordem selecionada pelo jogador
  public static Boolean verifyOrderedStacks(Pilha stack1, Pilha stack2, Pilha stack3 ){
    switch(orderChoice){
      case 1: // crescente
        if (stack1.isSortedAscending(stackSize) || stack2.isSortedAscending(stackSize) ||stack3.isSortedAscending(stackSize) ){
          return true;
        }
        return false;
        
      case 2: // decrescente
        if (stack1.isSortedDescending(stackSize) || stack2.isSortedDescending(stackSize) || stack3.isSortedDescending(stackSize)){
          return true;
        }
        return false;
        
      default:
        return  false;
    }
  }

  // resolucao automatica
  public static void automaticResolution() {
   if(stack2.getInformacao() < stack1.getInformacao()){
     int removedElement = stack2.remove();
     stack1.inserir(removedElement);
   }
    else{
      while(stack2.getInformacao()> stack1.getInformacao()){
        if(stack2.getInformacao() > stack3.getInformacao()){
          int removedElement = stack2.remove();
          stack3.inserir(removedElement);
        }
        else if(stack2.getInformacao() < stack3.getInformacao()){
          int removedElement = stack1.remove();
          stack2.inserir(removedElement);
          while(stack3.getInformacao() != -1){
            removedElement = stack3.remove();
            stack2.inserir(removedElement);
          }
          
        }
      }
    }
  }
}