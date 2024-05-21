package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.locadora.model.Midia;
import com.locadora.model.MidiaRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ManagedBean
@ViewScoped
public class MidiaController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Midia midia;
    
    private MidiaRN midiaRN;
     
    private List<Midia> midias;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagInputHidden_1;
    
    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	this.midias = new ArrayList<>();
    	this.midia = new Midia();
    	flagPesquisar = true;
    	flagNovo = true;
    	flagInputHidden_1 = false;
    	tituloForm = "Pesquisar";
    	carregarMidia();
    }
    
    public void buscarPorID(){
    	if (validarCampos()) {
    		
    		this.midiaRN = new MidiaRN();
    		this.midia = this.midiaRN.buscarPorID(this.midia.getId());
		}
    }
    
    public String cadastrar() throws DAOException{
    	
    	if (validarCampos()) {
    		
    		this.midiaRN = new MidiaRN();
    		this.midiaRN.salvar(this.midia);
    		Message.info("Mídia "+Message.MSG_SALVO);
    		carregarMidia();
    		novo();
    	}
    	return null;
    }

    public String remover() throws RNException, DAOException {
    	
    	this.midiaRN = new MidiaRN();
    	this.midiaRN.excluir(this.midia);
		Message.erro("Mídia "+Message.MSG_REMOCAO);
		cancelar();
		
		carregarMidia();
    	return null;
    }
    
    public String editar() throws RNException, DAOException {
    	if (validarCampos()) {
    		
    		this.midiaRN = new MidiaRN();
    		this.midiaRN.salvar(this.midia);
    		Message.info("Mídia "+Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
    	this.midia = new Midia();
    	flagPesquisar = true;
    	flagNovo = true;
    	flagEditar = false;
		flagCadastrar = false;
		flagCancelar = false;
		flagInputHidden_1 = false;
		tituloForm = "Pesquisar";
		return null;
    }
    
    public void novo() {
    	
   		this.midia = new Midia();
    	flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagInputHidden_1 = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    }
    
    
    public List<Midia> getMidias() {
		return midias;
	}

    public String atualizarFlagEditar() {
    	flagEditar = true;
    	flagCancelar = true;
    	flagPesquisar = false;
		flagCadastrar = false;
		flagInputHidden_1 = true;
		flagNovo = false;
		tituloForm = "Editar";
    	return null;
    }
    
    private void carregarMidia() {
		
    	if (ClasseUtil.empty(this.midias.size(), 1, "Lista de Mídia está vazia!")) {
			
    		this.midiaRN = new MidiaRN();
    		this.midias.addAll(this.midiaRN.listar());
		}
	}

    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(this.midia.getFilme().getId(), "Campo Filme está vazio.")
    			|| ClasseUtil.empty(this.midia.getInutilizada(), "Campo Inutilizada está vazio.")) {
	    		
    			retorno = false;
    	}
    	return retorno;
    }
    
	public boolean isFlagCadastrar() {
		return flagCadastrar;
	}

	public void setFlagCadastrar(boolean flagCadastrar) {
		this.flagCadastrar = flagCadastrar;
	}

	public boolean isFlagEditar() {
		return flagEditar;
	}

	public void setFlagEditar(boolean flagEditar) {
		this.flagEditar = flagEditar;
	}

	public boolean isFlagPesquisar() {
		return flagPesquisar;
	}

	public void setFlagPesquisar(boolean flagPesquisar) {
		this.flagPesquisar = flagPesquisar;
	}

	public boolean isFlagCancelar() {
		return flagCancelar;
	}

	public void setFlagCancelar(boolean flagCancelar) {
		this.flagCancelar = flagCancelar;
	}

	public boolean isFlagNovo() {
		return flagNovo;
	}

	public void setFlagNovo(boolean flagNovo) {
		this.flagNovo = flagNovo;
	}

	public boolean isFlagInputHidden_1() {
		return flagInputHidden_1;
	}

	public void setFlagInputHidden_1(boolean flagInputHidden_1) {
		this.flagInputHidden_1 = flagInputHidden_1;
	}

	public String getTituloForm() {
		return tituloForm;
	}

	public void setTituloForm(String tituloForm) {
		this.tituloForm = tituloForm;
	}

}