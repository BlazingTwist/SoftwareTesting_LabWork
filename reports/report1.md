# Exercise 6

### *"Name 2 classes that are not well-tested, and explain why the smoke test does not cover it."*

The classes `CollisionInteractionMap` and `InverseCollisionHandler` have 0% Class-coverage, because they are indirectly unused.  
Their only usage is in the `DefaultPlayerInteractionMap`, a class that isn't used by any code. (consequently the Class-coverage of this class is 0% as well)  

The SmokeTest would cover all 3 of these classes, if it instantiated the `DefaultPlayerInteractionMap` directly or indirectly.

<br/>

#  Exercise 7

### *"Have a look at class game.Game, method move. Is it covered by our smoke test?"*

Yes the method move is covered by the LauncherSmokeTest.

We are getting the exeption that the score is 10, but we expected 60.  
Althought the exeption gave us the hint to search for the bug between the 2 marked assetions.  
To fix the problem is quite simple, because we have got only one line of code after every assertion.

<br/>

# Exercise 8

### *"Change board.Direction.getDeltaX so that it returns dy instead of dx."*
### *"Explain what you see. Was it easy to understand where the problem is?"*

The test fails with this message:
```
org.opentest4j.AssertionFailedError: 
Expecting:
 <0>
to be equal to:
 <10>
but was not.
Expected :10
Actual   :0

at nl.tudelft.jpacman.LauncherSmokeTest.smokeTest(LauncherSmokeTest.java:69)
```

In my opinion, the message provided by the smoke test isn't very clear. And the information that this test is failing is not particularly helpful.  
This is because there are many possible error sources, as the test simply does "take one step east, are your points == 10?", it may be that the point calculator does not work, the level is set up incorrectly, something about the movement is broken, etc.

However, it is absolutely fine, that the smoke test provides such a general error, since it's purpose is to identify "does the game seem to work at a general level?" - and it correctly identified that it does *not*.

If we execute all test cases, it is easy to identify that something about navigating the board does not work correctly.  
Unfortunately gradle does not execute our `DirectionTest` class, which makes the issue strikingly obvious, because the `DirectionTest` is not part of the `default-test`s.

<br/>

# Exercise 9

### *"Then, provide at most two paragraphs explaining how Game, Unit, Board, and Level classes are related to each other."*

A List of Ghosts(which extends from Class `Unit`) and a `Board` is passed to the `Level` class on initialisation.  
In the `Game` class the methods from the `Level` class are used to manage/control the running Game.


<br/>

# Exercise 12

### *"Provide a domain matrix for the desired behavior of the boundary values in the withinBorders method."*

![Domain matrix](Exercise_12_img1.png)

**Further Considerations:**  
`Width` and `Height` are variables as well from the set of positive natural numbers (including `0`).  
We should test these boundaries ("`Width = 0`" and/or "`Height = 0`") as well, however, given the structure of two-dimensional Arrays, we cannot test for "`Width = 0 && Height > 0`".

Furthermore, assuming a `Width` of `0` reveals an inadequacy with the `Board` class, since `getHeight` will throw an exception.

Our resulting domain matrix may look like this:  
![Domain matrix](Exercise_12_img2.png)

<br/>

# Exercise 14

### *"What can we do to avoid code repetition during the Arrange part of the unit test?"*

It is important to specify the unit test as precisely as possible in the arrange phase.  
This saves you from calling classes / methods etc. that are not absolutely necessary for the unit-test.  
The less complex the test, the lower the probability of code repetitions. 