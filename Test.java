class Test{


public static void main(String args[])  {  



	RecentlyPlayedStore store = new RecentlyPlayedStore(3);

// Add songs for a user
store.addSong("user1", "S1");
store.addSong("user1", "S2");
store.addSong("user1", "S3");

// Get the recent songs for the user
List<String> recentSongs = store.getRecentSongs("user1");
System.out.println("Recent songs for user1: " + recentSongs); // Output: [S3, S2, S1]

// Add another song for the user
store.addSong("user1", "S4");

// Get the recent songs again
recentSongs = store.getRecentSongs("user1");
System.out.println("Recent songs for user1: " + recentSongs); // Output: [S4, S2, S3]


}  

}
