# Exercise 6

### *"Name 2 classes that are not well-tested, and explain why the smoke test does not cover it."*

The classes `CollisionInteractionMap` and `InverseCollisionHandler` have 0% Class-coverage, because they are indirectly unused.  
Their only usage is in the `DefaultPlayerInteractionMap`, a class that isn't used by any code. (consequently the Class-coverage of this class is 0% as well)  

The SmokeTest would cover all 3 of these classes, if it instantiated the `DefaultPlayerInteractionMap` directly or indirectly.

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