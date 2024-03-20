# Events

## Project Goal

The goal of the app is to provide real-time events information.  
Users can access an event list where each one has an image, name, date, venue, city name and state code.  
The app allows users to get those events searching by city. The search is one by one.

### Screen

The print screens are in low resolution because of Android Studio emulator rendering.  
In these images: Success, slow connection feedback, no connection feedback and empty state.

![success screen](https://github.com/luckeiros/ticket-api/assets/49406941/df4990ea-b954-4e7e-b268-210cd5c2e9e2)
![slow connection](https://github.com/luckeiros/ticket-api/assets/49406941/f3b7bfdc-0586-4eec-8c34-53d865f24179)
![no connection](https://github.com/luckeiros/ticket-api/assets/49406941/9d0434b0-f6de-4cff-92eb-3e29661671b4)
![empty state](https://github.com/luckeiros/ticket-api/assets/49406941/0bf17c62-e795-4e32-b402-d30ecd778ba5)

## Project Structure

### Architecture

The project uses MVVM + Clean Architecture.  
Each feature has the following directory structure:

- `data`
- `di`
- `domain`
- `presentation`

#### `data/`

This layer centralizes everything related to the feature data:  
- `remote`: Centralizes remote data.
  - `api`: Interface which access data from a remote source.
  - `mapper`: Data converter - from DTO to domain model.
  - `response`: Data Transfer Objects from API.
- `repository`: Interacts with remote source and provides data to the business layer.

#### `di/`

This layer centralizes the feature dependencies in a dependency injection (di) module.

#### `domain/`

This layer centralizes the domain models to be presented in the UI.

#### `presentation/`

This layer centralizes everything related to the UI.
- `state`: Models the different UI states.
- `view`: Presents the UI and interacts with the user.
- `viewModel`: Business and UI logic.

### Low Level Structure

The low level structure of the project contains three directories:

- `base`
- `common`
- `core`

#### `base/`

This layer centralizes the app base Activity and Fragment for common configurations usability in these structures.

#### `common/`

This layer has the app general 'commons'. It contains useful resources to be used in the entire application.
- `extension`: Extension functions commonly used.
- `view`: Common view treatments.
  - `feedback`: Centralizes feedback common usage.
  - `binding`: Centralizes a common manner of binding views.
  - `image`: Centralizes common image treatments.
  - `pagination`: Centralizes a common pagination handling.


#### `core/`

This layer centralizes literally the application core.
- `application`: The application base configuration.
- `network`: The application base network configuration.
  - `connectivity`: An approach to manage the device's connection state.
  - `interceptor`: Centralizes the entire application interceptors.

## General

### API Service

`EventApi` is an interface which provides a simple way to access data from the remote source.  
It uses Retrofit, which makes network requests much easier.  
The GET annotation is receiving only the endpoint/path because the ApiInterceptor already passes the Base URL.  
The getEvents() query receives a String city, which is the city searched by the user, and an Int page, which is used to get the nextPage from the API under the pagination concept. It returns an EventResponseDTO containing all the data that will be used in the app.  
This function is a `suspend` one because it runs in a Coroutines Dispatcher. It's better explained on the next topic.

### Repository

`EventRepository` is essentially the Repository Pattern. This helps to separate the data access logic from the rest of the application, making it easier to maintain and change data sources in the future.  
The implementation receives an API interface which is injected into its constructor. This API interface is previously created by Retrofit in the feature DI module.
The repository's getEvents function uses `withContext` from Kotlin Coroutines to run the API interface getEvents function into a IO Dispatcher context, which is optimized for I/O operations. This ensures that the function won't block the UI thread.

### Dependency Injection

`NetworkModule` is a crucial part of the app's architecture. It's responsible for managing the main app dependencies, such as Retrofit and OkHttpClient.  
This Retrofit instance can be used in the entire app runtime, to make network requests in any feature.  
The OkHttpClient instance is important to set up the interceptors, such as ApiInterceptor, which puts the API Key into the endpoint before making the request, and the Error/Connectivity Interceptor to manage error treatment. Also, it configures the timeout for network requests.

`EventModule` centralizes the feature dependencies, such as API (created with Retrofit), Repository, PaginationHandler and ViewModel. All these dependencies are instantiated and in this module and can be injected for the classes that will need.

### Model

`EventModel` centralizes the models that were converted from DTO to domain models. Those are simplified data classes to be easily used in the presentation layer.

### Mapper

`EventMapper` is responsible for convert DTO to domain models. It makes it in a very easy way by using extension functions.

### Connectivity Manager

`ConnectivityManager` is a wrapper that gets the current connection state from the device and, based on that, notifies the `ConnectivityInterceptor`, which can throw a NoConnectionException or not.

### Interceptor

`ErrorInterceptor` can throw `TimeoutException`, `BusinessException` and `UnknownException`. It also generates a Log template to make it easier to see the request log details as endpoint, method, status code and response body.

`ApiInterceptor` was explained at Dependency Injection section.

`ConnectivityInterceptor` was explained at Connectivity Manager section.

### Extensions

`CoroutinesScopeExt` is used as a helper for launching coroutines within a viewModel plus handle exceptions.

`ImageExt` is used as a helper for loading images using Glide library.

`IntExt` has a function to replace a null Int for zero.

`LiveDataExt` is used as a helper for emitting MutableLiveData values.

`StringExt` has a function to format date to a specific format.

`ViewExt`is used as a helper for view common treatments.

### Feedback

`FeedbackFactory` is a Factory Pattern used to create feedback messages based on the thrown Exception. It receives the Exception and create a `Feedback` data class passing a String with specific error message based on the Exception type.

### View State

`EventState` is a sealed class with four states: `Loading`, `Error`, `Success` and `NoEventsFound`.  
`Success` receives a list of events and the flag `isFirstLoading`, which is a Boolean to help in pagination.  
`Error` receives a `Feedback` to show based on the Exception.  
`Loading` and `NoEventsFound` receives nothing.

### Pagination Handler

`PaginationHandler` is an auxiliar interface used as a helper to make list pagination. It abstracts the pagination rules by updating "states" such as `currentPage` and `isLastPage` and helping ViewModel to manage it.

### ViewModel

`EventViewModel` is responsible for managing the data, doing business logic, sending data and emitting `EventState` to the UI (Fragment).  
It uses LiveData and MutableLiveData to emit those states, allowing the Fragment to observe the changes when a new state is emitted and update the UI accordingly. It also use the `PaginationHandler`, which was explained at Pagination Handler section.

### Presentation

`BaseActivity` extends from AppCompatActivity. It has an auxiliar function to open Fragments and a default loader, which can be used for any Activity or Fragment that wants to, making it an app's common behavior.  

`BaseFragment` extends from Fragment. It only uses the loader from the BaseActivity, which allows Fragments that extends from BaseFragment to use this same loader.

`EventActivity` extends from `BaseActivity` - that's the idea, every app activity can extend from `BaseActivity` and use its resources, such as the openFragment() function and the loader. That's what `EventActivity` is doing. 

`EventFragment` extends from `BaseFragment`. It observe the states emitted from `EventViewModel` and update the view accordingly. It also has a Pagination RecyclerView Listener.

### Unit Test

`EventViewModelTest` tests the different scenarios/states of the ViewModel. It uses Coroutines to run tests on its blocks and also the MockK library to mock the necessary.

## Final Considerations

One of the requirements for this test was to create any type of storage, but I couldn't make because of the limited time I had to make this test, not because of the deadline, but because I'm very overwhelmed in my current job and I almost had no time to do everything I wanted to do in this test.  
I had some ideas: using Room Database to cache the events the user already saw for at least 1 hour, avoiding unnecessary network requests for the same result. Also, if I had more time, I would apply Paging 3 from Jetpack to have a more concise and recommended pagination logic, but due to the lack of time, I had to make it the old way.
Well, despite everything mentioned, I put my heart in this test, working a few days along the dawn, but that's it. 
Thank you.

Lucas
