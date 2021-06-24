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
* We have a view (MainActivity) that is responsible for all the things shown on the screen and the databinding with the viewModel when we change something on the screen.
    * We have a joystick class that is used in the MainActivity.
* We have a viewmodel (FlightControlViewModel) that is responsible of sending the data to the model to be dealt with and to data bind with the view.
* We have a model (FlightControlModel) that is responsible of connecting to the flight gear server and of sending the data that it got from the viewmodel to the server.
    * All the sending is being done with a thread that is waiting for data to be arriving from the viewmodel (using active object design pattern).
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