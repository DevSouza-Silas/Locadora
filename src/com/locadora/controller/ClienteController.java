package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.locadora.model.Cliente;
import com.locadora.model.ClienteRN;
import com.locadora.model.Endereco;
import com.locadora.model.EnderecoRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Cliente cliente;
    
    private ClienteRN clienteRN;
    
    private EnderecoRN enderecoRN;
    
    private List<SelectItem> selectItemEnderecos;
    
    private List<Cliente> clientes;
    
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	
    	initObjects();
    	
    	flagPesquisar = true;
    	flagNovo = true;
    	tituloForm = "Pesquisar";
    	
    	carregarCliente();
    	carregarComboEndereco();
    }
    
    private void initObjects() {
    	
    	this.clientes = new ArrayList<>();
    	this.cliente = new Cliente();
    	this.cliente.setEndereco(new Endereco());
    	this.selectItemEnderecos = new ArrayList<>();
    }
    
    public String pesquisar(){
    	
    	if (!ClasseUtil.empty(cliente.getNome(), "Informe o Nome!")) {
    		
    		clienteRN = new ClienteRN();
    		clientes.clear();
    		clientes.addAll(clienteRN.buscarPorNome(cliente.getNome()));
		}
    	
    	return null;
    }
    
    public String cadastrar() {
    	
    	if (validarCampos()) {
    		
    		this.clienteRN = new ClienteRN();
    		this.clienteRN.salvar(this.cliente);
    		
    		Message.info("Cliente " + Message.MSG_SALVO);
    		carregarCliente();
    		novo();
    	}
    	return "";
    }

    public String remover() throws RNException, DAOException {
    	
    	clienteRN = new ClienteRN();
    	clienteRN.excluir(cliente);
    	
		Message.erro("Cliente " + Message.MSG_REMOCAO);
		cancelar();
		
		carregarCliente();
    	return null;
    }
    
    public String editar() throws RNException, DAOException {
    	
    	if (validarCampos()) {
    		
    		clienteRN = new ClienteRN();
    		clienteRN.atualizar(cliente);
    		
    		Message.info("Cliente " + Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
    	cliente = new Cliente();
    	cliente.setEndereco(new Endereco());
    	flagPesquisar = true;
    	
    	flagNovo = true;
    	flagEditar = false;
		flagCadastrar = false;
		flagCancelar = false;
		tituloForm = "Pesquisar";
		return null;
    }
    
    public String novo() {
    	
   		cliente = new Cliente();
   		cliente.setEndereco(new Endereco());
    	
   		flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    
		return null;
    }
    
    
    public List<Cliente> getClientes() {
		return clientes;
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
    
    private void carregarCliente() {
		
    	if (ClasseUtil.empty(clientes.size(), 1, "")) {
			
    		clienteRN = new ClienteRN();
    		clientes.addAll(clienteRN.listar());
		}
	}

    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(cliente.getNome(), "Campo Nome está vazio.")
    			|| ClasseUtil.empty(cliente.getEmail(), "Campo Email está vazio.")
    			|| ClasseUtil.empty(cliente.getCelular(), "Campo Celular está vazio.")
    			|| ClasseUtil.empty(cliente.getTelefone(), "Campo Telefone está vazio.")) {
	    		
    			retorno = false;
    	}
    	return retorno;
    }
    
    private void carregarComboEndereco(){
    	
    	List<Endereco> listEndereco = new ArrayList<>();
    	enderecoRN = new EnderecoRN();
    	
    	listEndereco = enderecoRN.listar();
    	
   		this.selectItemEnderecos.addAll(ClasseUtil.initCombo(listEndereco, "id", "rua"));
    }
    
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public List<SelectItem> getSelectItemEnderecos() {
		return selectItemEnderecos;
	}

	public void setSelectItemEnderecos(List<SelectItem> selectItemEnderecos) {
		this.selectItemEnderecos = selectItemEnderecos;
	}

}