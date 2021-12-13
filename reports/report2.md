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


# Exercise 28

### *"JPacman contains a test that can become a flaky test: see LauncherSmokeTest.smokeTest. Read the test and find out why this test can be flaky. Next, discuss other reasons why a test can become flaky and what can we do to avoid them."*

The smoke test only works in the current level. In another level he will fail. In addition, he can also fail if something is changed in the "attack" algorithms of the ghosts. The likelihood of flaky tests increases with the size of our tests. The larger it is, the flakier it can be. We can reduce this by considering beforehand what we want to test in order to avoid unnecessary tests and thus to keep the tests smaller. 

<br/>
