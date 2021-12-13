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


# Exercise 29

### *"What is your opinion regarding achieving 100% of code coverage? What are the advantages? What are the disadvantages? How should one deal with such metrics, in your opinion?"*

100% code coverage is a nice thing, but in the most cases it is not necessary. The advantage is that you can be sure that the code does what we expect. The disadvantage is that you usually need a lot of resources to achieve 100% code coverage. Such metrics are good for assessing how error-free the code is. For core elements of the code, you should achieve a very high percentage of the metrics, but for less important elements, in my opinion, 80% is often enough.

</br>
