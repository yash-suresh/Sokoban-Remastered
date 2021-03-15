package sample.engine;


/**
 * this class translates the map designed in 'SampleGame.skb' to actual crates, etc
 * @author Yash Suresh-modified
 */

public enum GameObject
{
    WALL('W'),
    EMPTY('W'),
    FLOOR(' '),
    CRATE('C'),
    DIAMOND('D'),
    KEEPER('S'),
    CRATE_ON_DIAMOND('O'),
    DEBUG_OBJECT('=');

    private final char symbol;

    /**
     * setter
     * @param symbol
     */
    GameObject(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * returns the corresponding object symbol
     * @param c symbol
     * @return WALL or t, which denotes the correct symbol for the object
     */
    public static GameObject fromChar(char c) {
        for (GameObject t : GameObject.values()) {
            if (Character.toUpperCase(c) == t.symbol) {
                return t;
            }
        }

        return WALL;
    }

    /**
     * getter
     * @return the corrresponding symbol
     */
    public char getCharSymbol() {
        return symbol;
    }
}