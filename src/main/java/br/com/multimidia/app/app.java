package br.com.multimidia.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class app {

    public static void main(String[] args) throws IOException {
    	
        try {
            //carrega nova imagem
            BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Usuário\\eclipse-workspace\\multimida_maven\\src\\main\\java\\br\\com\\multimidia\\app\\imagem1.png"));
            //instancia um filtro e aplica a escala de cinza
            Filtro filtro = new Filtro();
            //filtro.threshold(imagem1, 200);
            filtro.escalaDeCinza(imagem1);
            //gera uma nova imagem a partir da imagem1
            ImageIO.write(imagem1,"png",new File("imagem2.png"));
 
            //aqui apenas para demonstra��o,
            //carreguei novamente as duas imagens para exibi-las dentro de um JFrame
            imagem1 = ImageIO.read(new File("C:\\Users\\Usuário\\eclipse-workspace\\multimida_maven\\src\\main\\java\\br\\com\\multimidia\\app\\imagem1.png"));
            BufferedImage imagem2 = ImageIO.read(new File("imagem2.png"));
            Exibicao show = new Exibicao();
            show.exibirImagem(imagem1, imagem2);
            System.out.println("Filtro aplicado com sucesso!");
        }
        catch(IOException e){
            System.out.println("Erro! Verifique se o arquivo especificado existe e tente novamente.");
        }
        catch(Exception e){
            System.out.println("Erro! " + e.getMessage());
        }
    }
	
}
