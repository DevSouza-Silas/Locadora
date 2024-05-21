package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.locadora.model.Endereco;
import com.locadora.model.EnderecoRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ManagedBean
@ViewScoped
public class EnderecoController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Endereco endereco;
    
    private EnderecoRN enderecoRN;
     
    private List<Endereco> enderecos;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagInputHidden_1;
    
    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	this.enderecos = new ArrayList<>();
    	this.endereco = new Endereco();
    	flagPesquisar = true;
    	flagNovo = true;
    	flagInputHidden_1 = false;
    	tituloForm = "Pesquisar";
    	carregarEndereco();
    }
    
   /* public void pesquisar(){
    	if (validarCampos()) {
    		
    		enderecoRN = new EnderecoRN();
    		enderecos.clear();
    		enderecos.addAll(enderecoRN.buscarPorId(endereco.getRua()));
		}
    }*/
    
    public String cadastrar() throws DAOException{
    	
    	if (validarCampos()) {
    		
    		this.enderecoRN = new EnderecoRN();
    		this.enderecoRN.salvar(this.endereco);
    		Message.info("Endereço " + Message.MSG_SALVO);
    		carregarEndereco();
    		novo();
    	}
    	return null;
    }

    public String remover() throws RNException, DAOException {
    	
    	this.enderecoRN = new EnderecoRN();
    	this.enderecoRN.excluir(this.endereco);
		Message.erro("Endereço " + Message.MSG_REMOCAO);
		cancelar();
		
		carregarEndereco();
    	return null;
    }
    
    public String editar() throws RNException, DAOException {
    	if (validarCampos()) {
    		
    		this.enderecoRN = new EnderecoRN();
    		this.enderecoRN.salvar(this.endereco);
    		Message.info("Endereço "+ Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
    	this.endereco = new Endereco();
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
    	
   		this.endereco = new Endereco();
    	flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagInputHidden_1 = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    }
    
    
    public List<Endereco> getEnderecos() {
		return enderecos;
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
    
    private void carregarEndereco() {
		
    	if (ClasseUtil.empty(this.enderecos.size(), 1, "Lista de endereços está vazia!")) {
			
    		this.enderecoRN = new EnderecoRN();
    		this.enderecos.addAll(enderecoRN.listar());
		}
	}

    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(this.endereco.getRua(), "Campo Rua está vazio.")
    			|| ClasseUtil.empty(this.endereco.getBairro(), "Campo Bairro está vazio.")
    			|| ClasseUtil.empty(this.endereco.getNumero().longValue(), "Campo Número está vazio.")) {
	    		
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