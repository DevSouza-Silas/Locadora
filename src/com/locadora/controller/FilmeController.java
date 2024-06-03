package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.locadora.model.Categoria;
import com.locadora.model.CategoriaRN;
import com.locadora.model.Cliente;
import com.locadora.model.Endereco;
import com.locadora.model.EnderecoRN;
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
     
    private CategoriaRN categoriaRN;

    private List<Filme> filmes;
    
    private List<SelectItem> selectItemsCategorias;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	this.filmes = new ArrayList<>();
    	this.filme = new Filme();
    	this.filme.setCategoria(new Categoria());
    	this.selectItemsCategorias = new ArrayList<SelectItem>();
    	
    	flagPesquisar = true;
    	flagNovo = true;
    	tituloForm = "Pesquisar";
    	
    	carregarFilme();
    	carregarCategorias();
    }
    
    public String pesquisar(){

    	if (!ClasseUtil.empty(this.filme.getDescricao(), "")){
    		this.filmeRN = new FilmeRN();
    		this.filmes.clear();

    		filmes.addAll(this.filmeRN.buscarPorDescricao(this.filme.getDescricao()));
    	}
    	return null;
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
    		this.filmeRN.atualizar(this.filme);
    		Message.info("Filme "+Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
    	this.filme = new Filme();
    	this.filme.setCategoria(new Categoria());
    	
    	flagPesquisar = true;
    	flagNovo = true;
    	flagEditar = false;
		flagCadastrar = false;
		flagCancelar = false;
		tituloForm = "Pesquisar";
		return null;
    }
    
    public String novo() {
    	
    	filme = new Filme();
    	filme.setCategoria(new Categoria());
    	
    	flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    
		return null;
    }
    
    
    public List<Filme> getFilmes() {
		return filmes;
	}

    public String atualizarFlagEditar() {
    	flagEditar = true;
    	flagCancelar = true;
    	flagPesquisar = false;
		flagCadastrar = false;
		flagNovo = false;
		tituloForm = "Editar";
    	return null;
    }
    
    private void carregarFilme() {
		
    	if (ClasseUtil.empty(this.filmes.size(), 1, "")) {
			
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
    
    private void carregarCategorias(){
    	
    	List<Categoria> categorias = new ArrayList<>();
    	this.categoriaRN = new CategoriaRN();
    	
    	categorias = this.categoriaRN.listar();
    	
    	this.selectItemsCategorias.addAll(ClasseUtil.initCombo(categorias, "id", "descricao"));
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

	public String getTituloForm() {
		return tituloForm;
	}

	public void setTituloForm(String tituloForm) {
		this.tituloForm = tituloForm;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<SelectItem> getSelectItemsCategorias() {
		return selectItemsCategorias;
	}

	public void setSelectItemsCategorias(List<SelectItem> selectItemsCategorias) {
		this.selectItemsCategorias = selectItemsCategorias;
	}

}