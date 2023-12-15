class SudokuAux {
	
	//------------------------------FUNCOES AUX PARA ISVALID()--------------------------------------------------
	
	public static boolean isValid(int [][] sudoko) {
		if(howManyZeros(sudoko) != 0) return false;
		if(!allLinesValid(sudoko)) return false;
		if(!allColumnsValid(sudoko)) return false;
		if(!allSquaresValid(sudoko)) return false;
		if(numbersRepetidos(sudoko)) return false;
		return true;
	}
	
	public static int howManyZeros(int[][] sudoko) {
		int zeros = 0;
		for(int i = 0;i<sudoko.length;i++) {
			for(int j = 0;j<sudoko[i].length;j++) {
				if (sudoko[i][j] == 0) {
					zeros++;
				}
			}
		}
		return zeros;
	}

	private static boolean allLinesValid(int [][] sudoko) {
		int aux = 0;
		for(int i = 0;i<sudoko.length;i++) {   
			for(int j = 0;j<sudoko[i].length;j++) {
				aux+=sudoko[i][j];
			}
			if(Param.SUM_ALL_DIGITS != aux) return false;
			aux = 0;
		}									   
		return true;
	}
	
	private static boolean allColumnsValid(int [][] sudoko) {
		int aux = 0;
		for(int i = 0;i<sudoko.length;i++) {
			for(int j = 0;j<sudoko[i].length;j++) {
				aux+=sudoko[j][i];
			}
			if(Param.SUM_ALL_DIGITS != aux) return false;
			aux = 0;
		}
		return true;
	}
	
	public static boolean allSquaresValid(int [][] sudoko) {
		int aux = 0;
		for(int i = 0;i<sudoko.length;i++) {
			for(int j = 0;j<sudoko[i].length;j++) {
				aux+=sudoko[i][j];
			}
		}
		return aux == Param.SUM_ALL_DIGITS*9;
	}
	
	private static boolean numbersRepetidos(int[][] matrix) {
		return colunasRepetidas(matrix)[0] != 0 || linhasRepetidas(matrix)[0] != 0;
	}
	
	public static int [] colunasRepetidas(int [][] sudoko) {
		int [] colunasInvalidas = new int [9];
		int acc = 0;
		for(int i = 0;i<sudoko.length;i++) {
			for(int j = 0;j<sudoko.length;j++) {
				int aux = sudoko[j][i];
				for(int x = 0;x<sudoko.length;x++) {
					if (aux == sudoko[x][i] && x != j && aux != 0) {
						colunasInvalidas[acc] = i+1;
						acc++;
					}
				}
			}
		}
		return colunasInvalidas;
	}
	
	public static int [] linhasRepetidas(int[][] sudoko) {
		int acc = 0;
		int [] linhasInvalidas = new int[9];
		for(int i = 0;i<sudoko.length;i++) {
			for(int j = 0;j<sudoko[i].length;j++) {
				int aux = sudoko[i][j];
				for(int x = 0;x<sudoko[i].length;x++) {
					if (aux == sudoko[i][x] && j != x && aux !=0) {
						linhasInvalidas[acc] = i+1;
						acc++;
					}
				}
			}
		}
		return linhasInvalidas;
	}
	
	public static boolean linhaRepetida(int [][] sudoku, int linha) {
		for(int i = 0;i<sudoku[linha].length;i++) {
			int aux = sudoku[linha][i];
			for(int j = 0;j<sudoku[linha].length;j++) {
				if(aux == sudoku[linha][j] && aux != 0 && i!=j) return true;
			}
		}
		return false;
	}
	
	public static boolean colunaRepetida(int[][] sudoku, int coluna) {
		for(int i = 0;i<sudoku[coluna].length;i++) {
			int aux = sudoku[i][coluna];
			for(int j = 0;j<sudoku[coluna].length;j++) {
				if(aux == sudoku[j][coluna] && aux != 0 && i!=j) return true;
			}
		}
		return false;
	}
	
	public static boolean segmentoRepetido(int [][] sudoku, int segmento) {
		int squareLine = segmento / 3; 
		int squareColuna = segmento % 3;
		for(int i = squareLine * 3;i<((squareLine+1)*3);i++) {
			for(int j = squareColuna*3;j<((squareColuna+1)*3);j++) {
				int number = sudoku[i][j];
				for(int x = squareLine * 3;x<((squareLine+1)*3);x++) {
					for(int y = squareColuna*3;y<((squareColuna+1)*3);y++) {
						int number1 = sudoku[x][y];
						if (validCoords(i,j,x,y) && number == number1 && number != 0) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
	
	private static boolean validCoords(int i, int j, int x, int y) {
		return ((i==x && j!=y) || (i!=x && j==y) || (i!=x && j!=y));
	}

	//----------------------------------FUNCOES AUXILIARES----------------------------------------------
		
	public static int [][] matrixWithRandomZeros(int [][] sudoko, double percentagem) {
		if(!isValid(sudoko)) return sudoko;
		int numberOfZeros = (int)(((percentagem/100) * Param.TOTAL_SQUARES));
		int [] posicoesDosZeros = zerosCoords(numberOfZeros);
		for(int i = 0;i<posicoesDosZeros.length - 1;i+=2) {
			int x = posicoesDosZeros[i];
			int y = posicoesDosZeros[i+1];
			sudoko[x][y] = 0;
		}
		return sudoko;
	}
	
	private static int[] zerosCoords(int numberOfZeros) {
		int [] coords = new int[numberOfZeros*2];
		for(int i = 0;i<numberOfZeros*2;i+=2) { // 0-3 (0,0) ( 5,3) (0,0)
			coords[i] = (int) (Math.random() * 9); //[0,9[ x=8
	        coords[i+1] = (int) (Math.random() * 9); // y =7
		}
		return coords;
	}

	public static String makeStringWithMatrix(int [][] sudoko1) {
		String stringMatrix = "";
		for(int i = 0;i<sudoko1.length;i++) {
			for(int j = 0;j<sudoko1[i].length;j++) {
				stringMatrix+=sudoko1[i][j];
				if(j!= sudoko1[i].length -1 ) {
					stringMatrix+=" ";
				}
			}
			if(i!=sudoko1.length-1) stringMatrix+="\n";
		}
		return stringMatrix;
	}
	
	//----------------------DESENHAR O JOGO COM COLUNAS/LINHAS/NUMEROS------------------------------------------------
	
	public static void makeTabuleiroEJogo(int [][] sudoko,ColorImage img) {
		makeAllBackGroundColor(img);
		makeColunas(sudoko,img);
		makeLinhas(img);
		makeContorno(img);
		desenharNumeros(img, sudoko);
		return;
	}
	
	private static void makeAllBackGroundColor(ColorImage img) {
		for(int i = 0;i<img.getWidth();i++) {
			for(int j = 0;j<img.getHeight();j++) {
				img.setColor(i,j,Param.SQUARE_BACKCOLOR);
			}
		}
		return;
	}

	private static void desenharNumeros(ColorImage img, int [][] sudoko) {
		if(img == null) throw new NullPointerException("Imagem nula!");
		for(int i = 0;i<9;i++) {
			for(int j = 0;j<9;j++) {
				if(sudoko[i][j] != 0) colocarNumero(img, sudoko[i][j],j,i);
			}
		}
		return;
	}	
	
	private static void makeColunas(int [][] sudoko, ColorImage img) {
		for(int i = Param.SQUARE_SIZE;i<img.getWidth();i+=Param.SQUARE_SIZE) {
			for(int j = 0;j<img.getHeight();j++) {
				if(i == 90 || i == 180) {
					for(int x = i;x<i+Param.SEGMENTE_GRID_THICKNESS;x++) {
						img.setColor(i,j,Param.SEGMENT_GRID_COLOR);
					}
				} else {
					for(int x = i;x<i+Param.GRID_THICKNESS;x++) {
						img.setColor(i,j,Param.PLAYED_NUMBER_BACKCOLOR);
					}
				}
			}
		}
		return;
	}

	private static void makeLinhas(ColorImage img) {
		for(int j = Param.SQUARE_SIZE;j<img.getHeight();j+=Param.SQUARE_SIZE) {
			for(int i = 0;i<img.getWidth();i++) {
				if( j == 90 || j == 180) {
					for(int x = j;x<j+Param.SEGMENTE_GRID_THICKNESS;x++) {
						img.setColor(i,j,Param.SEGMENT_GRID_COLOR);
					}
				} else if (!((i >= 90 && i<=90) || ( i>= 180 &&  i<=180))) {
					img.setColor(i,j,Param.PLAYED_NUMBER_BACKCOLOR);
				}
			}
		}
		return;
	}

	private static void makeContorno(ColorImage img) {
		for(int i = 0;i<img.getWidth();i++) {
			for(int j = 0;j<img.getHeight();j++) {
				if(i <= Param.SEGMENTE_GRID_THICKNESS || i >= img.getWidth()-Param.SEGMENTE_GRID_THICKNESS 
						|| j <=Param.SEGMENTE_GRID_THICKNESS || j >=img.getHeight() - Param.SEGMENTE_GRID_THICKNESS) {
					img.setColor(i,j, Param.SEGMENT_GRID_COLOR);
				}
			}
		}
		return;
	}
	
	//------------------------------COLOCAR/ALTERAR NUMERO--------------------------------------------------

	public static void colocarNumero(ColorImage img, int numero, int x, int y) {
		int numberOfSquareX = x * Param.SQUARE_SIZE;
        int numberOfSquareY = y * Param.SQUARE_SIZE;
        int centerTextX = numberOfSquareX  + Param.SQUARE_SIZE / 2;
        int centerTextY = numberOfSquareY + Param.SQUARE_SIZE / 2;
        if(numero == 0) {
    		img.drawCenteredText(centerTextX,centerTextY, "", Param.LETTER_SIZE, Param.TEXTCOLOR);
    		return;
        }
		img.drawCenteredText(centerTextX,centerTextY, String.valueOf(numero), Param.LETTER_SIZE, Param.TEXTCOLOR);
		return;
	}
	
	public static void alterarNumero(ColorImage img, int numero, int x, int y) {
		for(int i = (Param.SQUARE_SIZE * x) + Param.SEGMENTE_GRID_THICKNESS +1 ;i<(Param.SQUARE_SIZE * (x +1)) - Param.SEGMENTE_GRID_THICKNESS-1;i++) {
			for(int j = (Param.SQUARE_SIZE * y) + Param.SEGMENTE_GRID_THICKNESS + 1;j<(Param.SQUARE_SIZE * (y+1)) - Param.SEGMENTE_GRID_THICKNESS -1;j++) {
				img.setColor(i,j, Param.SQUARE_BACKCOLOR);
			}
		}
		colocarNumero(img, numero, x, y);
	}
	
	//---------------------------------COLUNA/LINHA/SQUARE INVALIDO-----------------------------------------------
	
	public static void colunaInvalida(ColorImage img, int linha) {
		for(int i = linha * Param.SQUARE_SIZE;i<Param.SQUARE_SIZE*(linha+1);i+=Param.SQUARE_SIZE) {
			for(int j = 0;j<img.getHeight();j++) {
				for(int x = 0;x<Param.SEGMENTE_GRID_THICKNESS + 1;x++) {
					img.setColor(i+x,j, Param.OUTLINE_COLOR);
					img.setColor(i-x+Param.SQUARE_SIZE -1,j,Param.OUTLINE_COLOR);
				}
			}
		}
		for(int k = linha * Param.SQUARE_SIZE;k<Param.SQUARE_SIZE *(linha+1);k++) {
			for(int x = 0;x<Param.SEGMENTE_GRID_THICKNESS + 1;x++) {
				img.setColor(k,x,Param.OUTLINE_COLOR);
				img.setColor(k, img.getHeight() - x - 1, Param.OUTLINE_COLOR);
			}
		}
		return;
	}
	
	public static void linhaInvalida(ColorImage img, int coluna) {
		for(int j = coluna * Param.SQUARE_SIZE;j<Param.SQUARE_SIZE*(coluna+1);j+=Param.SQUARE_SIZE) {
			for(int i = 0;i<img.getWidth();i++) {
				for(int x = 0;x<Param.SEGMENTE_GRID_THICKNESS + 1;x++) {
					img.setColor(i,j+x, Param.OUTLINE_COLOR);
					img.setColor(i,j-x+Param.SQUARE_SIZE - 1, Param.OUTLINE_COLOR);
				}
			}
		}
		for(int k = coluna * Param.SQUARE_SIZE;k<Param.SQUARE_SIZE * (coluna+1);k++) {
			for(int x = 0;x<Param.SEGMENTE_GRID_THICKNESS + 1;x++) {
				img.setColor(x,k,Param.OUTLINE_COLOR);
				img.setColor(img.getWidth() - x - 1, k, Param.OUTLINE_COLOR);
			}
		}
		return;
	}
	
	public static void squareInvalido(ColorImage img , int quadrado) {
		if(quadrado > 8 ) throw new IllegalArgumentException("Segmento inválido");
		int squareLine = quadrado / 3; 
		int squareColuna = quadrado % 3; 
		for(int i = squareLine* Param.SQUARE_SIZE * 3;i<Param.SQUARE_SIZE*3*(squareLine+1);i++) {
			for(int x = 0;x<Param.SEGMENTE_GRID_THICKNESS+1;x++) {
				img.setColor((squareColuna*3*Param.SQUARE_SIZE)+x,i,Param.OUTLINE_COLOR);
				img.setColor(((squareColuna+1)*3*Param.SQUARE_SIZE) - 1-x,i,Param.OUTLINE_COLOR);
			}
		}
		for(int j = squareColuna*Param.SQUARE_SIZE * 3;j<Param.SQUARE_SIZE*3*(squareColuna+1);j++) {
			for(int x = 0;x<Param.SEGMENTE_GRID_THICKNESS+1;x++) {
				img.setColor(j,(squareLine*3*Param.SQUARE_SIZE)+x, Param.OUTLINE_COLOR);
				img.setColor(j, ((squareLine+1)*3*Param.SQUARE_SIZE)-x-1, Param.OUTLINE_COLOR);
			}
		}
	}

	public static int[][] stringToMatriz(String matrizString) {
		int [][] matriz = new int[9][9];
		

		return matriz;
	}

	
	//--------------------------------------------------------------------------------

}