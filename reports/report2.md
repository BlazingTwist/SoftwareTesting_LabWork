# Exercise 23

### *"Analyze requirements (found in doc/scenarios.md) and derive a decision table for the JPacman collisions from it. In this decision table you should encode the outcomes of collisions between two pairs of entities. You are free to filter out collisions that do not occur, such as two Pellet’s colliding. To give you an idea, look at the table below. Note that this table is incomplete and may have too many or too few columns."*

|Collider|Collidee|Consequence|
|--------|--------|-----------|
| Player |empty Square|points stay the same|
| Player | Wall | move is not conducted|
| Player | Pellet | earn Points, Pellet disappears|
| Player | Ghost |Pacman dies, Game over|
| Ghost | emptySquare | nothing Happens|
| Ghost | Player| Game over|
| Ghost| Pellet | Pellet isn´t visible anymore|

<br/>

# Exercise 27

### *"See the Ghost#randomMove() method. It makes use of Java’s Random class to generate random numbers. How would you test such method, if everytime you execute the method you get a different answer? Explain your idea."*

In this case I would test that one of the possible results of this method is present as the return value of this function. In relation to JPacman, I would create a level where I know what returns I can get and then test until I get all possible returns. 

<br/>
