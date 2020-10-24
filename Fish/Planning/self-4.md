## Self-Evaluation Form for Milestone 4

Under each of the following elements below, indicate below where your
TAs can find:

- the interpretation of your data representation for `board`
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/FishModel.java#L6-L73>
  - Also, we included our board interpretation and indexing explaination in our README under the Mileston 2 section.


- the interpretation of your data representation for `game state`
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/FishState.java#L12-L40>


- the publicly visible methods/functions for game treees 
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/FishTreeNode.java#L22-L110>
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/FishTreeNode.java#L155-L180>


- the data description of the game tree, including an interpretation;
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/FishTreeNode.java#L6-L40>


- a signature/purpose statement of functionality for the first query function
  - applyFunctionToState() : <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/FishTreeNode.java#L85-L94>
  - IAction interface : <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/Actions/IAction.java#L5-L20> 
  - MovePenguinAction class : <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/src/models/Actions/MovePenguinAction.java#L7-L38>
  - The performAction() will return a new state if the action was valid and succeed, if it is not valid, it will throw, which signal that the action was illegal.
  



- unit tests for first query functionality
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/test/FishTreeNodeTest.java#L259-L364>
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/0e0a20a689520e09044df755edfa6d5f1de30343/Fish/Common/Fish/test/MovePenguinActionTest.java#L13-L272> We have thorough tests of the MovePenguinAction class, since it is the only action right now, and it will be used in the first query functionality.

**Please use GitHub perma-links to the range of lines in specific
file or a collection of files for each of the above bullet points.**

  WARNING: all perma-links must point to your commit "0e0a20a689520e09044df755edfa6d5f1de30343".
  Any bad links will result in a zero score for this self-evaluation.
  Here is an example link:
    <https://github.ccs.neu.edu/CS4500-F20/annetta/tree/0e0a20a689520e09044df755edfa6d5f1de30343/Fish>

