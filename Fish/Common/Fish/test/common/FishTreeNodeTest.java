package common;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import common.models.FishModel;
import common.models.FishState;
import common.models.FishTreeNode;
import common.models.PenguinColor;
import common.models.PlayerInfo;
import org.junit.Before;
import org.junit.Test;

/**
 * a test class for FishTreeNode class
 */
public class FishTreeNodeTest {

  private int width1;
  private int height1;
  private int width2;
  private int height2;
  private int maxFishNum1;
  private boolean isRandom;
  private FishModel fourByEightFishModel;
  private FishModel oneByThreeFishModel;
  private PlayerInfo playerInfoRed;
  private PlayerInfo playerInfoBlack;
  private PlayerInfo playerInfoWhite;
  private ArrayList<PlayerInfo> twoPlayerInfos;
  private ArrayList<PlayerInfo> threePlayerInfos;
  private FishState gameState1;
  private FishState gameState2;


  @Before
  public void init() {

    width1 = 4;
    height1 = 8;
    maxFishNum1 = 1;
    isRandom = true;
    fourByEightFishModel = new FishModel(width1, height1, maxFishNum1, isRandom);
    width2 = 1;
    height2 = 3;
    oneByThreeFishModel = new FishModel(width2, height2, maxFishNum1, isRandom);
    playerInfoRed = new PlayerInfo(11, PenguinColor.red);
    playerInfoBlack = new PlayerInfo(12, PenguinColor.black);
    playerInfoWhite = new PlayerInfo(13, PenguinColor.white);
    twoPlayerInfos = new ArrayList<PlayerInfo>();
    twoPlayerInfos.add(playerInfoRed);
    twoPlayerInfos.add(playerInfoBlack);
    threePlayerInfos = new ArrayList<PlayerInfo>();
    threePlayerInfos.add(playerInfoRed);
    threePlayerInfos.add(playerInfoBlack);
    threePlayerInfos.add(playerInfoWhite);
    gameState1 = new FishState(fourByEightFishModel, threePlayerInfos);
    gameState2 = new FishState(oneByThreeFishModel, twoPlayerInfos);
  }

  @Test
  public void needMorePenguinsOnBoard() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    try {
      FishTreeNode tree = new FishTreeNode(null, nextState3);
    } catch (IllegalArgumentException e){
      assertEquals("Error: incomplete number of penguins for each Player",e.getMessage());
    }

  }

  @Test
  public void needMorePenguinsOnBoard1() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    try {
      FishTreeNode tree = new FishTreeNode(null, nextState8);
    } catch (IllegalArgumentException e){
      assertEquals("Error: incomplete number of penguins for each Player",e.getMessage());
    }

  }

  @Test
  public void createCurrentLayerTree() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    ArrayList<FishState> fishStates = tree.getDirectReachableStates();
    assertEquals(20, fishStates.size());

  }

  @Test
  public void createNextLayerTreeNodes() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    ArrayList<FishState> fishStates = tree.getDirectReachableStates();
    tree.generateChildNodes();
    ArrayList<FishTreeNode> treeNodes = tree.getChildNodes();
    assertEquals(20, treeNodes.size());

  }

  @Test
  public void createNextTwoLayerTreeNodes() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    ArrayList<FishState> fishStates = tree.getDirectReachableStates();
    tree.generateChildNodes();
    ArrayList<FishTreeNode> treeNodes = tree.getChildNodes();
    ArrayList<FishTreeNode> nextTreeNodes = new ArrayList<>();
    for (FishTreeNode node: treeNodes){
      node.generateChildNodes();
      ArrayList<FishTreeNode> nextNodes = node.getChildNodes();
      nextTreeNodes.addAll(nextNodes);
    }
    assertEquals(369, nextTreeNodes.size());
  }

//  @Test
//  public void actOnFishStateSucceed() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(nextState3.getCurrentPlayerIndex());
//    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerRed);
//
//    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
//    playerBlack = players4.get(nextState4.getCurrentPlayerIndex());
//    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerBlack);
//
//    ArrayList<Player> players5 = nextState5.getPlayersSortedByAgeAscend();
//    playerWhite = players5.get(nextState5.getCurrentPlayerIndex());
//    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerWhite);
//
//    ArrayList<Player> players6 = nextState6.getPlayersSortedByAgeAscend();
//    playerRed = players6.get(nextState6.getCurrentPlayerIndex());
//    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerRed);
//
//    ArrayList<Player> players7 = nextState7.getPlayersSortedByAgeAscend();
//    playerBlack = players7.get(nextState7.getCurrentPlayerIndex());
//    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerBlack);
//
//    ArrayList<Player> players8 = nextState8.getPlayersSortedByAgeAscend();
//    playerWhite = players8.get(nextState8.getCurrentPlayerIndex());
//    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerWhite);
//
//
//    FishTreeNode tree = new FishTreeNode(null, nextState9);
//    int currentPlayerIndex = nextState9.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState9.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players9 = nextState9.getPlayersSortedByAgeAscend();
//    playerRed = players9.get(currentPlayerIndex);
//
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinRed, playerRed);
//
//    assertEquals(2, penguinRed.getXPos());
//    assertEquals(2, penguinRed.getYPos());
//    FishState nextState = tree.applyActionToState(tree, moveAction);
//    penguinRed = nextState.getPenguinsOnBoard().get(0);
//    assertEquals(2, penguinRed.getXPos());
//    assertEquals(4, penguinRed.getYPos());
//  }

//  @Test
//  public void actOnFishStateFailed() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(nextState3.getCurrentPlayerIndex());
//    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerRed);
//
//    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
//    playerBlack = players4.get(nextState4.getCurrentPlayerIndex());
//    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerBlack);
//
//    ArrayList<Player> players5 = nextState5.getPlayersSortedByAgeAscend();
//    playerWhite = players5.get(nextState5.getCurrentPlayerIndex());
//    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerWhite);
//
//    ArrayList<Player> players6 = nextState6.getPlayersSortedByAgeAscend();
//    playerRed = players6.get(nextState6.getCurrentPlayerIndex());
//    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerRed);
//
//    ArrayList<Player> players7 = nextState7.getPlayersSortedByAgeAscend();
//    playerBlack = players7.get(nextState7.getCurrentPlayerIndex());
//    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerBlack);
//
//    ArrayList<Player> players8 = nextState8.getPlayersSortedByAgeAscend();
//    playerWhite = players8.get(nextState8.getCurrentPlayerIndex());
//    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerWhite);
//
//
//    FishTreeNode tree = new FishTreeNode(null, nextState9);
//    int currentPlayerIndex = nextState9.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState9.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players9 = nextState9.getPlayersSortedByAgeAscend();
//    playerRed = players9.get(currentPlayerIndex);
//
//    MovePenguinAction moveAction = new MovePenguinAction(3, 2, penguinRed, playerRed);
//
//    try {
//      FishState nextState = tree.applyActionToState(tree, moveAction);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: Invalid position to move to.", e.getMessage());
//    }
//
//
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void applyFunctionToStatesFailed() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(nextState3.getCurrentPlayerIndex());
//    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerRed);
//
//    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
//    playerBlack = players4.get(nextState4.getCurrentPlayerIndex());
//    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerBlack);
//
//    ArrayList<Player> players5 = nextState5.getPlayersSortedByAgeAscend();
//    playerWhite = players5.get(nextState5.getCurrentPlayerIndex());
//    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerWhite);
//
//    ArrayList<Player> players6 = nextState6.getPlayersSortedByAgeAscend();
//    playerRed = players6.get(nextState6.getCurrentPlayerIndex());
//    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerRed);
//
//    ArrayList<Player> players7 = nextState7.getPlayersSortedByAgeAscend();
//    playerBlack = players7.get(nextState7.getCurrentPlayerIndex());
//    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerBlack);
//
//    ArrayList<Player> players8 = nextState8.getPlayersSortedByAgeAscend();
//    playerWhite = players8.get(nextState8.getCurrentPlayerIndex());
//    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerWhite);
//
//
//    FishTreeNode tree = new FishTreeNode(null, nextState9);
//    int currentPlayerIndex = nextState9.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState9.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players9 = nextState9.getPlayersSortedByAgeAscend();
//    playerRed = players9.get(currentPlayerIndex);
//
//    MovePenguinAction moveAction = new MovePenguinAction(3, 2, penguinRed, playerRed);
//
//    ArrayList<FishState> nextState = tree.applyActionToStates(tree, moveAction);
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void applyFunctionToStatesFailed1() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(nextState3.getCurrentPlayerIndex());
//    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerRed);
//
//    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
//    playerBlack = players4.get(nextState4.getCurrentPlayerIndex());
//    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerBlack);
//
//    ArrayList<Player> players5 = nextState5.getPlayersSortedByAgeAscend();
//    playerWhite = players5.get(nextState5.getCurrentPlayerIndex());
//    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerWhite);
//
//    ArrayList<Player> players6 = nextState6.getPlayersSortedByAgeAscend();
//    playerRed = players6.get(nextState6.getCurrentPlayerIndex());
//    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerRed);
//
//    ArrayList<Player> players7 = nextState7.getPlayersSortedByAgeAscend();
//    playerBlack = players7.get(nextState7.getCurrentPlayerIndex());
//    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerBlack);
//
//    ArrayList<Player> players8 = nextState8.getPlayersSortedByAgeAscend();
//    playerWhite = players8.get(nextState8.getCurrentPlayerIndex());
//    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerWhite);
//
//
//    FishTreeNode tree = new FishTreeNode(null, nextState9);
//    int currentPlayerIndex = nextState9.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState9.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players9 = nextState9.getPlayersSortedByAgeAscend();
//    playerRed = players9.get(currentPlayerIndex);
//
//    MovePenguinAction moveAction = new MovePenguinAction(-30, 2, penguinRed, playerRed);
//
//    ArrayList<FishState> nextState = tree.applyActionToStates(tree, moveAction);
//
//  }

  @Test
  public void noParentNode() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    assertEquals(null, tree.getParentNode());
  }

  @Test
  public void parentNode1() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    tree.generateChildNodes();
    for (FishTreeNode node : tree.getChildNodes()){
      assertEquals(tree, node.getParentNode());
    }
  }

  @Test
  public void currentState() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    assertEquals(nextState9, tree.getCurrentState());
  }

  @Test
  public void currentState1() {
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
    FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
    FishState nextState6 = nextState5.placeInitPenguin(3, 6, playerInfoWhite);

    ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
    playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
    FishState nextState7 = nextState6.placeInitPenguin(1, 5, playerInfoRed);

    ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
    playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
    FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerInfoBlack);

    ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
    playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
    FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerInfoWhite);


    FishTreeNode tree = new FishTreeNode(null, nextState9);
    tree.generateChildNodes();
    FishState firstConnectedState = tree.getDirectReachableStates().get(0);
    FishState firstNodeCurrentState = tree.getChildNodes().get(0).getCurrentState();
    assertEquals(firstConnectedState, firstNodeCurrentState);
  }






}
