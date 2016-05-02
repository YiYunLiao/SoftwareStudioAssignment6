# SoftwareStudioAssignment6_Team3 #
## Explanations Of Design ##
####Operation
*	Click a character: The character will be added to network or put back to original place
*	Click on button "ADD ALL": All of the characters will be added to network
*	Click on button "CLEAR": All of the characters on network will be put back to original places
*	Press 1~7 on keyboard and release it: Change the episode displayed

####Add Character
![alt text](/picture/add.gif "Add Character")
####Remove Character
![alt text](/picture/remove.gif "Remove Character")
####Add All Characters
![alt text](/picture/addAll.gif "Add All Characters")
####Clear All Characters
![alt text](/picture/clear.gif "Clear All Characters")
####Change Episode
![alt text](/picture/changeEpisode.gif "Change Episode")

## Members And Distributions ##
####Member 1 : Allan: 103062203
*	Button.java
*	CharacterLabel.java
*	Character.java:	display(): void
*	Network.java: Everything except constructor, display(): void and method to detect mouse
*	MainApplet.java: mouseClicked(): void

####Member 2 : YiYunLiao: 103062235
*	Character.java: Everything except display(): void
*	All methods to detect if arrow was on the object
*	Every constructor except Button and CharacterLabel.java
*	MainApplet.java: Everything except mouseClicked(): void