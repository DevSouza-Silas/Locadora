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
import com.locadora.model.ClienteRN;
import com.locadora.model.Filme;
import com.locadora.model.FilmeRN;
import com.locadora.model.Locacao;
import com.locadora.model.LocacaoRN;
import com.locadora.model.Midia;
import com.locadora.model.MidiaRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ManagedBean
@ViewScoped
public class LocacaoController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Locacao locacao;
    
    private LocacaoRN locacaoRN;
    
    private ClienteRN clienteRN;
    
    private MidiaRN midiaRN;
    
    private FilmeRN filmeRN;
     
    private List<Locacao> locacoes;
    
    private List<SelectItem> selectItemsClientes;
    
    private List<SelectItem> selectItemsFilmes;
    
    private List<SelectItem> selectItemsMidias;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagInputHidden_1;
    
    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	this.locacoes = new ArrayList<>();
    	this.selectItemsClientes = new ArrayList<>();
    	this.selectItemsMidias = new ArrayList<>();
    	this.selectItemsFilmes = new ArrayList<>();
    	this.locacao = new Locacao();
    	this.locacao.setMidia(new Midia());
    	this.locacao.getMidia().setFilme(new Filme());
    	this.locacao.setCliente(new Cliente());
    	
    	flagPesquisar = true;
    	flagNovo = true;
    	flagInputHidden_1 = false;
    	tituloForm = "Pesquisar";
    	
    	carregarLocacao();
    	carregarComboCliente();
    	carregarComboMidia();
    }
    
    public void buscarPorID(){
    	if (validarCampos()) {
    		
    		this.locacaoRN = new LocacaoRN();
    		this.locacao = this.locacaoRN.buscarPorID(this.locacao.getId());
		}
    }
    
    public String cadastrar() throws DAOException{
    	
    	if (validarCampos()) {
    		
    		this.locacaoRN = new LocacaoRN();
    		this.locacaoRN.salvar(this.locacao);
    		Message.info("Locação "+Message.MSG_SALVO);
    		carregarLocacao();
    		novo();
    	}
    	return null;
    }

    public String remover() throws RNException, DAOException {
    	
    	this.locacaoRN = new LocacaoRN();
    	this.locacaoRN.excluir(this.locacao);
		Message.erro("Locação "+Message.MSG_REMOCAO);
		cancelar();
		
		carregarLocacao();
    	return null;
    }
    
    public String editar() throws RNException, DAOException {
    	if (validarCampos()) {
    		
    		this.locacaoRN = new LocacaoRN();
    		this.locacaoRN.salvar(this.locacao);
    		Message.info("Locação "+Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
    	this.locacao = new Locacao();
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
    	
   		this.locacao = new Locacao();
    	flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagInputHidden_1 = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    }
    
    
    public List<Locacao> getLocacoes() {
		return locacoes;
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
    
    private void carregarLocacao() {
		
    	if (ClasseUtil.empty(this.locacoes.size(), 1, "Lista de Locações está vazia!")) {
			
    		this.locacaoRN = new LocacaoRN();
    		this.locacoes.addAll(this.locacaoRN.listar());
		}
	}
    
    private void carregarComboCliente(){
    	
    	List<Cliente> clientes = new ArrayList<>();
    	this.clienteRN = new ClienteRN();
    	
    	clientes = this.clienteRN.listar();
    	
    	this.selectItemsClientes.addAll(ClasseUtil.initCombo(clientes, "id", "nome"));
    }
    
    private void carregarComboMidia(){
    	
    	List<Midia> midias = new ArrayList<>();
    	this.midiaRN = new MidiaRN();

    	midias = this.midiaRN.listar();
    	
    	this.selectItemsMidias.addAll(ClasseUtil.initCombo(midias, "id", "id"));
    }
    
    public void getCarregarFilmePorDescricao(){
    	
    	List<Filme> filmes = new ArrayList<>();
    	
    	this.filmeRN = new FilmeRN();
    	
    	this.locacao.setMidia(new Midia());
    	this.locacao.getMidia().setFilme(new Filme());
    	filmes = this.filmeRN.buscarPorDescricao(this.locacao.getMidia().getFilme().getDescricao());
    	
    	this.selectItemsFilmes.addAll(ClasseUtil.initCombo(filmes, "id", "descricao"));
    }

    
    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(this.locacao.getMidia().getId(), "Campo Mídia está vazio.")
    			|| ClasseUtil.empty(this.locacao.getCliente().getId(), "Campo Cliente está vazio.")
    			|| ClasseUtil.emptyDate(this.locacao.getDataDevolucao(), "Campo Data de devolução está vazio.")
    			|| ClasseUtil.emptyDate(this.locacao.getDataEmprestimo(), "Campo Data de empréstimo está vazio.")
    			|| ClasseUtil.emptyDate(this.locacao.getHoraEmprestimo(), "Campo Hora de empréstimo está vazio.")
    			|| ClasseUtil.empty(this.locacao.getObservacao(), "Campo Observação está vazio.")) {
	    		
    			retorno = false;
    	}
    	return retorno;
    }
    
	public List<SelectItem> getSelectItemsFilmes() {
		
		getCarregarFilmePorDescricao();
		return selectItemsFilmes;
	}

	public void setSelectItemsFilmes(List<SelectItem> selectItemsFilmes) {
		this.selectItemsFilmes = selectItemsFilmes;
	}

	public List<SelectItem> getSelectItemsClientes() {
		return selectItemsClientes;
	}

	public void setSelectItemsClientes(List<SelectItem> selectItemsClientes) {
		this.selectItemsClientes = selectItemsClientes;
	}

	public List<SelectItem> getSelectItemsMidias() {
		return selectItemsMidias;
	}

	public void setSelectItemsMidias(List<SelectItem> selectItemsMidias) {
		this.selectItemsMidias = selectItemsMidias;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
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