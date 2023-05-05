import java.util.ArrayList;
import java.io.*;

/*
A estrutura de armazenamento utiliza um arquivo no formato texto para armazenar os dados do projeto em questão.

A variável fileName (String) é responsável por armazenar o nome e caminho do arquivo que contém os dados a serem lidos ou escritos.
A classe FileReader é utilizada para ler o arquivo de texto, e a classe BufferedReader é utilizada para ler os dados do arquivo de forma mais eficiente e rápida.

Já a variável dataFile (File) é responsável por armazenar a referência ao arquivo de texto, que pode ser usado posteriormente para ler ou gravar dados.

Essa abordagem de armazenamento em arquivo de texto pode ser utilizada para persistência de dados em um banco de dados simples, ou para permitir que o usuário possa salvar e carregar informações em um aplicativo de desktop, sem a necessidade de configurar um servidor de banco de dados.
Porém, é importante ressaltar que essa abordagem pode não ser adequada para grandes volumes de dados ou para projetos que exijam alta disponibilidade e desempenho.
*/

public class Armazenamento implements ArmazenamentoGenerico {
	String fileName;
	FileReader fileReader;
	BufferedReader bufferReader;
	File dataFile;
	
	public Armazenamento() {
		fileName = "dados";
	}
	
	private void openFile() {
		try {
			dataFile = new File(fileName);
			dataFile.createNewFile();
			fileReader = new FileReader(dataFile);
			bufferReader = new BufferedReader(fileReader);
		} catch (Exception e) {
			System.out.println("File not found");
		}
		
	}
	
	private void closeFile() {
		try {
			bufferReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String registerNewUser(String user) {
		return "User " + user + "\n";
	}
	
	private String registerNewPointType(String pointType, int pointNumber) {
		return pointType + " " + pointNumber + "\n";
	}
	
	@Override
	public void armazenaPontos(String usuario, int numeroDePontos, String tipoDePontos) {
		openFile();
		String line = null;
		File newDataFile = new File("novosDados");
		boolean isCurrentUser = false;
		boolean userIsNotRegistered = true;
		try {
			FileWriter fileWriter = new FileWriter(newDataFile);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			while((line = bufferReader.readLine()) != null) {
				String [] words = line.split(" ");
				
				if(words[1].equals(usuario)) {
					isCurrentUser = true;
					userIsNotRegistered = false;
				} else if (isCurrentUser && (words[0].equals("User"))) {
					bufferWriter.write(registerNewPointType(tipoDePontos, numeroDePontos));
					isCurrentUser = false;
				} else if(isCurrentUser && words[0].equals(tipoDePontos)) {
					int totalPoints = numeroDePontos + Integer.parseInt(words[1]);
					line = tipoDePontos + ' ' + totalPoints;
					isCurrentUser = false;
				}
				
				bufferWriter.write(line + '\n');
			}
			
			if(isCurrentUser) {
				bufferWriter.write(registerNewPointType(tipoDePontos, numeroDePontos));
			}
			
			if(userIsNotRegistered) {
				bufferWriter.write(registerNewUser(usuario));
				bufferWriter.write(registerNewPointType(tipoDePontos, numeroDePontos));
			}
			
			bufferWriter.close();
			closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newDataFile.renameTo(dataFile);
		newDataFile.delete();
	}
	
	private boolean isTipoDePontoValido(String palavra, ArrayList<String> lista) {
		return !palavra.equals("User") && !lista.contains(palavra);
	}

	@Override
	public int getsPointsByType(String usuario, String tipoDePontos) {
		String line = null;
		boolean isCurrentUser = false;
		
		openFile();
     
		try {
			while((line = bufferReader.readLine()) != null) {
			    String words[] = line.split(" ");
			    			    
			    if(words[1].equals(usuario)) {
			    	isCurrentUser = true;
			    } else if (words[0].equals("User")) {
			    	isCurrentUser = false;
			    }
			    
			    if (isCurrentUser && words[0].equals(tipoDePontos)) {
			    	closeFile();
			    	return Integer.parseInt(words[1]);
			    }
			    
			}
			
			closeFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}   
				
		return 0;
		
	}

	@Override
	public ArrayList<String> returnsAllUsers() {
		openFile();
		String line = null;
		ArrayList<String> usuarios = new ArrayList<String>();
		
		try {
			while((line = bufferReader.readLine()) != null) {
				String words[] = line.split(" ");
				if(words[0].equals("User")) {
					usuarios.add(words[1]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeFile();
		
		return usuarios;
	}

	@Override
	public ArrayList<String> returnsAllPointTypes() {
		openFile();
		String line = null;
		ArrayList<String> tiposDePonto = new ArrayList<String>();
		
		try {
			while((line = bufferReader.readLine()) != null) {
				String words[] = line.split(" ");
				if(isTipoDePontoValido(words[0], tiposDePonto)) {
					tiposDePonto.add(words[0]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeFile();
		
		return tiposDePonto;
	}
	
	
}
