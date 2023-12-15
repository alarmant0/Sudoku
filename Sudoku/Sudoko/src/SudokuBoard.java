class SudokuBoard {

	private int [][] sudokuAtual;
	private int [] sudokuInit = new int[9*9];
	private int [][] sudokuInicial;
	private int [] coordenadasDasJogadas;
	private int next = 0;
	
	public SudokuBoard(int [][] sudoko) {
		this.sudokuAtual = sudoko;
		this.sudokuInicial = sudoko;
		int aux = 0;
		for(int i = 0;i<sudoko.length;i++) {
			for(int j = 0;j<sudoko[i].length;j++) {
				sudokuInit[aux] = sudoko[i][j];
				aux++;
			}
		}
		int zeros = getZeros();
		this.coordenadasDasJogadas = new int[zeros*2]; // duas coordenadas
	}
	
	//------------------------------GETTERS--------------------------------------------------
	
	public int getNext() {
		return this.next;
	}
	
	public int [] getCoordenadasDasJogadas() {
		return this.coordenadasDasJogadas;
	}
	
	public int [][] getSudokuAtual() {
		return this.sudokuAtual;
	}
	
	public int [][] getSudokuInicial() {
		int [][] inicial = new int[9][9];
		int aux = 0;
		for(int i = 0;i<9;i++) {
			for(int j = 0;j<9;j++) {
				inicial[i][j] = sudokuInit[aux];
				aux++;
			}
		}
		return inicial;
	}
	
	public int getNumero(int x,int y) {
		return this.sudokuAtual[x][y];
	}
	
	private int getZeros() {
		return SudokuAux.howManyZeros(this.sudokuInicial);
	}
	
	public boolean concluida() {
		return SudokuAux.isValid(this.sudokuAtual);
	}

	public int getSegmento(int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) throw new IllegalArgumentException("Coordenadas inválidas");
        int segmentRow = row / 3;
        int segmentCol = col / 3;
        int segmentNumber = segmentRow * 3 + segmentCol;
        return segmentNumber;
 }
	
	//------------------------------JOGAR(ALEATORIO)/UNDO()/RENICIAR--------------------------------------------------

	public boolean jogar(int x,int y, int valor) {
		if(this.sudokuAtual[x][y] != 0) return false;
		this.sudokuAtual[x][y] = valor;
		this.coordenadasDasJogadas[next] = x;
		next++;
		this.coordenadasDasJogadas[next] = y;
		next++;
		return true;
	}
	
	public int [] jogarAleatorio() {
		int [][] coords = findRandomZero();
		int acc = 5;
		int [] ret = new int[3];
		int [][] aux =this.sudokuAtual;
		while(acc >=0) {
			int random1 = (int)(Math.random() * getZeros());
			int random2 = (int)(Math.random() * 9) + 1;
			int segmento = getSegmento(coords[random1][0],coords[random1][1]);
			aux[coords[random1][0]][coords[random1][1]] = random2;
			if(!SudokuAux.segmentoRepetido(aux, segmento)) {
				this.sudokuAtual[coords[random1][0]][coords[random1][1]] = random2;	
				return ret;
			}
		}
		return ret;
	}
	
	public int [] undo() {
		if (next == 0) throw new IllegalStateException("Nao da para dar undo()");
		int [] coords = new  int[2];
		int y = this.coordenadasDasJogadas[next-1];
		coords[1] = y;
		next--;
		int x = this.coordenadasDasJogadas[next-1];
		coords[0] = x;
		next--;
		this.sudokuAtual[x][y] = 0;
		return coords;
	}	
	
	public int [][] reniciarTabuleiro(int [][] matrizInicial) {
		for(int i = 0;i<next;i++) undo();
		this.sudokuAtual = matrizInicial;
		return sudokuAtual;
	}
	
	//---------------------------------LINHAS E COLUNAS INVALIDAS-----------------------------------------------
	
	public int [] getLinhasInvalidas() {
		int [] linhasInvalidas = SudokuAux.linhasRepetidas(this.sudokuAtual);
		for(int i = 0;i<linhasInvalidas.length;i++) {
			if(linhasInvalidas[i] != 0) {
				linhasInvalidas[i] += 1;
			}
		}
		return linhasInvalidas;
	}
	
	public int [] getColunasInvalidas() {
		int [] colunasInvalidas = SudokuAux.colunasRepetidas(this.sudokuAtual);
		for(int i = 0;i<colunasInvalidas.length;i++) {
			if(colunasInvalidas[i] != 0) {
				colunasInvalidas[i] += 1;
			}
		}
		return colunasInvalidas;
	}
	
	//------------------------------FUNCOES AUXILIARES--------------------------------------------------
	
	private int[][] findRandomZero() {
		int aux = 0;
		int [][] zeros = new int[getZeros()][2];
        for(int i = 0;i<this.sudokuAtual.length;i++) {
        	for(int j = 0;j<this.sudokuAtual[i].length;j++) {
        		if(this.sudokuAtual[i][j] == 0) {
        			zeros[aux][0] = i;
        			zeros[aux][1] = j;
        			aux++;
        		}
        	}
        }
        return zeros;
    }
	
}