# US 005 - Register a new store 

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                          | Question: Which class is responsible for...              | Answer                  | Justification (with patterns)                         |
|:----------------------------------------|:---------------------------------------------------------|:------------------------|:------------------------------------------------------|
| Step 1: asks to register a new store  	 | 	... instantiating the class that handles the UI?        | RegisterStoreUI         | Pure Fabrication                                      |
| 			  		                                 | 	... coordinating the US?                                | RegisterStoreController | Controller                                            |
| 			  		                                 | 	... instantiating a new store?                          | Administrator           | Creator (Rule 1): in the DM Organization has a Store. |
| 			  		                                 | ... knowing the user using the system?                   | UserSession             |                                                       |
| Step 2: requests data                   | 	...displaying the UI for the actor to input data?						 | RegisterStoreUI         | Pure Fabrication                                      |
| Step 3: types requested data	           | 	... validating input data?                              | RegisterStoreUI         | Pure Fabrication                                      |
| Step 4: submits data  		                | 		...saving the inputted data? 					                     | RegisterStoreUI         | Pure Fabrication                                      |              
| Step 5: validation  		                  | 	... validating all data (local validation)?             | Task                    | IE: owns its data.                                    | 
| 			  		                                 | 	... validating all data (global validation)?            | Administrator           | He knows all hes stores.                              | 
| 			  		                                 | 	... saving the created store?                           | Organization            | It owns all its stores.                               | 
| Step 6: show operation succed  		       | 	... informing operation success?                        | RegisterStoreUI         | Pure Fabrication                                      | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Administrator
 * Store

Other software classes (i.e. Pure Fabrication) identified: 

 * RegisterStoreUI  
 * RegisterStoreController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](../../../sprintC/us005/03.design/svg/us005-sequence-diagram-full.svg)

### Alternative 2 - Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.

![Sequence Diagram - split](../../../sprintC/us005/03.design/svg/us005-sequence-diagram-split.svg)


**Create store**

![Sequence Diagram - Partial - Create Store](../../../sprintC/us005/03.design/svg/us005-sequence-diagram-partial-create-store.svg)



## 3.3. Class Diagram (CD)

![Class Diagram](../../../sprintC/us005/03.design/svg/us005-class-diagram.svg)