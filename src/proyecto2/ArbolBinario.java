package proyecto2;

public class ArbolBinario {
	NodoArbol root;
	
	//Funcion para agregar un paciente al arbol
	public void agregarInicial(Paciente paciente) {
		
		
		NodoArbol nuevoNodo = new NodoArbol(paciente);
		
		if(root == null) {
			root = nuevoNodo;
		}
		
		else {
			agregar(root, paciente);
		}
		
	}
	
	
	public void agregar(NodoArbol root, Paciente paciente) {
		
		if(root == null) {
			return;
		}
		
		NodoArbol nuevoNodo = new NodoArbol(paciente);
		
		if(paciente.cedula > root.paciente.cedula) {
			
			if(root.derecha == null) {
				root.derecha = nuevoNodo;
			}
			
			else {
				agregar(root.derecha, paciente);
			}
			
		}
		
		else {
			
			if(root.izquierda == null) {
				root.izquierda = nuevoNodo;
			}
			
			else {
				agregar(root.izquierda, paciente);
			}
		}
		
	}
	
	public Paciente buscarInicial (int cedula) {
		return buscar(root, cedula);
	}
	
	public Paciente buscar (NodoArbol root, int cedula) {
		
		if(root == null) {
			return null;
		}
		
		else {
			
			if(root.paciente.cedula == cedula) {
				return root.paciente;
			}
			
			if(cedula > root.paciente.cedula) {
				return buscar(root.derecha, cedula);
			}
			
			else {
				return buscar(root.izquierda, cedula);
			}
		}
	}
	
	public void listarInicial () {
		listar(root);
	}
	
	public void listar (NodoArbol root) {

	        if (root!=null) {
	            System.out.println("El paciente "+root.paciente.nombre+" paga por su tratamiento "+root.paciente.valorTratamiento);
	            listar(root.izquierda);
	            listar(root.derecha);
	        }
	}
	
	
}
