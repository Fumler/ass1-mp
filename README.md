Mobile Programming
==================

Assignment for the Mobile Programming course at Høgskolen i Gjøvik
Created by Fredrik Pettersen (090350)

This application is created solely for completing tasks submitted by the teachers of the mobile programming class at Høgskolen i Gjøvik. The application has a MainActivity that has a bunch of buttons for each task. When you click these buttons, the selected task will be performed.

The application uses the Internet for several tasks, for example it downloads text, video and images from the web. If the unit does not have an active Internet connection the program will let the user know and open the settings page. If the user is connected to the Internet it performs the selected task on a new thread with help from AsyncTask. Image and text requires the use of a HttpClient which you can do on the main thread fine in earlier versions of android but after version 3.0 they added the NetworkOnMainThreadException which does not let the program do networking on the main thread, so you need to do this on either a worker thread or via AsyncTask. Android does have integrated support for video streaming so you can actually do this without creating a new thread.

The difference between this application and other competing web apps is that there are no competing web apps, I hope.

This application could be extended in several different ways and explaining this would take way too much time and effort. Altough I will do a better job in the next assignment where we can create a normal application.

Tasks:
<pre>
1. Use at least two different UI view layout types
- The application uses LinearLayout and RelativeLayout.

2. Have at least 2 Activities ( this could be a splash screen activity, and a main game loop.)
- There are a total of six activites. It also starts a Google Maps activity.

3. Send data to gtl.hig.no/mobile/logging.php
- Data is sent trough a HttpGet method.

4. Retrieve two types of data from the web (text, and one other)
- Video and text is retrieved

5. Display at least one image accessed from the HiG website.
- An image of Simon McCallum is displayed.

6. Must be “signed” by you
- The application has been signed by a keystore created by the author

7. Must include an icon for the app
- The application has a custom icon, created in photoshop with help from android icon templates. 


Additional activates which can gain higher grades

1. Store data to a local file, and access the stored version if the web is not accessible
- The application downloads text from the web and stores this when you leave the activity, if you click the button while connected to the Internet it shows the live updated version from the web, if not it shows the local version.

2. Access phone sensors to allow changing the orientation of your app
- There is included a separate xml file for landscape view

3. Link app to map data showing users current location
- The application sends your current location to Google Maps and opens it via intent.

4. Create a full set of icons for different screen resolutions
- There is a custom icon for each resolution

5. Make the program localizable.
- Supported languages are Norwegian and English


</pre>





