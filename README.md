# GeoAlarm
## Description
is an example of using MVP Clean architecture to build geo alarm GeoArm is an example of using MVP Clean architecture to build geo alarm.

## Features
 * MVP architecture using Uncle Bob Clean Architecture
 * Built over RxJava
 * Using battery saving geofencing
 * Have many test cases


***How does It work ?***
-----------------------------
*simple and clean way , all of your calls have to be in only one direction , that is mean you shouldn't have any reference from your presenter in your useCase , and reference from your useCase in your entities , but can have a reference from useCase to presenter and from your presenter to your Android Main component (Activity , Fragment , Service , BroadCast Receiver ...... ) .*

![enter image description here](https://lh3.googleusercontent.com/-2j5KgH7iCH4/WFhwfTovV4I/AAAAAAAAHdU/RHJs6oeqBjIoANU1wBBXy_QkH3kff85tgCLcB/s0/MVP.png "MVP.png")


----------

**Interaction between Component**
-----------------------------
*The objective is the separation of concerns by keeping the business rules not knowing anything at all about the outside world, thus, they can be tested without any dependency to any external element. To achieve this, my proposal is about breaking up the project into 3 different layers, in which each one has its own purpose and works separately from the others. It is worth mentioning that each layer uses its own data model so this independence*


![enter image description here](https://lh3.googleusercontent.com/-rv3eVYsVelQ/WGqaeFn5DnI/AAAAAAAAHkQ/RqM-sKm4fVMhLM5oipcfV9x1hxWxRVwjACLcB/s0/MVP.jpg "MVP.jpg")


