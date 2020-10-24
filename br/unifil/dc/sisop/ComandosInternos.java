package br.unifil.dc.sisop;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
    
    public static int exibirRelogio() {
        System.out.print("São "+ LocalTime.now() + " de " + LocalDate.now()+'\n');
        return 1;
    }
    
    public static int escreverListaArquivos(Optional<String> nomeDir) {
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir);
        File[] arquivos = file.listFiles();

        for (File fileTmp : arquivos) {
            System.out.println(fileTmp.getName());
        }

        return 1;
    }
    
    public static int criarNovoDiretorio(String nomeDir) {
        new File(nomeDir).mkdir();
        System.out.print("Pasta Criada"+'\n');
        return 1;
    }
    
    public static int apagarDiretorio(String nomeDir) {
        File dir = new File(nomeDir);
        if(dir.exists()) {
            new File(nomeDir).delete();
            System.out.print("Pasta Deletada"+'\n');
        }else{
            System.out.print("Pasta não encontrada"+'\n');
        }
        return 1;
    }
    
    public static int mudarDiretorioTrabalho(String nomeDir){
       File diretorio;
       diretorio = new File(nomeDir).getAbsoluteFile();
       if(diretorio.exists()){
           System.setProperty("user.dir",diretorio.getAbsolutePath());
       }

       return 1;
    }
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}
}
