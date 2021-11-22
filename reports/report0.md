# Students

* Lukas Fritzsche - 227771
* Eric Jarosch - 227271

<br/>

# Exercise 3

### *"Why can't we exhaustively test our entire software project? What should we do instead?"*

We cannot exhaustively test our software project, because there is an effectively infinite amount of tests we can execute.  
Instead we should test key aspects of specifications, edge cases a developer may have forgotten about (or will have forgotten about in the future) as well as invalid / unexpected input.

<br/>

# Exercise 4

### *"What is the pesticide paradox about and what does it imply to software testers?"*

The pesticide paradox states, that running the same tests repeatedly (over a long time period / when the tested program changes) will not uncover any new defects.  
It is not necessarily paradoxical, but defies intuition: "the program is passing all tests -> it must be working fine".  
The conclusion is, that software testers should update the test cases as the program is changing to identify new defects.

<br/>

# Exercise 5

### *"Why should we automate, as much as possible, the test execution?"*

Automatic test execution guarantees that a developer cannot accidentally forget running the tests manually.  
Availability of automatic testing also opens the door to continous integration (since the tests can be run at any time).  
They also allow to test functionality across different platforms (e.g. by executing these tests on different physical servers with different operating systems or hardware / testing in virtual machines)

Overall, automatic testing both speeds up the testing process, as well as making it more reliable.