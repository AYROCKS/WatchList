<h1 align="center">🎥 WatchList</h1>
<p align="center"><strong>Discover movies effortlessly with WatchList — a modern app designed using Jetpack Compose and Material 3.</strong></p>
<p align="center">
    <a href="https://github.com/AYROCKS/WatchList/releases">
        <img src="https://img.shields.io/badge/version-1.0.0-green" alt="Version 1.0.0" />
    </a>
</p>

---

<h2>🌟 TL;DR</h2>
<p><strong>WatchList</strong> is a modern Android app to manage and discover movies, built with Jetpack Compose and integrated with the TMDB API. It features RoomDB for offline storage, advanced DSA algorithms for optimized performance, and Material 3 for an intuitive user interface.</p>

---

<h2>🌟 Key Features</h2>
<ul>
    <li><strong>📜 Home Screen</strong>: Browse movies by categories like:
        <ul>
            <li>🎥 <strong>Trending</strong></li>
            <li>🌟 <strong>Popular</strong></li>
            <li>🏆 <strong>Top Rated</strong></li>
            <li>🍿 <strong>In Cinemas</strong></li>
            <li> 🎮 <strong>Releasing Soon</strong></li>
        </ul>
    </li>
    <li>🔍 <strong>Search Screen</strong>: Find any movie quickly with debounce search for efficiency.</li>
    <li>📌 <strong>Bookmark Screen</strong>: Save your favorite movies using RoomDB with offline-first design.</li>
    <li>🔄 <strong>Details Screen</strong>: Dive into individual movie details, including trailers.</li>
    <li>🔄 <strong>View More Screen</strong>: Explore extensive lists with smooth pagination using LazyColumn.</li>
</ul>

---

<h2>💡 <strong>DSA Integration</strong></h2>
<p>Showcase real-life applications of Data Structures and Algorithms (DSA) with:</p>
<ul>
    <li><strong>Binary Search</strong>: Efficiently search movies based on title.</li>
    <li><strong>Quick Sort</strong>: Dynamically sort movie lists based on user preferences, like rating or release date.</li>
    <li><strong>Greedy Knapsack by Density</strong>: Optimize movie recommendations by balancing factors like popularity, ratings, and release date.</li>
</ul>

---

<h2>📱 Screenshots</h2>
<p align="center">
    <img src="https://github.com/AYROCKS/WatchList/blob/main/screenshots/homeScreen.jpg" alt="Home Screen" width="300" />
    <img src="https://github.com/AYROCKS/WatchList/blob/main/screenshots/searchScreen.jpg" alt="Search Screen" width="300" />
    <img src="https://github.com/AYROCKS/WatchList/blob/main/screenshots/favouritesScreen.jpg" alt="Bookmark Screen" width="300" />
    <img src="https://github.com/AYROCKS/WatchList/blob/main/screenshots/viewMoreScreen.jpg" alt="View More Screen" width="300" />
    <img src="https://github.com/AYROCKS/WatchList/blob/main/screenshots/detailScreen1.jpg" alt="Details Screen" width="300" />
    <img src="https://github.com/AYROCKS/WatchList/blob/main/screenshots/detailScreen2.jpg" alt="Details Screen" width="300" />
</p>

---

<h2>🛠️ Built With</h2>
<ul>
    <li><strong>Jetpack Compose</strong>: For a modern UI with efficient state management using StateFlow.</li>
    <li><strong>Material 3</strong>: Clean and intuitive design system.</li>
    <li><strong>RoomDB</strong>: Local database for managing bookmarks with offline-first strategy.</li>
    <li><strong>Pagination Library</strong>: Smooth scrolling for large datasets using LazyColumn.</li>
    <li><strong>Retrofit</strong>: Seamless API communication with efficient caching.</li>
    <li><strong>Coil</strong>: Image loading and caching for smooth user experience.</li>
    <li><strong>YouTube Player</strong>: Integrated trailer playback.</li>
    <li><strong>TMDB API</strong>: Comprehensive movie data integration.</li>
</ul>

---

<h2>🚀 Installation</h2>
<ol>
    <li><strong>Clone the Repository:</strong>
        <pre><code>git clone https://github.com/AYROCKS/WatchList.git</code></pre>
    </li>
    <li><strong>Open in Android Studio.</strong></li>
    <li><strong>Add Your TMDB API Key:</strong>
        <ul>
            <li>Obtain an API key from <a href="https://www.themoviedb.org/documentation/api">TMDB</a>.</li>
            <li>In the <code>local.properties</code> file:
                <pre><code>TMDB_API_KEY=your_api_key</code></pre>
            </li>
        </ul>
    </li>
    <li><strong>Build and Run</strong> the app on an emulator or device.</li>
</ol>

---

<h2>🔗 How It Works</h2>
<ul>
    <li><strong>Home Screen</strong>: Displays categorized lists of movies dynamically using LazyColumn.</li>
    <li><strong>Search Screen</strong>: Enables quick movie searches with debounce for efficient API calls.</li>
    <li><strong>Bookmark Screen</strong>: Saves your favorite movies locally using RoomDB with an offline-first approach.</li>
    <li><strong>Details Screen</strong>: Provides rich information, including integrated YouTube trailer playback.</li>
    <li><strong>View More Screen</strong>: Lets you scroll through movies in a specific category with pagination support.</li>
</ul>

---

<h2>🤝 Contributing</h2>
<p>We welcome contributions to <strong>WatchList</strong>! Here's how you can help:</p>
<ol>
    <li>Fork the repository.</li>
    <li>Create a feature branch:
        <pre><code>git checkout -b feature/your-feature-name</code></pre>
    </li>
    <li>Commit your changes:
        <pre><code>git commit -m "Describe your changes here"</code></pre>
    </li>
    <li>Push to the branch:
        <pre><code>git push origin feature/your-feature-name</code></pre>
    </li>
    <li>Submit a pull request.</li>
</ol>
<p>For more details, see the <a href="CONTRIBUTING.md">Contribution Guidelines</a>.</p>

---

<h2>🙏 Acknowledgments</h2>
<ul>
    <li><strong><a href="https://www.themoviedb.org/">TMDB</a></strong>: For their amazing movie database API.</li>
    <li><strong><a href="https://github.com/PierfrancescoSoffritti/android-youtube-player">Pierfrancesco Soffritti</a></strong>: For the YouTube Player library.</li>
    <li><strong><a href="https://material.io/">Material 3</a></strong>: For its intuitive and modern design system.</li>
    <li><strong><a href="https://developer.android.com/studio">Android Studio</a></strong>: For being the primary IDE for Android development.</li>
    <li><strong><a href="https://developer.android.com/kotlin">Kotlin</a></strong>: For being the modern, concise, and powerful language for Android development.</li>
    <li><strong><a href="https://developer.android.com/jetpack/compose">Jetpack Compose</a></strong>: For enabling a seamless UI/UX experience.</li>
</ul>

---

<h2>
