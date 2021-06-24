# Advanced Programming #2 - Third Assignment
## Yorai Roth Hazan, Ori Dabush
***
## First part - Documentation of the features of the Project
* when you open up a the project on your phone you will see a couple of things:
    * Two textboxes that one is for entering the ip and the second for the port.
    * A button that is connecting us to the flight gear server.
    * Two SeekBars that the vertical one control the throttle of the airplane and the horizontal one control the rudder of the airplane.
    * A joystick that contol the aileron and the elevator of the airplane.
***
## Second Part - Documentation of the files of the project
Our project is built like a MVVM structure: (view, viewmodel, model)
* View – responsible for all the things shown on the screen. In the activity_main.xml file we define the activity itself (all the views that will appear on the screen). In the view model there is also the joystick (in Joystick.java file). The joystick is being used in the main activity.
* The View Model - it is responsible for 2 things: 
  * The first thing is the data binding – the view model has a property for each data piece that we need to send to the flight gear simulator. Those properties are binded to the views that appear in the main activity (the view), and the are updating when the user changes the views (joystick and seekbars). We do it using BindingAdapter, InverseBindingAdapter and Bindable methods.
  * The second thing is to pass the data to the model when it is being changed.
* The Model - The model is the part which talks with the flight gear server. He does that on a different thread. This is done using the active object design pattern – the model has a BlockingQueue which contains the data that needs to be sent. The new thread removes an element from the queue (using the take method – which blocks until the queue is not empty). Every time the view model updates the model, a new data piece is inserted to the queue.
***
## Third Part - Installations required
* Download [Intellij](https://www.jetbrains.com/idea/download/#section=windows) or [AndroidStudio](https://developer.android.com/studio?gclid=Cj0KCQjw2tCGBhCLARIsABJGmZ6H7VdKBoKCjh4YNUIOmFTsVzwXn4LSmeVoHufmu7aaTdfqPwKaqLcaAlv3EALw_wcB&gclsrc=aw.ds#downloads) (for the Project)
* Download [FlightGear](https://www.flightgear.org/)
***
## Forth Part - Running on new computer
1. Download the apps from the third part.
2. Start fightGear and click on setting. In the bottom paste:
> --telnet=socket,in,10,127.0.0.1,6400,tcp
3. Open the project app that you downloaded and pen the project
4. Download a virtual phone device or connect your phone to the app (notice that the device's language must be English).
5. Click on Start (with a green arrow) and start running the project!
***
## Fifth Part
* Link to our GitHub code - https://github.com/dabushori/AP2-3.git
* Link to the web - https://github.com/dabushori/AP2-3
***
## Sixth Part - Video
Link to the video -
***