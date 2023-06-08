package libgdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;

public class TouchHandler {
    private StageNew stage;
    private Table table;
    private Actor target;
    private Card targetCardOld;
    private Card targetCardNew;
    private Button endTurnButton;
    private PlayerPerson attackedPerson;
    private Watcher watcher;
    private Vector2 tempCoords = new Vector2();
    private float distX;
    private float distY;
    private float posX;
    private float posY;
    public TouchHandler(StageNew stage, Table table) {
        this.stage = stage;
        this.table =table;
    }
    public void touchDownHandler(int screenX, int screenY){
        stage.screenToStageCoordinates(tempCoords.set(screenX, screenY));
        target = stage.hit(tempCoords.x, tempCoords.y, true);
        targetCardOld = null;
        targetCardNew = null;
        endTurnButton = null;
        attackedPerson = null;
        if(target != null && target.getName().equals("card") ) {
            targetCardOld = (Card) target;
            if (targetCardOld.isPlayer() == table.getCurrentPlayer().isPlayer()) {
                posX = targetCardOld.getX();
                posY = targetCardOld.getY();
                targetCardOld.setWidth(86);
                targetCardOld.setHeight(86);
                distX = tempCoords.x - targetCardOld.getX();
                distY = tempCoords.y - targetCardOld.getY();
            }
            else {
                targetCardOld = null;
            }
        }
        else if(target != null && target.getName().equals("button") ) {
            endTurnButton = (Button) target;
            if (endTurnButton.isPlayer() == table.getCurrentPlayer().isPlayer()) {
                endTurnButton.setWidth(110);
                endTurnButton.setHeight(110);
            } else {
                endTurnButton = null;
            }
        } else if (target != null && target.getName().equals("dialogbutton") ) {
            DialogButton dialogButton;
            dialogButton = (DialogButton) target;
            table.dialogButtonHandler(dialogButton);
        } else if (target != null && target.getName().equals("watcher") ) {
            watcher = (Watcher) target;
            watcherHandler();
        }
    }
    public void touchUpHandler(int screenX, int screenY) {
        if (targetCardOld != null && targetCardOld.getName().equals("card") && table.getRetakeCards() < 4 && table.getCurrentPlayer().isPlayer() && table.getTurncounter()<2 ){
            table.newRandomCard(targetCardOld);
        }
        if (targetCardOld != null && targetCardOld.getName().equals("card")) {
            targetCardOld.setWidth(64);
            targetCardOld.setHeight(72);
            targetCardOld.setX(posX);
            targetCardOld.setY(posY);
            target = stage.hit(tempCoords.x, tempCoords.y, false);
            if (target != null && target.getName().equals("card")) {
                targetCardNew = (Card) target;
                table.attackCardUnderMouse(targetCardOld, targetCardNew);
            } else if (target != null && target.getName().equals("table")) {
                table.playOnTableUnderMouse(screenX, screenY, targetCardOld);
            } else if (target != null && (target.getName().equals("player") || target.getName().equals("enemy"))) {
                attackedPerson = (PlayerPerson) target;
                table.attackPlayerUnderMouse(targetCardOld,attackedPerson);
            }
        } else if (endTurnButton != null && endTurnButton.getName().equals("button") && (endTurnButton.isPlayer() == table.getCurrentPlayer().isPlayer())) {
            endTurnButton.setWidth(100);
            endTurnButton.setHeight(100);
            table.endTurn();
        }
    }
    public void touchDraggedHandler(int screenX, int screenY) {
        stage.screenToStageCoordinates(tempCoords.set(screenX, screenY));
        if (targetCardOld != null && targetCardOld.getName().equals("card") ) {
            targetCardOld.setX(tempCoords.x - distX);
            targetCardOld.setY(tempCoords.y - distY);
        }
    }
    public void watcherHandler(){
        watcher.setTouchable(Touchable.disabled);
        watcher.setFontMessage("IT WATCH");
        Timer timer = new Timer();
        Timer.Task timerTask = new Timer.Task() {
            @Override
            public void run() {
                watcher.remove();
            }
        };
        timer.scheduleTask(timerTask, 1);
    }
}
