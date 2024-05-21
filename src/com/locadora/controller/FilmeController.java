package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.locadora.model.Filme;
import com.locadora.model.FilmeRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ManagedBean
@ViewScoped
public class FilmeController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Filme filme;
    
    private FilmeRN filmeRN;
     
    private List<Filme> filmes;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagInputHidden_1;
    
    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	this.filmes = new ArrayList<>();
    	this.filme = new Filme();
    	flagPesquisar = true;
    	flagNovo = true;
    	flagInputHidden_1 = false;
    	tituloForm = "Pesquisar";
    	carregarFilme();
    }
    
    public void pesquisar(){
    	if (validarCampos()) {
    		
    		this.filmeRN = new FilmeRN();
    		this.filmes.clear();
    		filmes.addAll(this.filmeRN.buscarPorDescricao(this.filme.getDescricao()));
		}
    }
    
    public String cadastrar() throws DAOException{
    	
    	if (validarCampos()) {
    		
    		this.filmeRN = new FilmeRN();
    		this.filmeRN.salvar(this.filme);
    		Message.info("Filme "+Message.MSG_SALVO);
    		carregarFilme();
    		novo();
    	}
    	return null;
    }

    public String remover() throws RNException, DAOException {
    	
    	this.filmeRN = new FilmeRN();
    	this.filmeRN.excluir(this.filme);
		Message.erro("Filme "+Message.MSG_REMOCAO);
		cancelar();
		
		carregarFilme();
    	return null;
    }
    
    public String editar() throws RNException, DAOException {
    	if (validarCampos()) {
    		
    		this.filmeRN = new FilmeRN();
    		this.filmeRN.salvar(this.filme);
    		Message.info("Filme "+Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
    	this.filme = new Filme();
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
    	
   		this.filme = new Filme();
    	flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagInputHidden_1 = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    }
    
    
    public List<Filme> getFilmes() {
		return filmes;
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
    
    private void carregarFilme() {
		
    	if (ClasseUtil.empty(this.filmes.size(), 1, "Lista de Filmes está vazia!")) {
			
    		this.filmeRN = new FilmeRN();
    		this.filmes.addAll(this.filmeRN.listar());
		}
	}

    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(this.filme.getDescricao(), "Campo Descrição está vazio.")
    			|| ClasseUtil.emptyDate(this.filme.getAno(), "Campo Ano está vazio.")
    			|| ClasseUtil.empty(this.filme.getCategoria().getId(), "Campo Categoria está vazio.")) {
	    		
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