# US 008 - Select and Post Announcement Request from list

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, I intend to see the list of property announcement requests made
to myself, so that I can post the announcement.






### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	The real estate agent reviews advertisement requests, registers the information in the system and
publishes the offer so that it is visible to all clients who visit the agency and use the application.
> Owners go to one of the company's branches and meet with a real estate agent to sell or
rent one or more properties, or they can use the company's application for the same purposes. The
owner provides property characteristics and the requested price and sends the request to an agent.
Upon receiving the order, the agent sets the commission and publishes the offer in the system. The
commission can be a fixed amount or a percentage.
The real estate agent reviews advertisement requests, registers the information in the system and
publishes the offer so that it is visible to all clients who visit the agency and use the application.
> When the client decides to buy/rent the property, he sends a request for the purchase/lease of the
property to the agent. After being appreciated by the agent, he accepts or rejects the order. If the
request is accepted, the offer will not be shown again to clients using the application.


**From the client clarifications:**

> **Question:** What would be the attributes of the Owner and Agent?
>  
> **Answer:** The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the contact
telephone number. The Agent is an employee of the company.
> 
> **Question:** About US08, since as an agent I intend to see the advertisement requests made to me in order to publish the chosen advertisement after consulting the list, why should there be an option to reject it since the previously chosen advertisement had already the intention of being published.The rejection operation shouldn't be described as another feature?
> 
> **Answer:** I want to check all property announcement requests and have the option to accept or reject any announcement request. The property owner is a human being and can make errors like any other human being...
> 
> **Question:** besides the creation of a message justifying the rejection should a message or an email be sent to the client.
> 
> **Answer:** The system should also send the message to the owner by e-mail.
> 
> **Question:** Does the request once it's declined by the agent be deleted from the request list?
> 
> **Answer:** The announcement request should not be shown again to the agent. We never delete information from our system.
> 
> **Question:** Do the agent have to insert the commission after accepting the request from the request list?
> 
> **Answer:** The agent should, firstly, set the commission and then publishes the offer in the system. The sale price (the USD value that is shown in the announcement) should include the commission value (owner requested price + commission) and should not show the commission. The commission is only specified when the agent accepts the request.
> 
> **Question:**  Is the owner responsible to assigning the property to an agent or there are other ways to do that?
> 
> **Answer:** In US8 we get "As an agent, I intend to see the list of property announcement requests made to myself, so that I can post the announcement". In this US the agent is the actor.
> 
> **Question:** In this US8, will it be necessary to show search criteria? If so, which ones?
> 
> **Answer:** There is no search criteria.
> 
> **Question:** Regarding US008, can the agent decline an announcement request?
> 
> **Answer:** Yes. The agent must include a message justifying the rejection.
>
> **Question:** Can the agent select multiple requests at the same time?
> 
> **Answer:** No. The agent can only post one announcement at a time.
> 
> **Question:** When displaying the property announcement requests in the system to the agent besides them being ordered from most recent to oldest is there a need to display the specific day when the requests were published?
> 
> **Answer:** The list of property announcement requests should be sorted by the date they were created, with the most recent requests appearing first. The system should show the date when the property announcement requests was made.







### 1.3. Acceptance Criteria


* **AC1:** The list of property announcement requests should be sorted by the date
  they were created, with the most recent requests appearing first.

* **AC2:** An announcement is posted when a request is accepted. The list of requests
  should be refreshed, and that request should not be shown again.
* **AC3:** If the agent refuses the announcement request, they should send a message or an email with the appropriate justification.
* **AC4:** The agent must first define the commission and then publish the offer in the system. The sale price (the USD amount that is shown in the ad) must include the commission amount (owner asked price + commission) and must not show the commission.





### 1.4. Found out Dependencies


* There a dependency to "US004-: As an owner, I intend to submit a request for listing a property sale or rent,
  choosing the responsible agent". US008 describes the functionality where agents can view the list of property announcement requests made specifically to them, so that they can post the corresponding announcements. Therefore, for there to be announcement requests for agents to view, it is necessary for owners to submit these requests, as described in US004.

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * the commission
    * the message


**Selected data:**
  * announcement request



**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

![System Sequence Diagram](svg/us008-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* 