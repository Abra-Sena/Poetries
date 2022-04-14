# Poetries App

This App retrieves all Poems available in the Poetry Database accessible [here](https://poetrydb.org)


## Development
Developed with an MVVM architecture, we are using Fragments for the views to have a light weight application as one fragment is being used multiple times.
Dependency injection with Koin is providing all necessary modules needed for our functionnality as well as the viewmodel shared by all views.
We are using LiveData to monitor the response we query from the API in order to observe our data and have updated value for all views when needed. Coroutines is handling all our background tasks to free the main thread from heavy task.
Our Data is being persisted with RoomDatabase to save data in local to handle situations where network failure occurs, User will be able to access the list of Poems previously saved.

### Problems Encountered
- Endpoint returning Array instead of Object for our Data, we can only get our response as a list of our data object
- RoomDatabase not handling List item, but the Poems content 'lines' has List<String> type. We use TypeConverters to create functions to handle the conversion between String and List<String>
  

## Features
- A welcome view fragment to guide the user on how to use the application
- User get a random Poem with a tap on the 'Random' button
- A Tap on 'Title' or 'Author' buttons reveals the searchview where user enters the value to search.
  - Searching by 'title' will retrieve all Poems having the passed parameter in its Title
  - Searching by 'author' will retrieve all Poems having the passed parameter in its Author's name

### Upcoming Features
- Style the App to have a nice view
- Animation when transition between views
- Unit Testings
- Download Authors photo from intrnet sources to load into our list of Poem, the endpoint does not include this information.

  
### Next Features
- Sharing a Poem with other apps on the device
  

## Libraries


