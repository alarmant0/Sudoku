import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class Sudoku {

	private SudokuBoard sudokuBoard;
	public ColorImage boardImage = new ColorImage(Param.SQUARE_SIZE*9,Param.SQUARE_SIZE*9, Param.SQUARE_BACKCOLOR);; // para ver no PandionJ
	
	public Sudoku(String file, double difficulty) { // jogo1.sud
		makeSudokoBoard(file,difficulty);
		paint();
	}
	
	//---------------------------------SUDOKOBOARD-----------------------------------------------
	
	private void makeSudokoBoard(String file, double difficulty) {
		int [][] matriz = new int [9][9];
		try {
			Scanner scanner = new Scanner(new File(file));
			int y = 0;
			while(scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				String [] array = linha.split(" ");
				for(int i = 0;i<array.length;i++) {
					matriz[y][i] = Integer.parseInt(array[i]);
				}
				y++;
			}
			double dificuldade = (double)((difficulty * 100));
			this.sudokuBoard = new SudokuBoard(SudokuAux.matrixWithRandomZeros(matriz, dificuldade));
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro a abrir o ficheiro!");
		}
		return;
	}
	
	//---------------------------------BUTOES--------------------------------------------
	
	public void jogar(int linha, int coluna, int valor) {
		if(this.sudokuBoard == null) throw new NullPointerException("Nao existe jogo!");
		if(!this.sudokuBoard.jogar(linha,coluna,valor)) return;
		if(SudokuAux.linhaRepetida(this.sudokuBoard.getSudokuAtual(), linha)) SudokuAux.linhaInvalida(this.boardImage,linha);
		if(SudokuAux.colunaRepetida(this.sudokuBoard.getSudokuAtual(),coluna)) SudokuAux.colunaInvalida(this.boardImage, coluna);
		if(SudokuAux.segmentoRepetido(this.sudokuBoard.getSudokuAtual(), this.sudokuBoard.getSegmento(linha,coluna))) SudokuAux.squareInvalido(this.boardImage, this.sudokuBoard.getSegmento(linha,coluna));
		SudokuAux.colocarNumero(boardImage,valor,coluna,linha);
		if(this.sudokuBoard.concluida()) System.out.println("Ganhou!");
		return;
	}
	
	public void undo() {
		int [] coords = this.sudokuBoard.undo();
		SudokuAux.makeTabuleiroEJogo(this.sudokuBoard.getSudokuAtual(), this.boardImage);
		SudokuAux.alterarNumero(this.boardImage, 0 , coords[1], coords[0] );
		return;
	}
	
	public void resetBoard() {
		this.sudokuBoard.reniciarTabuleiro(this.sudokuBoard.getSudokuInicial());
		paint();
		return;
	}
	
	public void loadGame() throws FileNotFoundException {
		System.out.println("LOAD GAME");
		int [][] matriz = getMatrizLoader();
		this.sudokuBoard = new SudokuBoard(matriz);
		paint();
	}
	
	private int[][] getMatrizLoader() throws FileNotFoundException {
		int [][] matriz = new int[9][9];
		File file = new File(".\\src\\jogo1.sudgame");
		Scanner scanner = new Scanner(file);
		int aux = 1;
		int y = 0;
		int [] values = new int[9*9];
		String matrizString = "";
		while(scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			if (aux < 11) {
				matrizString+=linha + "\n";
			} else {
				aux++;
				continue;
			}
			
		}
		matriz = SudokuAux.stringToMatriz(matrizString);
		scanner.close();
		return matriz;
	}
	
	public int [][] getMatriz1() {
		return this.sudokuBoard.getSudokuAtual();
	}
	
	public int [][] getMatriz2() {
		return this.sudokuBoard.getSudokuInicial();
	}
	
	public void saveGame() throws FileNotFoundException {
		String caminhoFicheiro = ".\\src\\jogo1.sudgame";
        File arquivo = new File(caminhoFicheiro);
        int [][] saveSudokuAtual = this.sudokuBoard.getSudokuAtual();
    	String sudokuAtual = SudokuAux.makeStringWithMatrix(saveSudokuAtual);
        int [][] saveSudokuInicial = this.sudokuBoard.getSudokuInicial();
    	String sudokuInicia = SudokuAux.makeStringWithMatrix(saveSudokuInicial);
        PrintWriter pw = new PrintWriter(arquivo);
    	pw.println(sudokuInicia);
    	pw.print("\n");
        pw.println(sudokuAtual);
        pw.close();
		return;
	}
	
	public void jogarAleatorio() {
		int [] numeros = this.sudokuBoard.jogarAleatorio();
		if (numeros[2] == 0) return;
		SudokuAux.alterarNumero(this.boardImage, numeros[2] , numeros[1],numeros[0] );
		return;
	}
	
	//---------------------------------PINTAR-----------------------------------------------
	
	private void paint() {
		if(this.sudokuBoard == null) throw new NullPointerException("Sudoku e null!");
		SudokuAux.makeTabuleiroEJogo(this.sudokuBoard.getSudokuAtual(), this.boardImage);
		return;
	}
	
}