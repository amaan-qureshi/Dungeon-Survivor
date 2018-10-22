# Dungeon Survivor - Turn Based RPG

## Game Installation and Running

### Build Requirements
* maven
* jdk 1.8

### Building the game
To build the project please execute the following command
```
mvn clean install
```

To build the project reports(Cobertura/FindBugs/PMD) please execute the following command
```
mvn clean install site
```
reports can be found in 
* \target\site\cobertura\index.html
* \target\site\pmd.html
* \target\site\findbugs.html

### Running the Game
After the project is built please run the following command to launch the game
```
java -jar .\target\Fantasy-RPG-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Playing the Game
`
* Dungeon Survivor is a survival game where the objective is to explore the map, kill all enemies and earn XP. The game finishes once all enemies on a map are dead
* For input :  A menu appears every time an interaction is available. To select an option press the number associated to that option and press the enter key
* On starting a new game the game difficulty menu appears. The difficulty level decides the number of enemies randomly positioned on the map
* The name of your player has to be entered after that and press enter to confirm.
* Character class has to be chosen after that:
    Each character has different attributes:
    1. HP =  health of character
    2. POWER = maximum strength with which player can attack
    3. ARMOUR = maximum shield with which player can defend
* The game begins after that. On the map <b>P</b> indicates location of player and <b>E</b> indicates enemies
* You can move around the map by using the displayed options.
* To go back to main menu to save or to start a new game press 5 and then enter.
* You can not go to the main menu while a battle is in progress. Only during roaming mode.
* To enter into a battle:  occupy the same block as the enemy.
* The game will automatically enter battle mode.
* You have option to attack the enemy or defend.
* Every attack or defend has a randomness associated with it to keep the game interesting.
* Maximum attack power is equal to the POWER of your character and the real attack power is equal to POWER times (0.1 to 1)
* Same goes for blocking
* After the enemy is killed the game game goes back to the roaming mode.
* Once all the enemies are killed and map is cleared the game is Won and final XPs earned is shown         
`
