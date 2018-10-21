package com.game.components.maps;

import com.game.components.RenderComponents;
import com.game.constants.EntityType;
import com.game.components.entities.Entity;
import com.game.components.input.impl.UserMovementConsole;
import com.game.components.interactions.InteractionFactory;
import com.game.constants.MessageConstants;
import com.game.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.game.messages.MessageUtil.getMessage;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class GameMap implements RenderComponents {

    private final UserMovementConsole userMovementInput;
    private final InteractionFactory interactionFactory;
    private boolean isMapPaused;
    private boolean isGameExited;

    private final List<List<MapBlock>> mapBlocks = new ArrayList<>();

    public GameMap(UserMovementConsole userMovementInput, InteractionFactory interactionFactory){
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

    @Override
    public void render(){
        System.out.println(prepareUpperLineSeparator(mapBlocks.size()));
        mapBlocks.forEach(this::drawLine);
        System.out.println(prepareLowerLineSeparator(mapBlocks.size()));
    }

    private void drawLine(List<MapBlock> blocks) {
        System.out.print('|');
        blocks.forEach(this::drawEntity);
        System.out.println('|');
    }

    private void drawEntity(MapBlock block) {
        switch (block.getBlockEntityType()) {
            case PLAYER: {
                if(block.getEntity().isAlive()){
                    System.out.print(" P ");
                }
                else{
                    System.out.print(" X ");
                }
            }
                break;
            case ENEMY:
                    System.out.print(" E ");
                    break;
            default:
                System.out.print(" _ ");
        }
    }

    private String prepareLowerLineSeparator(int numberEntitiesInLine) {
        return generate(() -> "-").limit(numberEntitiesInLine + 18).collect(joining());
    }
    private String prepareUpperLineSeparator(int numberEntitiesInLine) {
        return generate(() -> "-").limit((numberEntitiesInLine/2) + 8).collect(joining())
                +"MAP"
                +generate(() -> "-").limit((numberEntitiesInLine/2) + 7).collect(joining());
    }


    List<List<MapBlock>> getMapBlocks() {
        return mapBlocks;
    }

    public boolean isPlayerAlive() {
        return blocks().anyMatch(b -> b.getEntity()!=null && b.getEntity().getType() == EntityType.PLAYER &&  b.getEntity().isAlive());
    }

    public boolean tasksLeft() {
        return blocks().anyMatch(b -> b.getEntity()!=null && b.getEntity().getType() != EntityType.PLAYER);
    }

    private Stream<MapBlock> blocks() {
        return mapBlocks.stream().flatMap(List::stream);
    }

    public void goToNextTurn() {
        MapBlock playerBlock = findPlayerBlock();
        Position nextPosition = userMovementInput.getNextPosition(playerBlock.getPosition());
        if(isGamePaused(nextPosition)){return;}
        if (isValid(nextPosition)) {
            moveUser(playerBlock.getPosition(), nextPosition);
        }
    }

    private boolean isGamePaused(Position nextPosition) {
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
                System.out.println(getMessage(MessageConstants.XP_MESSAGE,user.getName(),user.getExperience()));
            }
        }
    }

    private MapBlock getBlockByPosition(Position position) {
        return mapBlocks.get(position.getX()).get(position.getY());
    }

    public MapBlock findPlayerBlock(){
        return blocks().filter(b -> b.getEntity()!=null && b.getEntity().getType() == EntityType.PLAYER &&  b.getEntity().isAlive()).findFirst().
                orElseThrow(() -> new IllegalStateException(getMessage(MessageConstants.UNABLE_TO_FIND_PLAYER)));
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
