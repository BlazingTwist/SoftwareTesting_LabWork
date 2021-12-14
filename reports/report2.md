# Exercise 20

![](Exercise_20_assignment.png)

*see added test class `MapParserTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/24)*

|"Nice Weather"-Test|Method under Test|Summary|
|:---|:---|:---|
|test_parseMap_normalMap|parseMap(char[][] map)|Provides a typical Map and verifies that the Tiles `empty`, `wall`, `pellet` and `ghost` are created correctly.|
|test_parseMap_validPlayerPosition|parseMap(char[][] map)|Provides a typical Map and verifies that the Player is correctly placed in the world.|
|test_parseMap_validStringList|parseMap(List<String> text)|Verify that the overload is working as expected.|
|test_parseMap_validInputStream|parseMap(InputStream source)|Verify that the overload is working as expected.|
|test_parseMap_validMapName|parseMap(String mapName)|Verify that the overload is working as expected.|

<div class="page"/>

# Exercise 21

![](Exercise_21_assignment.png)

*see added test class `MapParserTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/24)*

|"Bad Weather"-Test|Method under Test|Summary|
|:---|:---|:---|
|test_parseMap_emptyMap|parseMap(char[][] map)|Provides a completely empty map and verifies that it is handles correctly.|
|test_parseMap_invalidCharacter|parseMap(char[][] map)|Verify that an exception is thrown when maps contain unknown characters.|
|test_parseMap_invalidFormat|parseMap(List<String> text)|Verify that invalidly formatted maps raise an exception.|
|test_parseMap_invalidMapName|parseMap(String mapName)|Verify that trying to parse a map that does not exist raises an exception.|
|test_parseMap_throwsIOException|parseMap(String mapName)|Verify behaviour of parseMap, when the InputStream overload throws an exception.|


<div class="page"/>

# Exercise 22

![](Exercise_22_assignment.png)

*see added test class `GameTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/26)*

![](Exercise_22_coverage.png)

<br/>

# Exercise 23

![](Exercise_23_assignment.png)

|Collider|Collidee|Consequence|
|--------|--------|-----------|
| Player | empty Square | points stay the same           |
| Player | Wall         | move is not conducted          |
| Player | Player       | players occupy the same square |
| Player | Pellet       | earn Points, Pellet disappears |
| Player | Ghost        | Pacman dies, Game over         |
| Ghost  | emptySquare  | ghost occupies empty square    |
| Ghost  | Wall         | move is not conducted          |
| Ghost  | Player       | Game over                      |
| Ghost  | Pellet       | Pellet isn´t visible anymore   |
| Ghost  | Ghost        | ghosts occupy the same square  |

<br/>

# Exercise 24

![](Exercise_24_assignment.png)

*see added test class `PlayerCollisionsTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/27)*

<br/>

# Exercise 25

![](Exercise_25_assignment.png)

*see modified test class `PlayerCollisionsTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/28)*

<br/>

# Exercise 26

![](Exercise_26_assignment.png)

Coverage before:
![](Exercise_26_cov_before_tests.png)

Coverage after:
![](Exercise_26_cov_after_tests.png)

The remaining uncovered line in `CollisionInteractionMap`
![](Exercise_26_uncovered_line.png)

The added `PlayerCollisionsTest` supports testing Classes that implement the `CollisionMap` interface *and* make use of a `PointCalculator`.  
It exercises all scenarios described in Exercise_23, except for those that do not involve `Unit`s ("empty Square" and "Wall").  
Currently collisions between Units that are assumed to be stationary are not checked (e.g. Pellet on Pellet).

<br/>

# Exercise 27

![](Exercise_27_assignment.png)

In this case I would test that one of the possible results of this method is present as the return value of this function.  
Currently, the `randomMove()` method creates a new Random Number Generator every time it is called - that smells! Instead it should obtain a new random number from an existing generator.  
If that was the case, it would also mean that we can mock the generator and eliminate the randomness in our tests.

<br/>

# Exercise 28

![](Exercise_28_assignment.png)

The smoke test only works in the current level. In another level he will fail. In addition, he can also fail if something is changed in the "attack" algorithms of the ghosts. The likelihood of flaky tests increases with the size of our tests. The larger it is, the flakier it can be. We can reduce this by considering beforehand what we want to test in order to avoid unnecessary tests and thus to keep the tests smaller. 

<br/>

# Exercise 29

![](Exercise_29_assignment.png)

100% code coverage is a nice thing, but in the most cases it is not necessary. The advantage is that you can be sure that the code does what we expect. The disadvantage is that you usually need a lot of resources to achieve 100% code coverage. Such metrics are good for assessing how error-free the code is. For core elements of the code, you should achieve a very high percentage of the metrics, but for less important elements, in my opinion, 80% is often enough.

</br>

# Exercise 30

![](Exercise_30_assignment.png)

TODO

<br/>

# Exercise 31

![](Exercise_31_assignment.png)

TODO

<br/>

# Exercise 32

![](Exercise_32_assignment.png)

TODO