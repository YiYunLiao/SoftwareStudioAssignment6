# _Software Studio Assignment6 **Team3**_ #
## **Requirement** ##
Write a	program to make users	perceive the interaction between characters in the movie Star Wars for each episodes (in visualization or audiation). In this program,	users expect to understand the network structure, such as the interaction of the two specified characters, in a format other than just texts. The data set was	organized by Evelina Gabasova[1], in JSON format on Github and will be explained	 in the next section.
## **Explanation of the Design** ##
####Operation
*	Click a character: The character will be added to network or put back to original place.
*	Click on button "ADD ALL": All of the characters will be added to network.
*	Click on button "CLEAR": All of the characters on network will be put back to original places.
*	Press 1~7 on keyboard and release it: Change the episode displayed.

####Visualization
*	The width of each link is visualized based on the value of the link.
*	Littile circles are animated when being clicked or clicking effective button.

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

## **Team Members And Distributions** ##
####Member 1 : Allan: **103062203**
*	Button.java
*	CharacterLabel.java
*	Character.java:	display(): void
*	Network.java: Everything except constructor, display(): void and method to detect mouse
*	MainApplet.java: mouseClicked(): void
*	Find materials of music.

####Member 2 : YiYunLiao: **103062235**
*	Character.java: Everything except display(): void
*	All methods to detect if arrow was on the object.
*	Every constructor except Button and CharacterLabel.java
*	MainApplet.java: Everything except mouseClicked(): void
*	MinimPlayer.java
*	MusicClip.java

######Reference
[1]	Date set: [StarWars-social-network](https://github.com/evelinag/StarWars-social-network)