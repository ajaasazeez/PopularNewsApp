<h1>MVVM Popular News App</a></h1>
<p>This is an app to showcase MVVM architecture with NY times API</p>
<p>The app uses the following libraries / topics:</p>
<ul>
	<li>Kotlin</li>
  <li>Hilt</li>
	<li>Coroutines</li>
  <li>Flow</li>
	<li>Retrofit2</li>
  <li>Data Binding</li>
  <li>Navigation Components</li>
	<li>ViewModels</li>
	<li>Repository pattern</li>
<br>
  <p>The ui package contains the MainActivity which is the main container activity for two fragments PopularNewsFragment & NewsDetailsFragment using navigation componets. The PupularNewsFragment shows a list of most popular news articles calling API and when clicking on a item moves to NewsDetailsFragment which shows details passed from PupularNewsFragment using navArgs  </p>
  <p>Hilt is used for dependency injection which is a wrapper library for dagger. There is network module which contails all the network related dependencies.</p>
 <p>In viewmodel I have provided repository and NetworkHelper classes via constructor injection with Hilt. The NetworkHelper checks whther there is network connection available or not.
   The repository contains suspend funtion to call the most popular news. This is called in the init block of the viewmodel.
   In repository I have provided RemoteDataSource class as constructor injection which contains the actual call. This class will contain all the remote data.
   The Repository and the RemoteDataSouce emits Flow which is then converted to live data in Viewmodel and observed in the views. It is better to use Flow in Repository because Livedata is not fully fledged reactive. Livedata is better in case when we get the data in a single shot and and it caches the data to livedata is being observed in the view.
   The RemoteDataSource call the api with getResponse() which differentiate success and errors occuring in the API call
   After the API call is success the list will be populated in the recyclerview which uses ListAdapter with DiffUtils. The adapter uses data bindind to bind the data in the recycler items. When clicked on a item data will be passed to the NewsDetailFragment using navArgs and display it there using databinding.
  </p>
<br>
<p></p>
