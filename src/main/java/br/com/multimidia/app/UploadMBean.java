package br.com.multimidia.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFileWrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@RequestScoped
public class UploadMBean {
	
	private Boolean flag;
	private UploadedFile uploadedFile;
	private String tipoFiltro;
	
	public UploadMBean() {
		flag = true;
		uploadedFile = new UploadedFileWrapper();
	}
	
	public void download() {
		System.out.println(tipoFiltro);
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
					new FacesMessage("Upload completo", "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}
}
