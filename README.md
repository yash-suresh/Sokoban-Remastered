# COMP2013_CW_psyys5

Redesigning the retro Sokoban Game.
by Yash Suresh: psyys5.

The original game engine is by Stefano Frazzetto. I have refactored, improved and tested this game.

How to run game:
1) Import the file 'src' and open with IDE.
2) In src/sample, click 'Main' class.
3) Run 'main' method. Entry point of program.

Version: Java10: v10.0.2 | JUNIT5

Javadocs path: "SureshYash_IntelliJ_10/javadocs"

Additions:
1) In-game music plays indefinitely. Option to toggle.
2) Start Screens: 4 wall colour choice,4 crate colour choice, plus pictures and custom fonts. Powered by FXML+CSS.
   ![img_3.png](img_3.png)
   
3) Button for Menu -> Game
4) Menu asks for player name, error alert pops up if left blank, and halts progress.
5) Permanent high score list for each level. Created with the help of dynamic file path which checks and/or creates a file for each level, stores the move counter along with name entered in the menu, and finally sorts it.
6) Popup with highscore+player name for each level, move count for current game, name of current player, and option to go to leaderboard for that level. If highscore is beaten, the current player will automatically go on top.
   ![img_6.png](img_6.png)
   

![img_5.png](img_5.png)
8) Sprites for all GraphicObject elements:.
9) 3 levels: 1st is easy and for showing functionality during screencast. 2 and 3 are medium difficulty.
10) ResetLevel(). resets current level for current or any map set.
11) SpaceBar shortcut ->resetLevel()
12) Countdown timer: makes game interesting. Game is lost when time runs out. Pop-up declaring loss shows up.
12) In game display panel, which shows move count, level number, and time remaining. Customised with images, fonts.
    ![img_4.png](img_4.png)
13) Keeper animation. Keeper changes face direction and can be seen 'walking'.
14) If keeper stood for long period, sprite becomes 'rested', for more realism.
15) Default keeper position everytime level reset, or level advanced.
16) Iteration function which leaves grid blank, if surrounded by walls on all 8 sides, to give game more authentic feel.

REFACTORING:
1) Compact, well organised file structure, adhering to MVC.
   
![img_1.png](img_1.png)
2) Use of Factory Pattern in SpriteStore which instantiates sprite images to be used by other classes.
3) Controller class for each scene, and an FXML for each menu.
   ![img.png](img.png)
3) Use of singleton in SoundFX and writer classes.
4) Modified every class, but have classed the core game classes as 'Engine' package.
5) New classes: GameController, Menu(1-3)Controllers, Menu(1-2).fxml, styesheet, writer, SoundFX, SpriteStore
6) Split up 'Main' class into GameController, SoundFX, and writer.
7) Split up GraphicObject,StartMeUp,Level into smaller functions
8) JUNIT tests. All passed.
9) Setters and getters for class variables. Javadoc for every single function. Also have '//' comments, for clearer code comprehension.
10) Comprehensive class diagram

Issues:
1) Tried creating FXML for in-game menu. Too many errors. Did it in Java instead.
2) Changing the name of 'sample' in 'src' gives errors, unresolved even in  Labs. I have left it as 'sample.

Possible improvements:

1) The high score display, leaderboard and pop-up is quite primitive. It was originally not planned to be done, but due to cw deadline extensions, this became an afterthought, when I had more time for DMS. I couldn't customise it like I did my Menus. I had to rush through them in about a day.

2) Couldn't add undo() or saveFile(). Not enough time.

3) Gradle/Maven.

I intend to further improve this project.









