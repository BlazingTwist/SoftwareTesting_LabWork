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
