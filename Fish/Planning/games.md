###Design Task

Our data representation to enable two different mechanisms such as rule checking and planning a player’s next move will be represented in a TreeNode class. The class will be used for constructing a tree of all posible moves, which means a tile has multiple possible moves to the other tiles, and the other tiles also has other possible moves so that every possible move can be used in constructing our tree.
    
    public class TreeNode<Tile> implements Iterable<TreeNode<Tile>> {

    //Tile information of the node.
    Tile tile; 

    //Its parent TreeNode.
    TreeNode<Tile> parent; 

    //The child TreeNodes that this TreeNode connects to.
    ArrayList<TreeNode<Tile>> children; 

    }

The TreeNode class will implement an Iterable<TreeNode\<Tile>>, so we could traverse through each branch of the tree and update the whole tree when a certain movement happens within a certain game. The class will have several methods that will allow to update when there are movement happening on the board, like deleting a TreeNode and adding a TreeNode. The players will plan their movements through traversing through the TreeNode and analyze whether they’re able to get the most amount of fish. The TreeNode will be used by the referee as a way to reference whether the movements that a player will be making is within the ArrayList<TreeNode\<Tile>>, which is the rule checking. The TreeNode class will be updated when there are movements happening within a FishGameState, these movements will call on the methods within the TreeNode classes simultaneously updating the TreeNode class as well.