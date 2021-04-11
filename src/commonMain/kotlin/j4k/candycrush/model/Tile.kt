package j4k.candycrush.model

/**
 * A single tile which can be placed on the [GameField].
 */
enum class Tile {

    A, B, C, D, E,
    /**
     * A wall which blocks falling stones. Not used.
     */
    Wall,
    /**
     * An empty field.
     */
    Hole,
    /**
     * The Tile is not present. Happens if Tile coordinates are out of the [GameField].
     */
    OutOfSpace;

    val index = ordinal

    fun shortName(): String = name.first().toString()

    fun isWall() = this == Wall

    fun isHole() = this == Hole

    fun isTile() = !isWall() && !isHole() && !isOutOfSpace()

    fun isNotTile() = !isTile()

    fun isOutOfSpace() = this == OutOfSpace

    fun isEmpty() = !isHole()

    companion object {

        private val toTile = mutableMapOf<String, Tile>()

        init {
            values().forEach { toTile[it.shortName()] = it }
        }

        fun getTile(index: Int): Tile {
            if (index > values().size) {
                throw IllegalArgumentException("Tile index $index > ${values().size} is not available")
            }
            val tile = values()[index]
            if (tile.isNotTile()) {
                throw IllegalArgumentException("Tile $tile for index $index is not a tile")
            }
            return tile
        }

        fun getTile(shortName: String): Tile {
            return toTile[shortName] ?: throw IllegalArgumentException("Failed finding tile for'$shortName'")
        }

        fun randomTile() = values().filter { it.isTile() }.random()

    }


}