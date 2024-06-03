package com.locadora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.locadora.model.Categoria;
import com.locadora.model.CategoriaRN;
import com.locadora.util.ClasseUtil;
import com.locadora.util.DAOException;
import com.locadora.util.Message;
import com.locadora.util.RNException;

@ManagedBean
@ViewScoped
public class CategoriaController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
    private Categoria categoria;
    
    private CategoriaRN categoriaRN;
     
    private List<Categoria> categorias;
    
	private String confirmarSenha;
	
	private String tituloForm;
	
    private boolean flagCadastrar;

    private boolean flagInputHidden_1;
    
    private boolean flagEditar;
    
    private boolean flagCancelar;
    
    private boolean flagPesquisar;
    
    private boolean flagNovo;
    
    @PostConstruct
    public void init() {
    	
    	categorias = new ArrayList<>();
    	categoria = new Categoria();
    	confirmarSenha = null;
    	
    	flagPesquisar = true;
    	flagNovo = true;
    	flagInputHidden_1 = false;
    	tituloForm = "Pesquisar";
    	
    	carregarCategoria();
    }
    
    public String pesquisar(){
    	if (!ClasseUtil.empty(categoria.getDescricao(), "Informe a descrição!")) {
    		
    		categoriaRN = new CategoriaRN();
    		categorias.clear();
    		categorias.addAll(categoriaRN.buscarPorDescricao(categoria.getDescricao()));
		}
    	return null;
    }
    
    public String cadastrar() throws DAOException{
    	
    	if (validarCampos()) {
    		
        	categoriaRN = new CategoriaRN();
    		categoriaRN.salvar(categoria);
    		Message.info("Categoria "+ Message.MSG_SALVO);
    		carregarCategoria();
    		novo();
    	}
    	return null;
    }

    public String remover() throws RNException, DAOException {
    	
		categoriaRN = new CategoriaRN();
		categoriaRN.excluir(categoria);
		Message.erro("Categoria "+Message.MSG_REMOCAO);
		cancelar();
		
		carregarCategoria();
    	return null;
    }
    
    public String editar() throws RNException, DAOException {
    	if (validarCampos()) {
    		
    		categoriaRN = new CategoriaRN();
    		categoriaRN.atualizar(categoria);
    		Message.info("Categoria "+Message.MSG_EDICAO);
    		cancelar();
    	}

		return null;
    } 
    
    public String cancelar() {
    	
   		categoria = new Categoria();
   		confirmarSenha = null;
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
    	
   		categoria = new Categoria();
   		confirmarSenha = null;
    	flagEditar = false;
    	flagPesquisar = false;
    	flagCancelar = true;
		flagCadastrar = true;
		flagInputHidden_1 = true;
		flagNovo = false;
		tituloForm = "Cadastrar";
    }
    
    
    public List<Categoria> getCategorias() {
		return categorias;
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
    
    private void carregarCategoria() {
		
    	if (ClasseUtil.empty(categorias.size(), 1, "Lista de categoria está vazia!")) {
			
    		categoriaRN = new CategoriaRN();
    		categorias.addAll(categoriaRN.listar());
		}
	}

    private boolean validarCampos() {
    	boolean retorno = true;
    	
    	if (ClasseUtil.empty(categoria.getDescricao(), "Campo Descrição está vazio.")
    			|| ClasseUtil.empty(categoria.getDescricao().length(), 3, "A descrição não pode ser menor que 3 caracteres.")) {
	    		
    			retorno = false;
    	}
    	return retorno;
    }
    
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
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