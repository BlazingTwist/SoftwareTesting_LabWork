# Students

* Eric Jarosch - 227271
* Lukas Fritzsche - 227771

<br/>

# Exercise 33

![](Exercise_33_assignment.png)

*see added test class `SuspensionTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/30)*

Essentially, this test covers three cases:
* Initial start allows movement of units
* Suspension after initial start disallows movement of units
* Restart after suspension allows movement of units

<br/>

# Exercise 34

![](Exercise_34_assignment.png)

*see added test class `PlayerMovementTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/31)*

<div class="page"/>

# Exercise 35

![](Exercise_35_assignment.png)

Scenario 2.4 and 2.5 are mostly quite similar to Unit-Testing, since there are decently powerful interfaces to control the player. (In a way, it is also "easier" than Unit-Testing, since the System manages a large chunk of initialization for us)

However, 2.4 and 2.5 have the added difficulty that we cannot directly observe whether Player::setAlive or Level::levelLost has been called.  
What we have to do instead, is to observe the attributes of the Level to identify what happened (e.g. "no pellets left" -> won | or "all players dead" -> lost)  
Alternatively we can register a LevelObserver to listen to levelWon and levelLost.

2.4 provides additional difficulty, since the Ghosts might move if the thread executing the test is too slow. This could result in flaky tests.

<br/>

# Exercise 36

![](Exercise_36_assignment.png)

*see modified test class `PlayerMovementTest`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/35)*

<br/>

# Exercise 37

![](Exercise_37_assignment.png)

Testing User Story 3 is a lot more complicated.  
We cannot directly control the action of a ghost, but we can provide custom levels in such a way that the ghost chooses our desired action.  
Additionally, we have to wait for the ghost to execute its action, fortunately we can register a LevelObserver that allows us to observe when a move is made.

Overall, when performing System Tests, there are a lot more side-effects to consider than during Unit Testing.

<br/>

# Exercise 38

![](Exercise_38_assignment.png)

<img src="Exercise_38.png" style="width: 500px"/>

<br/>

# Exercise 39

![](Exercise_39_assignment.png)

<img src="Exercise_39_img.png" style="width: 500px"/>

<div class="page"/>

# Exercise 40

![](Exercise_40_assignment.png)

![](Exercise_40_img.png)

<br/>

# Exercise 42

![](Exercise_42_assignment.png)

*see modified document `scenarios.md`*

<div class="page"/>

# Exercise 43

![](Exercise_43_assignment.png)

<img src="Exercise_43_img1.png" style="width: 500px"/>

<img src="Exercise_43_img2.png" style="width: 500px"/>

<div class="page"/>

# Exercise 45

![](Exercise_45_assignment.png)

*see added source class `MultiLevelLauncher`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/42)*

<br/>

# Exercise 46

![](Exercise_46_assignment.png)

*see added source class `MultiLevelGame`*  
*or alternatively the relevant [Merge Request](https://github.com/BlazingTwist/SoftwareTesting_LabWork/pull/42)*

<br/>

# Submission

### *"List three things you consider good (either in your solution or in the framework),..."*

Working with the labworks was very helpful in gaining practical experience. Especially working with mocks brought me a lot. 

<br/>

### *"...and list three things you consider annoying or bad, and propose an alternative for them."*

Nothing to say.

<br/>
