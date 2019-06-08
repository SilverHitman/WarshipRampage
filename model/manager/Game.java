/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.manager;

import java.util.ArrayList;
import model.entity.Attack;
import model.entity.Player;

/**
 *
 * @author Usuario
 */
public class Game extends Thread {

    private PlayerGameManager []playersToPlay;
    private int turn;

    public Game(PlayerGameManager[] playersToPlay) {
        this.playersToPlay = playersToPlay;
        this.turn = 0;
        verifiedSetPartners();
        setAvaiavality();
        startTurns();
    }

    private void setAvaiavality() {
        for (PlayerGameManager playertoPlay : playersToPlay) {
            playertoPlay.getPlayer().setOnLine(false);
        }
    }

    private void startTurns() {
        playersToPlay[this.turn].setTurn(true);
        this.turn++;
        for (int i = 1; i < playersToPlay.length; i++) {
            playersToPlay[i].setTurn(false);
        }
    }

    public void changeTurn() {
        int lastTurn = this.turn;
        playersToPlay[lastTurn].setTurn(false);
        playersToPlay[this.turn].setTurn(true);
        this.turn++;
        if (this.turn >= playersToPlay.length) {
            this.turn = 0;
        }
    }

    private boolean havePartners() {
        boolean isAllPartners = false;
        for (PlayerGameManager player : playersToPlay) {
            if (player.getTeam() != null) {
                isAllPartners = true;
            } else {
                isAllPartners = false;
                break;
            }
        }
        return isAllPartners;
    }

    private void setPartners() {
        int partnerNumber = 0;
        for (int i = 0; i < playersToPlay.length && !havePartners(); i++) {
            partnerNumber = (int) (Math.random() * playersToPlay.length);
            if (playersToPlay[i] != playersToPlay[partnerNumber]) {
                if (playersToPlay[i].getTeam() != null && playersToPlay[partnerNumber].getTeam() != null) {
                    playersToPlay[i].setTeam(playersToPlay[partnerNumber]);
                    playersToPlay[partnerNumber].setTeam(playersToPlay[i]);
                }
            }
        }
    }

    private void verifiedSetPartners() {
        if (playersToPlay.length % 2 == 0) {
            setPartners();
        }
    }

    public boolean attackASection(PlayerGameManager attacker) {
        boolean attackConfirmation = false;
        if (attacker.isTurn()) {
            for (PlayerGameManager playerTableManager : playersToPlay) {
                if (playerTableManager != attacker) {
                    attackConfirmation = playerTableManager.getMyGameTable().attackSection(attacker.getAttackList().get(attacker.getAttackList().size() - 1));
                }
            }
            changeTurn();
        }
        return attackConfirmation;
    }

    public void givePoints() {
        for (PlayerGameManager playerGameManager : playersToPlay) {
            playerGameManager.getPlayer()
                    .setPoints(playerGameManager.getPlayer().getPoints()
                            + pointsPerAttackOfAPlayer(playerGameManager));
        }
    }

    public int pointsPerAttackOfAPlayer(PlayerGameManager playerGameManager) {
        int pointsPerAttack = 0;
        for (Attack attack : playerGameManager.getAttackList()) {
            if (attack.isImpact()) {
                pointsPerAttack++;
            }
        }
        return pointsPerAttack;
    }

}
