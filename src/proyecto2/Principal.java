package proyecto2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args) throws NumberFormatException, IOException{
		
		
		//Variables
		int pacientesFiebre = 0;
		float valorXsintoma = 100;
		String sintomas[] = {"gripa", "fiebre", "dolor de estomago", "tos", "deficiencia respiratoria", "escalofrio"};
		int cantSintomas [] = {0, 0, 0, 0, 0, 0};	//LECTURA DE ARCHIVO
		
		BufferedReader bf;
		bf = new BufferedReader(new FileReader("datos.txt"));
		int tamaño = Integer.parseInt(bf.readLine());
		ArbolBinario arbol = new ArbolBinario();
		String [] datos;
		
		for (int i=0; i<tamaño; i++) {
			
			//Guarda en un array los datos de una linea donde cada posicion es el texto antes de ','
			datos = bf.readLine().split(",");
			
			//Obtiene la cantidad de sintomas de esa una linea
			int numSintomas =  Integer.parseInt(datos[2]);
			
			//Se inicializa el array de sintomas de el usuario de la linea
			String [] sintomasPaciente = new String[numSintomas];
			
			//Se almacena nombre y cedula de la linea
			int cedula = Integer.parseInt(datos[0]);
			String nombre = datos[1];
			
			//Verifica que no se guarde un paciente repetido
			if(arbol.buscarInicial(cedula) == null) {
				//Se avanza las siguientes lineas que son los sintomas y se almacenan en el vector
				for(int j=0; j< numSintomas; j++) {
					datos = bf.readLine().split(",");
					//Va consultando y sumando pacientes que tengan fiebre
					sintomasPaciente[j] =sintomas[Integer.parseInt(datos[0])];
					cantSintomas[Integer.parseInt(datos[0])] = cantSintomas[Integer.parseInt(datos[0])] + 1;
					
				}
				
				//Se crea el paciente con sus atributos y el array de sintomas
				float valorTratamiento = valorXsintoma * numSintomas;
				Paciente paciente = new Paciente(cedula, nombre, sintomas, valorTratamiento);
				
				//Agregamos un paciente al arbol
				arbol.agregarInicial(paciente);
				System.out.println(nombre+" ha sido agregado al sistema");
			}else {
				System.out.println(nombre+" esta duplicado. "+nombre+" no pudo ser agregada al sistema");
			}
			
			//--------------------------------------------------

		}
		
		//Buscamos un paciente y mostramos sus sintiomas por cedula
		Paciente paciente = arbol.buscarInicial(1010022902);
		if(paciente != null) {
			System.out.println(paciente.nombre+" tiene "+paciente.sintomas.length+" sintomas:");
			for (int i = 0; i < paciente.sintomas.length; i++) {
				System.out.println(paciente.sintomas[i]);
			}
		}else {
			System.out.println("El paciente ingresado no existe");
		}
		
		
		//Pacientes con fiebre
		int mayor = 0;
		for (int i = 0; i < cantSintomas.length; i++) {
			if (sintomas[i].equals("fiebre")) {
				System.out.println("El sistema registra "+cantSintomas[i]+" pacientes con fiebre");
			}
			
			if (cantSintomas[mayor] < cantSintomas[i]) {
				mayor = i;
			}
			
			
		}
		
		//Sintoma mas comun
		System.out.println("El sintoma mas comun es "+sintomas[mayor]);
		
		//Paciente y su precio por tratamiento
		arbol.listarInicial();
		

	}

}
