import java.util.Scanner;// importo a leitura do teclado
import java.util.Random;// importo o modo aleatorio

public class JogoDaVela { // crio uma class de acesso publico

    static int pontoJogador = 0; // crio uma variavel do tipo inteira recebendo o valor 0
    static int pontoComputador = 0;// crio uma variavel do tipo inteira recebendo o valor 0
    static Scanner input = new Scanner(System.in); //leitura do teclado e instancio um novo objeto


//crio o layout do tabuleiro
    public static void main(String[] args) {
    char[][] tabuleiro = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};   
    exibirTabuleiro(tabuleiro);
    boolean GameOver =false;
    boolean jogueNovamente=true;

// condicção enquanto  a funcao jogue novamente for executada e testo se o jogo acabou ou nao
    while(jogueNovamente){
        while(!GameOver){
             moverJogador(tabuleiro);
             GameOver = isGameOver(tabuleiro);
             if(GameOver){
                break;
             }
            computadorMove(tabuleiro);
            GameOver = isGameOver(tabuleiro);
            if(GameOver){
                break;
            }
        }
        System.out.println("Pontos do Jogador" + pontoJogador);// retorno para usuario 
        System.out.println("Pontos do Computador" + pontoComputador); // retorno para usuario
        System.out.println("Você quer Jogar novamente S/N"); // retorno para usuario
        input.nextLine(); //adiciono uma nova linha
        String resultado = input.nextLine(); //mostro o resultado na linha debaixo
        //utilizo o stwich para verificar qual foi a entrada do usuario para que o jogo continue ou nao
        switch(resultado){
            case "S":
            case "s":
                jogueNovamente=true;
                System.out.println("Legal Vamos Jogar de novo");
                reinicarTabuleiro(tabuleiro);
                GameOver =false;
                exibirTabuleiro(tabuleiro);
                break;

            case "N":
            case "n":
              jogueNovamente = false;
              System.out.println("Obrigada por jogar");
              break;
            default:
              break;


        }
    }
   
}



//criei uma funcao para mostrar o tabuleiro
public static void exibirTabuleiro(char [][] tabuleiro){
    for(char[] linha : tabuleiro){
        for( char c : linha){
            System.out.print(c);
        }
        System.out.println();
        }
    
    }       

//criei uma funcao para toda vez que alguem jogar ela atualiza o tabuleiro
public static void atualizarTabuleiro(int posicao, int jogador, char[][] tabuleiro) {

    char caractere;

    if (jogador == 1) {
      caractere = 'X';
    } else {
      caractere = 'O';
    }
    switch (posicao) {
        case 1:
        tabuleiro[0][0] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 2:
        tabuleiro[0][2] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 3:
        tabuleiro[0][4] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 4:
        tabuleiro[1][0] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 5:
        tabuleiro[1][2] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 6:
        tabuleiro[1][4] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 7:
        tabuleiro[2][0] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 8:
        tabuleiro[2][2] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
      case 9:
        tabuleiro[2][4] = caractere;
        exibirTabuleiro(tabuleiro);
        break;
          
      default:
        break;
      }
  

}
//crio uma funcao para mover o jogar no tabuleiro e exibir na tela
public static void moverJogador(char[][] tabuleiro){

   System.out.println("Por favor faça a mudança. (1-9)");
    

    int mover= input.nextInt();

    boolean resultado = isValidMover(mover, tabuleiro);
    
    
    while(!resultado){
        System.out.println("Movimento Invalido. Tente de novo");
        mover = input.nextInt();
        resultado = isValidMover(mover, tabuleiro);
    }
    System.out.println("Jogar foi movido para posição" + mover);
    atualizarTabuleiro(mover,1, tabuleiro);
}
//crio uma funcao para testar se o movimento do jogador ja n foi feito e se n esta ocupado o campo para colocar x ou o
public static boolean isValidMover(int mover, char[][] tabuleiro){
    switch (mover){
        case 1:
            if(tabuleiro[0][0] == '_'){
                return true;
            } else{
                return false;
            }
        case 2:
            if(tabuleiro[0][2] == '_'){
                return true;
            } else{
                return false;
            }
        case 3:
            if(tabuleiro[0][4] == '_'){
                return true;
            } else{
                return false;
            }
        case 4:
            if(tabuleiro[1][0] == '_'){
                return true;
            } else{
                return false;
            }
        case 5:
            if(tabuleiro[1][2] == '_'){
                return true;
            } else{
                return false;
            }
        case 6:
            if(tabuleiro[1][4] == '_'){
                return true;
            } else{
                return false;
            }
        case 7:
            if(tabuleiro[2][0] == ' '){
                return true;
            } else{
                return false;
            }
        case 8:
            if(tabuleiro[2][2] == ' '){
                return true;
            } else{
                return false;
            }
        case 9:
            if(tabuleiro[2][4] == ' '){
                return true;
            } else{
                return false;
            }

        default:
            return false;
    
    }
}
//crio uma funcao em que o computador move atraves do random   
public static void computadorMove(char[][] tabuleiro){
    Random rand = new Random();
    int mover = rand.nextInt(9)+1;

    boolean result = isValidMover(mover,tabuleiro);

    while(!result){
        mover = rand.nextInt(9)+1;
        result = isValidMover(mover, tabuleiro);
    }

    System.out.println("Computer moved at position "+ mover);
    atualizarTabuleiro(mover,2,tabuleiro);
}
//crio uma funçao para para validar se todas as posiçoes foram ocupadas e mostrar quem ganhou
public static boolean isGameOver(char [][] tabuleiro){

    //Vitoria Horizontal
    if(tabuleiro[0][0] == 'X'&& tabuleiro[0][2] == 'X' && tabuleiro [0][4] == 'X' ){
        System.out.println("Jogador Vence");
        pontoJogador++;
        return true;
    }
    if(tabuleiro[0][0] == 'O'&& tabuleiro[0][2] == 'O' && tabuleiro [0][4] == 'O' ){
        System.out.println("Computador Vence");
        pontoComputador++;
        return true;
    }
    if(tabuleiro[1][0] == 'X'&& tabuleiro[1][2] == 'X' && tabuleiro [1][4] == 'X' ){
        System.out.println("Jogador Vence");
        pontoJogador++;
        return true;
    }
    if(tabuleiro[1][0] == 'O'&& tabuleiro[1][2] == 'O' && tabuleiro [1][4] == 'O' ){
        System.out.println("Computador Vence");
        pontoComputador++;
        return true;
    }
    if(tabuleiro[2][0] == 'X'&& tabuleiro[2][2] == 'X' && tabuleiro [2][4] == 'X' ){
        System.out.println("Jogador Vence");
        pontoJogador++;
        return true;
    }
    if(tabuleiro[2][0] == 'O'&& tabuleiro[2][2] == 'O' && tabuleiro [2][4] == 'O' ) {
        System.out.println("Computador Vence");
        pontoComputador++;
        return true;
    }

    //Vertical vitoria

        if(tabuleiro[0][0] == 'X'&& tabuleiro[1][0] == 'X' && tabuleiro [2][0] == 'X' ){
            System.out.println("Jogador Vence");
            pontoJogador++;
            return true;
        }
        if(tabuleiro[0][0] == 'O'&& tabuleiro[1][0] == 'O' && tabuleiro [2][0] == 'O' ){
            System.out.println("Computador Vence");
            pontoComputador++;
            return true;
        }

        if(tabuleiro[0][2] == 'X'&& tabuleiro[1][2] == 'X' && tabuleiro [2][2] == 'X' ){
            System.out.println("Jogador Vence");
            pontoJogador++;
            return true;
        }
        if(tabuleiro[0][2] == 'O'&& tabuleiro[1][2] == 'O' && tabuleiro [2][2] == 'O' ){
            System.out.println("Computador Vence");
            pontoComputador++;
            return true;
        }

        if(tabuleiro[0][4] == 'X'&& tabuleiro[1][4] == 'X' && tabuleiro [2][4] == 'X' ){
            System.out.println("Jogador Vence");
            pontoJogador++;
            return true;
        }
        if(tabuleiro[0][4] == 'O'&& tabuleiro[1][4] == 'O' && tabuleiro [2][4] == 'O' ){
            System.out.println("Computador Vence");
            pontoComputador++;
            return true;
        }

        //Diagonal vitoria
        if(tabuleiro[0][0] == 'X'&& tabuleiro[1][2] == 'X' && tabuleiro [2][4] == 'X' ){
            System.out.println("Jogador Vence");
            pontoJogador++;
            return true;
        }
        if(tabuleiro[0][0] == 'O'&& tabuleiro[1][2] == 'O' && tabuleiro [2][4] == 'O' ){
            System.out.println("Computador Vence");
            pontoComputador++;
            return true;
        }

        if(tabuleiro[2][0] == 'X'&& tabuleiro[1][2] == 'X' && tabuleiro [0][4] == 'X' ){
            System.out.println("Jogador Vence");
            pontoJogador++;
            return true;
        }
        if(tabuleiro[2][0] == 'O'&& tabuleiro[1][2] == 'O' && tabuleiro [0][4] == 'O' ){
            System.out.println("Computador Vence");
            pontoComputador++;
            return true;
        }

        if(tabuleiro[0][0] != '_' && tabuleiro[0][2] != '_' && tabuleiro[0][4] != '_' && tabuleiro[1][0] !='_'&&
            tabuleiro[1][2] != '_' && tabuleiro[1][4] != '_' && tabuleiro[2][0] != ' ' && tabuleiro[2][2] != ' ' && tabuleiro[2][4] != ' '){
            System.out.println("Empate");
            return true;
        }

return false;}

//crio uma função para zerar o tabuleiro quando o usuario deseja jogar novamente
public static void reinicarTabuleiro(char [][] tabuleiro){
    tabuleiro[0][0] = '_';
    tabuleiro[0][2] = '_';
    tabuleiro[0][4] = '_';
    tabuleiro[1][0] = '_';
    tabuleiro[1][2] = '_';
    tabuleiro[1][4] = '_';
    tabuleiro[2][0] = ' ';
    tabuleiro[2][2] = ' ';
    tabuleiro[2][4] = ' ';
}
}