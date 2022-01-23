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

<br/>

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

TODO

<br/>

# Exercise 38

![](Exercise_38_assignment.png)

TODO

<br/>

# Exercise 39

![](Exercise_39_assignment.png)

TODO

<br/>

# Exercise 40

![](Exercise_40_assignment.png)

TODO

<br/>

# Exercise 41

![](Exercise_41_assignment.png)

TODO

<br/>

# Exercise 42

![](Exercise_42_assignment.png)

TODO

<br/>

# Exercise 43

![](Exercise_43_assignment.png)

TODO

<br/>

# Exercise 44

![](Exercise_44_assignment.png)

TODO

<br/>

# Exercise 45

![](Exercise_45_assignment.png)

TODO

<br/>

# Exercise 46

![](Exercise_46_assignment.png)

TODO

<br/>

# Exercise 47

![](Exercise_47_assignment.png)

TODO

<br/>

# Exercise 48

![](Exercise_48_assignment.png)

TODO

<br/>

# Exercise 49

![](Exercise_49_assignment.png)

TODO

<br/>

# Exercise 50

![](Exercise_50_assignment.png)

TODO

<br/>

# Exercise 51

![](Exercise_51_assignment.png)

TODO