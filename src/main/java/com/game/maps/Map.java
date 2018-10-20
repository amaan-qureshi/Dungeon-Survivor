package com.game.maps;

import com.game.constants.EntityType;
import com.game.entities.Entity;
import com.game.input.UserMovementInput;
import com.game.interactions.InteractionFactory;
import com.game.util.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class Map implements Serializable{

    private final UserMovementInput userMovementInput;
    private final InteractionFactory interactionFactory;
    private boolean isMapPaused;
    private boolean isGameExited;

    private final List<List<MapBlock>> mapBlocks = new ArrayList<>();
    public Map(UserMovementInput userMovementInput, InteractionFactory interactionFactory){
        this.userMovementInput = userMovementInput;
        this.interactionFactory = interactionFactory;

        int rowMapSize = 8;
        int colMapSize = 8;
        for(int i =0; i<rowMapSize ; i++){
            List<MapBlock> mapBlocksList = new ArrayList<>();
            for(int j=0; j<colMapSize ; j++){
                MapBlock mapBlock = new MapBlock();
                mapBlock.setPosition(new Position(i,j));
                mapBlocksList.add(mapBlock);
            }
            mapBlocks.add(mapBlocksList);
        }
    }

    public void render(){
        String lineSeparator = prepareLineSeparator(mapBlocks.size());
        System.out.println("MAP");
        System.out.println(lineSeparator);
        mapBlocks.forEach(this::drawLine);
        System.out.println(lineSeparator);

    }

    private void drawLine(List<MapBlock> blocks) {
        System.out.print(CELL_SEPARATOR);
        blocks.forEach(this::drawEntity);
        System.out.println(CELL_SEPARATOR);
    }

    private void drawEntity(MapBlock block) {
        switch (block.getBlockEntityType()) {
            case PLAYER:
                    System.out.print(" P ");
                break;
            case ENEMY:
                    System.out.print(" E ");
                    break;
            default:
                System.out.print(" _ ");
        }
    }

    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 18;

    private String prepareLineSeparator(int numberEntitiesInLine) {
        return generate(() -> "-").limit(numberEntitiesInLine + MAP_SIZE_MARGIN).collect(joining());
    }

    public List<List<MapBlock>> getMapBlocks() {
        return mapBlocks;
    }

    public boolean isPlayerAlive() {
        return blocks().anyMatch(b -> b.getEntity()!=null && b.getEntity().getType() == EntityType.PLAYER &&  b.getEntity().isAlive());
    }

    private Stream<MapBlock> blocks() {
        return mapBlocks.stream().flatMap(List::stream);
    }

    public void goToNextIteration() {

        Position currentPosition = blocks().filter(b -> b.getEntity()!=null && b.getEntity().getType() == EntityType.PLAYER &&  b.getEntity().isAlive()).findFirst().
                orElseThrow(() -> new IllegalStateException("It is impossible to continue game when no user character on the map")).getPosition();

        Position nextPosition = userMovementInput.getNextPosition(currentPosition);

        if(gamePaused(nextPosition)){return;}

        System.out.println(nextPosition);
        if (isValid(nextPosition)) {
            System.out.print("Move Player");
            moveUser(currentPosition, nextPosition);
        }
        else{
            System.out.print("Invalid Position");
        }

    }

    private boolean gamePaused(Position nextPosition) {
        if(nextPosition.getY()==99 && nextPosition.getX()==99){
            this.isMapPaused = true;
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isValid(Position nextPosition) {
        return nextPosition.getX() >= 0 && nextPosition.getX()<=7 && nextPosition.getY() >=0 && nextPosition.getY() <=7;
    }

    private void moveUser(Position currentPosition, Position nextPosition) {

        MapBlock currentBlock = getBlockByPosition(currentPosition);
        MapBlock nextBlock = getBlockByPosition(nextPosition);

        Entity user = currentBlock.getEntity();

        if(nextBlock.getEntity()==null){
            currentBlock.setEntity(null);
            nextBlock.setEntity(user);
        }
        else{
            interactionFactory.getInteraction(nextBlock.getEntity().getType()).interact(user,nextBlock.getEntity());

            if(user.isAlive()){
                currentBlock.setEntity(null);
                nextBlock.setEntity(user);
            }
            else{
                System.out.print("Final User XP : " + user.getExperience());
            }
        }
    }

    private MapBlock getBlockByPosition(Position position) {
        return mapBlocks.get(position.getX()).get(position.getY());
    }

    public boolean isMapPaused() {
        return isMapPaused;
    }

    public void setMapPaused(boolean mapPaused) {
        isMapPaused = mapPaused;
    }

    public boolean isGameExited() {
        return isGameExited;
    }

    public void setGameExited(boolean gameExited) {
        isGameExited = gameExited;
    }
}
