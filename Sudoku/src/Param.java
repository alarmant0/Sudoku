class Param {

	
	// Color pallete
	

	static final Color SEGMENT_GRID_COLOR = new Color (19,41,76);				// Preto grelha segmentos
	static final Color GRID_COLOR = new Color (218,221,228);					// Cinza grelha
	static final Color TEXTCOLOR = new Color (19,41,76);						// Preto numeros
	static final Color OUTLINE_COLOR = new Color (213,70,70);					// Vermelho outline warning
	static final Color SQUARE_BACKCOLOR = new Color (255,255,255);				// Branco 
	
	static final Color PLAY_TEXTCOLOR = new Color (213,91,98);					// Vermelho
	static final Color PLAY_BACKCOLOR = new Color (194,221,248);				// Cyan forte

	static final Color PLAYED_ROW_COL_SEGM_BACKCOLOR = new Color (228,235,242);	// Cyan claro
	static final Color PLAYED_NUMBER_BACKCOLOR = new Color (199,215,232);		// Cyan 
	static final Color REPEATED_NUMBER_BACKCOLOR = new Color (241,209,214);		// Rosa


	// Dimension parameter

	static final int LETTER_SIZE = 20;
	static final int SQUARE_SIZE = 30;
	static final int SPACING = 2;
	static final int GRID_THICKNESS = 1;
	static final int SEGMENTE_GRID_THICKNESS = 2;
	static final double TOTAL_SQUARES = 81;
	static final int SUM_ALL_DIGITS = 45;

	
	static int[][] matrixValida = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 1, 4, 3, 6, 5, 8, 9, 7},
            {3, 6, 5, 8, 9, 7, 2, 1, 4},
            {8, 9, 7, 2, 1, 4, 3, 6, 5},
            {5, 3, 1, 6, 4, 2, 9, 7, 8},
            {6, 4, 2, 9, 7, 8, 5, 3, 1},
            {9, 7, 8, 5, 3, 1, 6, 4, 2}
        };
	static int [][] matrizTeste = {{5, 3, 9, 0, 7, 0, 0, 0, 0},
			{6, 0, 7, 1, 9, 5, 0, 0, 0},
			{8, 9, 8, 0, 0, 0, 0, 6, 0}, 
			{8, 2, 4, 0, 6, 0, 0, 3, 0},
			{4, 7, 6, 8, 5, 3, 9, 2, 1},
			{7, 1, 3, 9, 2, 4, 8, 5, 6},
			{1, 6, 5, 0, 0, 0, 2, 8, 0}, 
			{9, 8, 2, 4, 1, 9, 0, 0, 5},
			{3, 4, 1, 2, 8, 6, 4, 7, 9}};
	
	
}