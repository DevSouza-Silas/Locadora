package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.locadora.model.Filme;
import com.locadora.model.FilmeRN;
import com.locadora.model.Midia;
import com.locadora.model.MidiaRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ViewScoped
@ManagedBean(name = "midiaController")
public class MidiaController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Midia midia;
    
    private Filme filme;
    
    private MidiaRN midiaRN;
    
    private FilmeRN filmeRN;
     
    private List<Midia> midias;
    
    private List<SelectItem> selectItemsFilmes;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagInputHidden_1;
    
    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	
    	initObjects();
    	
    	flagPesquisar = true;
    	flagNovo = true;
    	flagInputHidden_1 = false;
    	tituloForm = "Pesquisar";
    	
    	carregarMidia();
    	carregarFilmes();
    }
    		
    private void initObjects() {
    	
    	this.midias = new ArrayList<>();
    	this.selectItemsFilmes = new ArrayList<>();
    	this.midia = new Midia();
    	this.filme = new Filme();
    	this.midia.setFilme(new Filme());
    }
    
    public void buscarMidiaPorFilme(){
    	
    	if (this.filme.getId() > 0) {
				
	   		this.midiaRN = new MidiaRN();
	   		this.midias = this.midiaRN.buscarMidiaPorFilme(this.filme.getId());
    	} else {
    		this.midias = this.midiaRN.listar();
    	}
    }
    
    public void buscarPorID(){
    	
   		this.midiaRN = new MidiaRN();
   		this.midia = this.midiaRN.buscarPorID(this.midia.getId());
    }
    
    public String cadastrar() throws DAOException{

    	this.midia.setFilme(this.filme);
    	
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
    		this.midiaRN.atualizar(this.midia);
    		
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
		
    	if (this.midias.size() == 0) {
			
    		this.midiaRN = new MidiaRN();
    		this.midias.addAll(this.midiaRN.listar());
		}
	}
    
    private void carregarFilmes(){
    	
    	List<Filme> filmes = new ArrayList<>();
    	this.filmeRN = new FilmeRN();
    	
    	filmes = this.filmeRN.listar();
    	
    	this.selectItemsFilmes.addAll(ClasseUtil.initCombo(filmes, "id", "descricao"));
    }

    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(this.midia.getFilme().getId(), "Campo Filme está vazio.")) {
	    		
   			retorno = false;
    	}
    	return retorno;
    }
    
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
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

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public List<SelectItem> getSelectItemsFilmes() {
		return selectItemsFilmes;
	}

	public void setSelectItemsFilmes(List<SelectItem> selectItemsFilmes) {
		this.selectItemsFilmes = selectItemsFilmes;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

}