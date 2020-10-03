## Self-Evaluation Form for Milestone 1

### General 

We will run self-evaluations for each milestone this semester.  The
graders will evaluate them for accuracy and completeness.

Every self-evaluation will go out into your Enterprise GitHub repo
within a short time afrer the milestone deadline, and you will have 24
hours to answer the questions and push back a completed form.

This one is a practice run to make sure you get


### Specifics 


- does your analysis cover the following ideas:

  - the need for an explicit Interface specification between the (remote) AI 
    players and the game system?
      - Yes, in the second bullet point under Representation section of Sign-up server and the third bullet point of Milestones, we had the idea of creating an interface for coding different AI players.


  - the need for a referee sub-system for managing individual games
      - No, we don't think a referee sub-system is necessary. In our idea, we considered the referee as a part of the game logic where it will assign colors to players when the game starts, determing if the next move from a player is valid, etc. 


  - the need for a tournament management sub-system for grouping
    players into games and dispatching to referee components
      - Yes, sort of. In the Sign-up server and Game Service sections of system memo, we explained how users are passing their AI players to the Game Service, we are expecting the Game Service to group the AI players by different users. In terms of the referee, as we explained in the previous questions, we thought referee is a part of the game logic, so it is unnecessary.


- does your building plan identify concrete milestones with demo prototypes:

  - for running individual games
    - Yes. In the third bullet point of Milestones, we explained a stage where a game board is creaeted, and we put a local-created dummy AI player into the game and display its movements.



  - for running complete tournaments on a single computer 
    - Yes. In the fifth bullet point of Milestones.



  - for running remote tournaments on a network
    - Yes. In the sixth bullet point of Milestones.




- for the English of your memo, you may wish to check the following:

  - is each paragraph dedicated to a single topic? does it come with a
    thesis statement that specifies the topic?
    - In the System memo, we used bullet points and sub-bullet points in seperating each components and their functionalities, it is clear to see what each component do visually. Also there is a Description section for summarizing and explaining the relationships between each components. In terms of the Milestones memo, we had six miliestones and each are separated by bullet points, they are all purpose-sepecific as well.



  - do sentences make a point? do they run on?
    - Yes, since we were using bullet points for the most of the time, we think it is clear to view the points and distinguish the hierarchies.



  - do sentences connect via old words/new words so that readers keep
    reading?
    - The only "words" that appears frequently are the name of each components, we mentioned them when there is a connection between different components, and we think that can help readers to have a better picture of our design structure.



  - are all sentences complete? Are they missing verbs? Objects? Other
    essential words?
    - Some bullet points are not in complete sentences and might miss subjects, but we think they are fine because they are well-structured in sections and bullet points, which shoudn't be hard to read.


  - did you make sure that the spelling is correct? ("It's" is *not* a
    possesive; it's short for "it is". "There" is different from
    "their", a word that is too popular for your generation.)
    - Yes, we don't think there are any other typos.


The ideal feedback are pointers to specific senetences in your memo.
For PDF, the paragraph/sentence number suffices. 

For **code repos**, we will expect GitHub line-specific links. 


