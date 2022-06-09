package br.com.multimidia.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFileWrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class UploadMBean {
	
	private Boolean flag;
	private UploadedFile uploadedFile;
	private StreamedContent streamedContent;
	private String tipoFiltro;
	
	public UploadMBean() {
		flag = true;
		uploadedFile = new UploadedFileWrapper();
		streamedContent = new DefaultStreamedContent();
	}
	
	public void downloadArquivo() throws IOException {
		flag =true;
		String dir = "C:\\Users\\Usuário\\eclipse-workspace\\multimida_maven\\";
		
		//Carrega Imagem
		BufferedImage img = ImageIO.read(new File(dir+uploadedFile.getFileName()));
		
		/*Instancia filtro*/
		Filtro filtro = new Filtro();
		
		/*Aplica filtro conforme opção do usuário*/
		if(tipoFiltro.equals("1")) {
			filtro.escalaDeCinza(img);
		}else if(tipoFiltro.equals("2")){
			filtro.negativo(img);
		}else {
			filtro.threshold(img, 200);
		}
		
		/*Gera imagem com filtro*/
		ImageIO.write(img,"png",new File(dir+"imagemGerada.png"));
		
		/*Faz o download da imagem filtrada*/
		InputStream in = new FileInputStream(new File(dir+"imagemGerada.png"));
		streamedContent = DefaultStreamedContent.builder()
				.name("download.png")
				.contentType(uploadedFile.getContentType())
				.stream(()->in)
				.build();
		
		/*Mensagem de sucesso*/
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Download", "Download concluído!"));
	}

	public void upload(FileUploadEvent event) {
		uploadedFile = event.getFile();
		flag = false;
		String dir = "C:\\Users\\Usuário\\eclipse-workspace\\multimida_maven\\";
		try {
			File file = new File(dir, uploadedFile.getFileName());

			OutputStream out = new FileOutputStream(file);
			out.write(uploadedFile.getContent());
			out.close();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Upload completo", "O arquivo " + uploadedFile.getFileName() + " foi enviado!"));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}
}
