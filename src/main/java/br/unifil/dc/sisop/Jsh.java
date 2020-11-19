package br.unifil.dc.sisop;

import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {
    static final String relogio = "relogio";
    static final String la = "la";
    static final String cd = "cd";
    static final String ad = "ad";
    static final String mdt = "mdt";
    static final String encerrar = "encerrar";

    /**
     * Funcao principal do Jsh.
     * 
     * @throws IOException
     */
    public static void promptTerminal() throws IOException {

        while (true) {
            exibirPrompt();
            ComandoPrompt comandoEntrado = lerComando();
            executarComando(comandoEntrado);
        }
    }

    /**
     * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
     * terminal está pronto para receber o próximo comando como entrada.
     * 
     * @throws IOException
     */
    public static void exibirPrompt() throws IOException {
        String userName = System.getProperty("user.name");
        String userDir = System.getProperty("user.dir");
        String comando ="id -u";
        Process child = Runtime.getRuntime().exec(comando);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(child.getInputStream()));
        String line = stdInput.readLine();

        System.out.print(userName+'#'+line+':'+userDir+'%'+" ");
        
    }

    /**
    * Preenche as strings comando e parametros com a entrada do usuario do terminal.
    * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
    * outras palavras subsequentes sao parametros para o comando. A palavras sao
    * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
    * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
    * EOL (End Of Line).
    *
    * @return 
    */
    public static ComandoPrompt lerComando() {
        Scanner in = new Scanner(System.in);
        String comando = in.nextLine();
        ComandoPrompt prompt = new ComandoPrompt(comando);
        return prompt;
    }

    /**
    * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
    * se for, o executa.
    * 
    * Se nao for, verifica se é o nome de um programa terceiro localizado no atual 
    * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
    * esse processo executa, o processo do uniterm deve permanecer em espera.
    *
    * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
    * programa desconhecido.
    */
    public static void executarComando(ComandoPrompt comando) {
        
        Scanner in = new Scanner(System.in);

            switch (comando.getNome()) {
                case relogio:
                    ComandosInternos.exibirRelogio();
                    break;
                case la:
                    String userDir = System.getProperty("user.dir");
                    ComandosInternos.escreverListaArquivos(Optional.ofNullable(userDir));
                    break;
                case cd:
                    System.out.print("Nome para a Pasta: ");
                    ComandosInternos.criarNovoDiretorio(in.nextLine());
                    break;
                case ad:
                    System.out.print("Nome da pasta para exclusão: ");
                    ComandosInternos.apagarDiretorio(in.nextLine());
                    break;

                case mdt: ComandosInternos.mudarDiretorioTrabalho(in.nextLine());
                    break;

                case encerrar: System.exit(0);
                    break;

                default:
                     System.out.print("Comando não encontrado"+'\n');
                     break;
            }


    }

    public static int executarPrograma(ComandoPrompt comando) {
        throw new RuntimeException("Método ainda não implementado.");
    }
    
    
    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        promptTerminal();
    }
    
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {}
}
