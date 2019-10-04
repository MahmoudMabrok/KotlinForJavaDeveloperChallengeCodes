package board


fun createSquareBoard(mWidth: Int): SquareBoard {
    return object : SquareBoard{
        var cells = arrayOf( arrayOf<Cell>(), arrayOf<Cell>())
        init {
           cells =  arrayOf( arrayOf<Cell>(), arrayOf<Cell>())
        }
        override val width: Int
            get() = mWidth

        override fun getCellOrNull(i: Int, j: Int): Cell? {
       }

        override fun getCell(i: Int, j: Int): Cell {
        }

        override fun getAllCells(): Collection<Cell> {
        }

        override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        }

        override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        }

        override fun Cell.getNeighbour(direction: Direction): Cell? {
        }

    }
}
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

