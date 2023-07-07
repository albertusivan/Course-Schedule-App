# Course-Schedule-App
1. What project have you worked about?

The project that Ive working on is completing a program regarding the Course Scheduler application on Android. This application will show you a nearest schedule of course that have been add by the user. User also can delete the course by sliding it to the right or delete it manualy on detail screen. This aplication allow user to change theme into dark mode and can enable the notification feature, wich will send you a notification reminder every 6am. 

For the architecture itself, this application uses sqlite, room with three main components including entity, DAO (Data Access Object) , and database. There is also view model to store and manage UI-related data, paging for displaying list of course, PendingIntent for noification, and espresso for ui testing.

2. Which part is hardest?

The hardest part from the project that Ive working on, is in the todo 9 where the task is that i need to make AddCourseActivity to set new course schedule. Its not realy that hard, but its the hardest rather than another todo. It need to make the activity, the ui base on the project overview, and the viewmodelfactory itself. And i think this is one is hardest than another todo

3. How is the flow to change the theme to a dark theme?

when user choose the dark mode theme, the system will call the changetheme function that will set the dark mode using AppCompatDelegate.

the AppCompatDelegate itself responsible for managing the theme of the application. When we set the default night mode using AppCompatDelegate.setDefaultNightMode, it updates the internal state of the delegate and applies the new theme to the current and future activities. After that we call recreate on the activity, it destroys the current activity instance and creates a new one with the updated theme.

4. How does notification reminder work?

the notification will work if the user activates the notification in the settings section of the course schedule application. after user enable the notification feature, application will send you a daily notification reminder every 6am. how does it work?, when the notification preference is on, sistem will call the DailyReminder class which already set the schedule every 6 am will call the pending intent for notification. The notification will contain the course data that get from list of course. The data itslef including the coursename, start time, and end time

5. Why do we need LiveData?

first of all we have to know what is a LiveData in general. LiveData is a data holder class in the Android Architecture Components library that is designed to observe changes in data and notify observers when the data changes. LiveData is particularly useful for updating the UI in response to changes in data. In this aplication livedata is usefull especialy when showing the nearest course schedule. With the livedata we can see the nearest course schedule update every time we change the data, either delete it or add it



